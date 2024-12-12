package com.example.demo;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainApplication {
    private static final String URL = "jdbc:postgresql://localhost:5432/mbs";
    private static final String USERNAME = "project";
    private static final String PASSWORD = "project2";

    private static final Scanner in = new Scanner(System.in);
    private static final Map<String, String> msamdDict = new HashMap<>();

    private static Map<String, List<String>> currentFilters = new HashMap<>();
    private static Map<String, List<String>> allOptions = new HashMap<>();

    public static void main(String[] args) {
        initFilterOptions();
        initDict();

        System.out.println("Welcome to the Mortgage Backed Security Packer!");

        while(true) {
            displayMenu();
            String option = in.nextLine();
            
            switch(option) {
                case "1":
                    addFilter();
                    break;
                case "2":
                    deleteFilter();
                    break;
                case "3":
                    calculateRate();
                    break;
                case "4":
                    addMortgage();
                    break;
                case "5":
                    System.out.println("Thank you for using our MBS Packer. We hope to see you again soon!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again (enter a number)!");
            }
        }
    }

    // referenced https://mkyong.com/jdbc/how-do-connect-to-postgresql-with-jdbc-driver-java/ to establish connection
    private static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return conn;

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // display the menu for the three functionalities
    private static void displayMenu() {
        System.out.println("\n=-=-=-=-=-Main Menu-=-=-=-=-=");

        //System.out.println("   [insert active filters]");
        displayActiveFilters();
        // need to display which filters are active
        buildingFinalQuery();

        System.out.println("\nAvailable Options:");
        System.out.println("1. Add Filter\n2. Delete Filter\n3. Calculate Rate\n4. Add Mortgage\n5. Exit\n");
        System.out.print("Choose an option (enter a number): ");
    }

    private static void addMortgage(){
        System.out.println("Let's fill out the Mortgage Information!");
        int income = getInteger("What is the applicant income in thousands of dollars? (EX: 75)");
        double incomeVal = (double)income;
        int loanAmount = getInteger("What is the loan amount in thousands of dollars? (EX: 100)");
        double loanVal = (double)loanAmount;
        int MSAMD = getChoiceInteger("What is the MSAMD?",allOptions.get("MSAMD"));
        String MSAMDVal = msamdDict.get(allOptions.get("MSAMD").get(MSAMD));
        int sex = getChoiceInteger("What is the applicant sex?",allOptions.get("Sex"));
        if(sex==0){
            sex=1;
        }else if(sex==1){
            sex=2;
        }else if(sex==2){
            sex=4;
        }
        //String sexVal = allOptions.get("Sex").get(sex);
        int loanType = getChoiceInteger("What is the loan type?",allOptions.get("Loan Type"));
        loanType++;
        //String loanTypeVal = allOptions.get("Loan Type").get(loanType);
        int ethnicity = getChoiceInteger("What is the applicant ethnicity?",allOptions.get("Ethnicity"));
        if(ethnicity==0){
            ethnicity=1;
        }else if(ethnicity==1){
            ethnicity=2;
        }else if(ethnicity==2){
            ethnicity=4;
        }
        //String ethnicityVal = allOptions.get("Ethnicity").get(ethnicity);

        Connection newConn = null;
        PreparedStatement appStmt = null;
        PreparedStatement applicantStmt = null;
        PreparedStatement locationQueryStmt = null;
        PreparedStatement locationInsertStmt = null;
        try{
            newConn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            newConn.setAutoCommit(false);//To protect integrity in case of errors

            //First we try to get the location with the MSAMD code in it
            String locationQuery = "SELECT state_code, county_code " +
                    "FROM Location WHERE msamd = ? LIMIT 1";
            locationQueryStmt = newConn.prepareStatement(locationQuery);
            locationQueryStmt.setString(1, MSAMDVal);

            //System.out.println("QUERY1: " + locationQueryStmt.toString());
            ResultSet locationRs = locationQueryStmt.executeQuery();

            String stateCode = "";
            String countyCode = "";
            if (locationRs.next()) {
                stateCode = locationRs.getString("state_code");
                countyCode = locationRs.getString("county_code");
            } else {
                throw new SQLException("No location found for msamd: " + MSAMDVal);
            }
            //Assuming we got the location now, we go ahead and begin inserting into the application
            //We need to retrieve the application id that is created
            String appSql = "INSERT INTO Application (loan_amount_000s,loan_type, purchaser_type, action_taken) " +
                    "VALUES (?, ?, ?, ?) RETURNING application_id";
            appStmt = newConn.prepareStatement(appSql);
            appStmt.setDouble(1, loanVal);
            appStmt.setInt(2, loanType);
            appStmt.setInt(3, 0);
            appStmt.setInt(4, 1);
            //System.out.println("QUERY2: " + appStmt.toString());

            //get the application id
            ResultSet rs = appStmt.executeQuery();
            int applicationId = 0;
            if (rs.next()) {
                applicationId = rs.getInt("application_id");
            }

            //Assuming things are still working, we now proceed to insert into the applicant table
            String applicantSql = "INSERT INTO Applicant (application_id, applicant_ethnicity, applicant_sex, applicant_income_000s) " +
                    "VALUES (?, ?, ?, ?)";
            applicantStmt = newConn.prepareStatement(applicantSql);
            applicantStmt.setInt(1, applicationId);
            applicantStmt.setInt(2, ethnicity);
            applicantStmt.setInt(3, sex);
            applicantStmt.setDouble(4, incomeVal);
            //System.out.println("QUERY3: " + applicantStmt.toString());
            applicantStmt.executeUpdate();

            String locationInsertSql = "INSERT INTO Location (application_id, msamd, state_code, county_code) " +
                    "VALUES (?, ?, ?, ?)";
            locationInsertStmt = newConn.prepareStatement(locationInsertSql);
            locationInsertStmt.setInt(1, applicationId);
            locationInsertStmt.setString(2, MSAMDVal);
            locationInsertStmt.setString(3, stateCode);
            locationInsertStmt.setString(4, countyCode);
            //System.out.println("QUERY4: " + locationInsertStmt.toString());
            locationInsertStmt.executeUpdate();


            //commit transaction, if we made it this far, means it didn't blow up!
            newConn.commit();
            System.out.println("New mortgage record inserted successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                //close the connections we made!
                if (locationQueryStmt != null) locationQueryStmt.close();
                if (locationInsertStmt != null) locationInsertStmt.close();
                if (appStmt != null) appStmt.close();
                if (applicantStmt != null) applicantStmt.close();
                if (newConn != null) newConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    private static int getInteger(String Instructions){
        while(true){
            System.out.println(Instructions);//need only print once
            String input = in.nextLine();
            try{
                return Integer.parseInt(input);
            }catch(NumberFormatException e){
                System.out.println("Please enter an integer!");//print warning and loop again
            }
        }
    }

    private static int getChoiceInteger(String Instructions, List<String> choices)
    {
        while(true) {
            System.out.println(Instructions);
            for (int i = 0; i < choices.size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), choices.get(i));
            }
            System.out.print("\nSelect the number that matches your option: ");
            String input = in.nextLine();
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < choices.size()) {
                    return index;
                } else {
                    System.out.println("Invalid selection. Please choose a number between 1 and " + choices.size() + ".");
                }
            }catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static void addFilter() {
        // add filter NOT fully implemented (only worked on display, not functionality)
        System.out.println("\n\n=-=-=-=-Filter Types-=-=-=-=");
        // MAKE SURE TO REMOVE OPTION IF WE DONT IMPLEMENT ALL OF THESE
        System.out.println("1. MSAMD\n2. Income to Debt Ratio\n3. County\n4. Loan Type\n5. Tract to MSAMD Income\n6. Loan Purpose\n7. Property Type\n8. Owner Occupancy\n");
        System.out.print("Which filter would you like to add (enter a number)? ");
        String option = in.nextLine();

        switch(option) {
            case "1":
                // provide a list of msamd with names if they have them, or numbers if there
                // is no name, the user should be able to pick any one or more of them. (allow list of
                // acceptable MSAMD)
                // addingSpecFilter("MSAMD", "Enter MSAMD(s), type -1 when the list is complete:");
                addListFilter("MSAMD");
                break;
            case "2": 
                // (for this project you can use
                // applicant income / loan amount to compute this ratio) 
                // System.out.print("Enter minimum income-to-debt ratio: ");
                // double minIncomeToDebtRatio = in.nextDouble();
                // System.out.print("Enter maximum income-to-debt ratio: ");
                // double maxIncomeToDebtRatio = in.nextDouble();
                // in.nextLine();

                // List<String> filters1 = Arrays.asList(String.valueOf(minIncomeToDebtRatio), String.valueOf(maxIncomeToDebtRatio));
                // currentFilters.put("Income to Debt Ratio", filters1);
                // System.out.println("Added Income to Debt Ratio filter: " + minIncomeToDebtRatio + " to " + maxIncomeToDebtRatio);
                addRangeFilter("Income to Debt Ratio", "Enter minimum income-to-debt ratio:", "Enter maximum income-to-debt ratio:");
                break;
            case "3":
                addListFilter("County");
                break;
            case "4":
                // addingSpecFilter("Loan Type", "Enter Loan Type(s), type -1 when the list is complete: ");
                addListFilter("Loan Type");
                break;
            case "5":

                // List<String> filters2 = Arrays.asList(String.valueOf(minTractRatio), String.valueOf(maxTractRatio));
                // currentFilters.put("Tract to MSAMD Income", filters2);
                // System.out.println("Added Tract to MSAMD Income range: " + minTractRatio + " to " + maxTractRatio);
                addRangeFilter("Tract to MSAMD Income", "Enter minimum tract to MSAMD income ratio:", "Enter maximum tract to MSAMD income ratio:");
                break;
            
            case "6":
                // addingSpecFilter("Loan Purpose", "Enter Loan Purpose(s), type -1 when the list is complete: ");
                addListFilter("Loan Type");
                break;
            case "7":
                // addingSpecFilter("Property Type", "Enter Property Type(s), type -1 when the list is complete: ");
                addListFilter("Property Type");
                break;
            case "8":
                // addingSpecFilter("Property Type", "Enter Property Type(s), type -1 when the list is complete: ");
                addListFilter("Owner Occupancy");
                break;
            default:
                System.out.println("Invalid option! Back to main menu.");
                break;
        }
        
    }

    private static void addListFilter(String filter) {
        List<String> available = new ArrayList<>(allOptions.get(filter));
        List<String> selected = currentFilters.get(filter);

        if(selected != null) {
            // found "removeall" function in https://docs.oracle.com/javase/8/docs/api/java/util/List.html
            available.removeAll(selected);
        }
        else {
            selected = new ArrayList<>();
        }

        if(available.isEmpty()) {
            System.out.printf("No more options available for %s.\n", filter);
            return;
        }

        // display option menu
        System.out.printf("\nAvailable options for %s:\n", filter);
        for(int i = 0; i < available.size(); i++) {
            System.out.printf("%d. %s\n", (i+1), available.get(i));
        }

        System.out.print("\nWhich option would you like to filter for (enter numbers separated by commas: \"1,2,3\"): ");
        String input = in.nextLine();
        String[] inputs = input.split(",");

        boolean failed = false;
        List<String> success = new ArrayList<>();

        for(int i = 0; i < inputs.length; i++) {
            String choice = inputs[i].trim();
            try {
                int index = Integer.parseInt(choice) - 1; // -1 because the options start counting from 1 but indices start as 0
                if(index >= 0 && index < available.size()) {
                    String op = available.get(index);
                    if(!selected.contains(op)) {
                        selected.add(op);
                        success.add(op);
                    }
                    else {
                        failed = true;
                    }
                }
                else {
                    failed = true;
                }
            } catch (Exception e) {
                failed = true; // invalid!!
            }
        }

        if(!selected.isEmpty()) {
            currentFilters.put(filter, selected);
        }

        if(!success.isEmpty()) {
            System.out.println("Successfully added " + filter + " filter(s): " + String.join(", ", success));
            if(failed) {
                System.out.println("The rest of the filters couldn't be applied.");
            }
        }
        else {
            System.out.println("No filters were successfully applied.");
        }
    }

    private static void addRangeFilter(String filter, String minString, String maxString) {
        try {
            System.out.println(minString);
            double min = Double.parseDouble(in.nextLine().trim());
            System.out.println(maxString);
            double max = Double.parseDouble(in.nextLine().trim());

            if(min > max) {
                System.out.println("Min can't be greater than max! Returning to main menu.");
            }
            else if(min < 0 || max < 0) {
                System.out.println("No negative numbers! Returning to main menu.");
            }
            else {
                List<String> range = Arrays.asList("" + min, "" + max);
                currentFilters.put(filter, range);
                System.out.printf("Added %s filter: between %f and %f.", filter, min, max);
            }

        } catch (Exception e) {
            System.out.println("Invalid input! Returning to main menu.");
        }
    }

    private static void displayActiveFilters()
    {
        System.out.println("Active Filters: ");
        if(currentFilters.isEmpty()){
            System.out.println("No active filters");
        }else{
            List<String> filterStrings=new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : currentFilters.entrySet()) {
                String filterName = entry.getKey();
                String filterValue = "";
                if(filterName.equals("Income to Debt Ratio") || filterName.equals("Tract to MSAMD Income")){
                    filterValue = String.join(" and " , entry.getValue());
                    filterValue = "between " + filterValue;
                }
                else{
                    filterValue = String.join(" OR " , entry.getValue());
                }
                filterStrings.add(filterName + "= " + filterValue);

            }
            System.out.println(String.join(" AND\n", filterStrings));
        }
    }

    private static void deleteFilter() {
        if(currentFilters.isEmpty()) {
            System.out.println("No filters to delete! Returning to main menu.");
            return;
        }

        System.out.println("\nActive Filters:");
        List<String> filterKeys = new ArrayList<String>(currentFilters.keySet());

        for(int i = 0; i < filterKeys.size(); i++) {
            System.out.printf("%d. %s\n", (i+1), filterKeys.get(i));
        }
        System.out.printf("%d. Delete all filters\n\n", (filterKeys.size()+1));

        System.out.print("Choose a filter to delete (enter a number): ");
        String input = in.nextLine();
        int choice = -1;

        try {
            choice = Integer.parseInt(input);
            if (choice < 1 || choice > filterKeys.size() + 1) {
                System.out.println("Invalid choice! Returning to main menu.");
                return;
            }
        } catch(Exception e) {
            System.err.println("Invalid input! Returning to main menu.");
        }

        if(choice == filterKeys.size() + 1) {
            currentFilters.clear();
            System.out.println("All filters have been deleted.");
        }
        else {
            String remove = filterKeys.get(choice-1);
            currentFilters.remove(remove);
            System.out.printf("Successfully removed %s filter(s). Returning to main menu.\n", remove);
        }
    }

    private static void calculateRate() {
		StringBuilder finalQuery = new StringBuilder(
                "SELECT Application.loan_amount_000s, Application.rate_spread, Application.lien_status, Application.application_id " +
                        "FROM Application " +
                        "LEFT JOIN LoanType ON Application.loan_type = LoanType.loan_type " +
                        "LEFT JOIN PropertyType ON Application.property_type = PropertyType.property_type " +
                        "LEFT JOIN LoanPurpose ON Application.loan_purpose = LoanPurpose.loan_purpose " +
                        "LEFT JOIN OwnerOccupancy ON Application.owner_occupancy = OwnerOccupancy.owner_occupancy " +
                        "LEFT JOIN ActionTaken ON Application.action_taken = ActionTaken.action_taken " +
                        "LEFT JOIN Location ON Application.application_id = Location.application_id " +
                        "LEFT JOIN County ON Location.county_code = County.county_code " +
                        "LEFT JOIN MSAMD ON Location.msamd = MSAMD.msamd " +
                        "LEFT JOIN PurchaserType ON Application.purchaser_type = PurchaserType.purchaser_type " +
                        "LEFT JOIN Applicant ON Application.application_id = Applicant.application_id " +
                        "WHERE ActionTaken.action_taken_name = 'Loan originated' AND PurchaserType.purchaser_type IN (0, 1, 2, 3, 4, 8) "
        );

        for(String key : currentFilters.keySet()) {
            finalQuery.append("AND (");
            List<String> choices = currentFilters.get(key);
            for(int i = 0; i < choices.size(); i++) {
                String keyVal = key;
                if(key.equals("County")|| key.equals("Loan Type") || key.equals("Loan Purpose") || key.equals("Property Type") || key.equals("Owner Occupancy")) {
                    keyVal = keyVal.replaceAll("\\s+", "_") + "_name";
                    finalQuery.append(keyVal).append(" = ? ");
                    if(i < choices.size()-1) {
                        finalQuery.append("OR ");
                    }
                }
                if(key.equals("MSAMD")) {
                    keyVal = "Location.msamd";
                    finalQuery.append(keyVal).append(" = ? ");
                    if(i < choices.size()-1) {
                        finalQuery.append("OR ");
                    }
                }
                if(key.equals("Tract to MSAMD Income")) {
                    keyVal = "(Location.tract_to_msamd_income::numeric)";
                    if(i == 0) {
                        finalQuery.append(keyVal).append(" BETWEEN ? ");
                    }
                    // checking even/odd because code is structured so that
                    // we can check multiple ranges if needed
                    else if((i % 2) == 1) {
                        finalQuery.append("AND ? ");
                        if(i < choices.size() - 1) {
                            finalQuery.append("OR ");
                        }
                    }
                    else if((i % 2) == 0) {
                        finalQuery.append("? ");
                    }
                }
                if(key.equals("Income to Debt Ratio")) {
                    keyVal = "(applicant.applicant_income_000s / application.loan_amount_000s)";
                    if(i == 0) {
                        finalQuery.append(keyVal).append(" BETWEEN ? ");
                    }

                    // checking even/odd because code is structured so that
                    // we can check multiple ranges if needed
                    else if((i % 2) == 1) {
                        finalQuery.append("AND ? ");
                        if(i < choices.size() - 1) {
                            finalQuery.append("OR ");
                        }
                    }
                    else if((i % 2) == 0) {
                        finalQuery.append("? ");
                    }
                }
            }
            finalQuery.append(") ");
        }
        finalQuery.append(";");

        int index = 1;
        String tempString = finalQuery.toString();

        try {
        	Connection conn = getConnection();

        	if(conn == null) {
        		System.out.println("Database connection failed...");
        		return;
        	}

            PreparedStatement stmt = conn.prepareStatement(tempString);
            for(String key : currentFilters.keySet()) {
                List<String> choices = currentFilters.get(key);

                if(key.equals("MSAMD")) {
                    for(String value : choices) {
                        String msamd_code = msamdDict.get(value);
                        stmt.setString(index++, msamd_code);
                    }
                }
                else if(key.equals("Tract to MSAMD Income") || key.equals("Income to Debt Ratio")) {
                    for(String value : choices) {
                        double converted = Double.parseDouble(value);
                        stmt.setDouble(index++, converted);
                    }
                }
                else {
                    for(String value : choices) {
                        stmt.setString(index++, value);
                    }
                }
                
            }

            ResultSet rs = stmt.executeQuery();
            
            // variables to calculate weighted avg of rates (weighted by loan amt) AND sum of all mortgages as cost of securitization
            double weightedRateSum = 0.0;
            long loanSum = 0;

            // making a list of application_ids to know which ones to update if user accepts
            List<Integer> applicationIDs = new ArrayList<>();

            while(rs.next()) {
            	// multiplied loan amount by 1000s because it was stored in the database in 1000s!!!
            	long loanAmount = rs.getLong("loan_amount_000s") * 1000;
            	int lienStatus = rs.getInt("lien_status");

            	double rateSpread = rs.getDouble("rate_spread");

            	// checked https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html to see how to determine if a value is null
            	if(rs.wasNull()) {
            		if(lienStatus == 1) {
                        rateSpread = 1.5;
                    }
                    else{
                        rateSpread = 3.5;
                    }
            	}

            	rateSpread += 2.33; // account for base rate!!

            	loanSum += loanAmount;
            	weightedRateSum += rateSpread * loanAmount;

            	applicationIDs.add(rs.getInt("application_id"));
            }

            if(loanSum > 0) { // just to make sure no division by 0
                // to fully understand how the weighted avg rate works, i used this site:
                // https://www.savingforcollege.com/article/how-to-calculate-the-weighted-average-interest-rate
                double weightedRateAvg = weightedRateSum / loanSum;
                System.out.println("\n=-=-=-=-Rate Calculation-=-=-=-=");
                System.out.printf("Total Loan Amount: %d\nWeighted Average Rate: %.3f\n", loanSum, weightedRateAvg);

                System.out.print("\nDo you accept this rate? (yes/no): ");
                String response = in.nextLine().trim().toLowerCase();

                if(response.equals("yes")) {
                    updateDatabase(conn, applicationIDs);
                    System.out.println("Thank you for using our MBS Packer. We hope to see you again soon!");
                    System.exit(0);
                }
                else if(response.equals("no")) {
                    System.out.println("Rate declined. Returning to main menu.\n\n");
                }
            }
            else if(loanSum < 0) {
                System.out.println("Integer Overflow Error!");
            }
            else {
                System.out.println("No matching mortgages found.");
            }
        }
        catch (Exception e) {
        	System.err.println("An error occurred during rate calculation: " + e.getMessage());
            e.printStackTrace();
        }
	}

    private static void updateDatabase(Connection conn, List<Integer> ids) {
        // used https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html to figure out how to use parameters in a query in jdbc

        String query = """
                UPDATE Application
                SET purchaser_type = 5
                WHERE application_id = ?;
                """;
        
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            // used https://www.geeksforgeeks.org/how-to-execute-multiple-sql-commands-on-a-database-simultaneously-in-jdbc/ to figure out how to run multiple queries instead of running each one individually
            // also used https://docs.raima.com/rdm/15_2/ug/jdbc/Statement/Method/executeBatch.htm to figure out how to determine if the updates were successful or failed

            for(int id : ids) {
                stmt.setInt(1, id);
                stmt.addBatch();
            }
            int[] updateStatus = stmt.executeBatch();
            int count = 0;
            ArrayList<Integer> failed = new ArrayList<Integer>();

            for(int i = 0; i < updateStatus.length; i++) {
                if(updateStatus[i] == Statement.EXECUTE_FAILED) {
                    failed.add(ids.get(i));
                }
                else {
                    count++;
                }
            }

            System.out.printf("Successfully updated %d loans to 'Private Securitization'!\n", count);
            if(!failed.isEmpty()) {
                System.err.println("Failed to update the following application IDs: " + failed);
            }

        } catch (SQLException e) {
            System.err.println("Failed to update database: " + e.getMessage());
        }
    }

    private static String buildingFinalQuery()
    {
        /*
        Couple of issues:
        1. The key values as we store them in currentFilters don't line up with column names
        2. While we can fix this with an if statement as seen below, it doesnt work for MSAMD
           MSAMD is a mix of 2 columns, so you would need to dynamically alter which column goes in the query.
        3. income to debt ratio and tract to msamd income need to be considered as separate cases bc
           they use between and also we need to calculate income to debt ratio.
        4. We're still missing the 8th filter.
         */



        StringBuilder finalQuery = new StringBuilder(
                "SELECT COUNT(*) AS total_rows, SUM(Application.loan_amount_000s) AS total_loan_amount " +
                        "FROM Application " +
                        "LEFT JOIN LoanType ON Application.loan_type = LoanType.loan_type " +
                        "LEFT JOIN PropertyType ON Application.property_type = PropertyType.property_type " +
                        "LEFT JOIN LoanPurpose ON Application.loan_purpose = LoanPurpose.loan_purpose " +
                        "LEFT JOIN OwnerOccupancy ON Application.owner_occupancy = OwnerOccupancy.owner_occupancy " +
                        "LEFT JOIN ActionTaken ON Application.action_taken = ActionTaken.action_taken " +
                        "LEFT JOIN Location ON Application.application_id = Location.application_id " +
                        "LEFT JOIN County ON Location.county_code = County.county_code " +
                        "LEFT JOIN MSAMD ON Location.msamd = MSAMD.msamd " +
                        "LEFT JOIN PurchaserType ON Application.purchaser_type = PurchaserType.purchaser_type " +
                        "LEFT JOIN Applicant ON Application.application_id = Applicant.application_id " +
                        "WHERE ActionTaken.action_taken_name = 'Loan originated' AND PurchaserType.purchaser_type IN (0, 1, 2, 3, 4, 8) "
        );

        for (String key : currentFilters.keySet())
        {
            finalQuery.append("AND (");
            List<String> choices = currentFilters.get(key);
            //Need specific if/else case. If it is a filter for
            //tract income or debt ratio cuz it needs to be between
            for (int i = 0;i<choices.size();i++)
            {
                String keyVal = key;
                if(key.equals("County")|| key.equals("Loan Type") || key.equals("Loan Purpose") || key.equals("Property Type") || key.equals("Owner Occupancy"))
                {
                    keyVal = keyVal.replaceAll("\\s+", "_") + "_name";
                    finalQuery.append(keyVal).append(" = ? ");
                    if(i<choices.size() - 1)
                    {
                        finalQuery.append("OR ");
                    }
                }
                if(key.equals("MSAMD")) {
                    keyVal = "Location.msamd";
                    finalQuery.append(keyVal).append(" = ? ");
                    if(i<choices.size() - 1)
                    {
                        finalQuery.append("OR ");
                    }
                }
                if(key.equals("Tract to MSAMD Income")) {
                    keyVal = "(Location.tract_to_msamd_income::numeric)";
                    if(i==0){
                        finalQuery.append(keyVal).append(" BETWEEN ? ");
                    }
                    //checking even/odd because code is structured so that
                    //we can check multiple ranges if needed
                    else if(i%2==1){
                        finalQuery.append("AND ? ");
                        if(i < choices.size() - 1) {
                            finalQuery.append("OR ");
                        }
                    }
                    else if(i%2==0){
                        finalQuery.append("? ");
                    }
                }
                if(key.equals("Income to Debt Ratio")) {
                    keyVal = "(applicant.applicant_income_000s / application.loan_amount_000s)";
                    if(i==0){
                        finalQuery.append(keyVal).append(" BETWEEN ? ");
                    }
                    //checking even/odd because code is structured so that
                    //we can check multiple ranges if needed
                    else if(i%2==1){
                        finalQuery.append("AND ? ");
                        if(i < choices.size() - 1) {
                            finalQuery.append("OR ");
                        }
                    }
                    else if(i%2==0){
                        finalQuery.append("? ");
                    }
                }


            }
            finalQuery.append(") ");
        }
        finalQuery.append(";");
        //System.out.println("Final Query: " + finalQuery);

        int index = 1;
        String tempString = finalQuery.toString();

        try(Connection tempConnection = getConnection()){
            PreparedStatement statement = tempConnection.prepareStatement(tempString);
            for(String key : currentFilters.keySet())
            {
                List<String> choices = currentFilters.get(key);

                if(key.equals("MSAMD")) {
                    for(String value : choices) {
                        String msamd_code = msamdDict.get(value);
                        statement.setString(index++, msamd_code);
                    }
                }
                else if(key.equals("Tract to MSAMD Income") || key.equals("Income to Debt Ratio")) {
                    for(String value : choices) {
                        double converted = Double.parseDouble(value);
                        statement.setDouble(index++, converted);
                    }
                }
                else {
                    for(String value : choices)
                    {
                        statement.setString(index++,value);
                    }
                }
                
            }
            //System.out.println(statement.toString());
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                int matchingRows = rs.getInt("total_rows");
                long totalLoanAmount = rs.getLong("total_loan_amount") * 1000;

                System.out.println("\nMatching Rows: " + matchingRows);
                System.out.println("Total Loan Amount: " + (long)(totalLoanAmount));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    private static void initFilterOptions() {
        allOptions.put("MSAMD", Arrays.asList(
            "Allentown, Bethlehem, Easton - PA, NJ",
            "Atlantic City, Hammonton - NJ",
            "Camden - NJ",
            "Newark - NJ, PA",
            "New York, Jersey City, White Plains - NY, NJ",
            "35620",
            "Ocean City - NJ",
            "37980",
            "Trenton - NJ",
            "Vineland, Bridgeton - NJ",
            "Wilmington - DE, MD, NJ"
        ));

        allOptions.put("County", Arrays.asList(
            "Atlantic County",
            "Cumberland County",
            "Essex County",
            "Gloucester County",
            "Hudson County",
            "Hunterdon County",
            "Mercer County",
            "Middlesex County",
            "Monmouth County",
            "Morris County",
            "Ocean County",
            "Bergen County",
            "Passaic County",
            "Salem County",
            "Somerset County",
            "Sussex County",
            "Union County",
            "Warren County",
            "Burlington County",
            "Camden County",
            "Cape May County"
        ));

        allOptions.put("Loan Type", Arrays.asList(
            "Conventional",
            "FHA-insured",
            "VA-guaranteed",
            "FSA/RHS-guaranteed"
        ));

        allOptions.put("Loan Purpose", Arrays.asList(
            "Home purchase",
            "Home improvement",
            "Refinancing"
        ));

        allOptions.put("Property Type", Arrays.asList(
            "One-to-four family dwelling (other than manufactured housing)",
            "Manufactured housing",
            "Multifamily dwelling"
        ));

        allOptions.put("Owner Occupancy", Arrays.asList(
                "Owner-occupied as a principal dwelling",
                "Not owner-occupied as a principal dwelling",
                "Not applicable"
        ));

        allOptions.put("Ethnicity", Arrays.asList(
                "Hispanic or Latino",//1
                "Not Hispanic or Latino",//2
                "Not applicable"//4
        ));

        allOptions.put("Sex", Arrays.asList(
                "Male",//1
                "Female",//2
                "Not applicable"//4
        ));
    }

    private static void initDict() {
        msamdDict.put("Allentown, Bethlehem, Easton - PA, NJ", "10900");
        msamdDict.put("Atlantic City, Hammonton - NJ", "12100");
        msamdDict.put("Camden - NJ", "15804");
        msamdDict.put("Newark - NJ, PA", "35084");
        msamdDict.put("New York, Jersey City, White Plains - NY, NJ", "35614");
        msamdDict.put("35620", "35620");
        msamdDict.put("Ocean City - NJ", "36140");
        msamdDict.put("37980", "37980");
        msamdDict.put("Trenton - NJ", "45940");
        msamdDict.put("Vineland, Bridgeton - NJ", "47220");
        msamdDict.put("Wilmington - DE, MD, NJ", "48864");
    }
}
