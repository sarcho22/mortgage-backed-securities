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
    public static void main(String[] args) {
        System.out.println("Welcome! [insert some generic msg or we can name this silly app]");

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
                    System.out.println("Thank you for using [insert silly app name]. We hope to see you again soon!");
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
            // debugging statements
            // if (conn != null) {
            //     System.out.println("Connected to the database!");
            // } else {
            //     System.out.println("Failed to make connection!");
            // }
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
        System.out.println("Active Filters: ");
        System.out.println("   [insert active filters]");
        // need to display which filters are active

        System.out.println("\nAvailable Options:");
        System.out.println("1. Add Filter\n2. Delete Filter\n3. Calculate Rate\n4. Exit\n");
        System.out.print("Choose an option (enter a number): ");
    }

    private static void addFilter() {
        System.out.println("add filter NOT fully implemented (only worked on display, not functionality)...");
        System.out.println("Choose a filter type:");
        // MAKE SURE TO REMOVE OPTION IF WE DONT IMPLEMENT ALL OF THESE
        System.out.println("1. MSAMD\n2. Income to Debt Ratio\n3. County\n4. Loan Type\n5. Tract to MSAMD Income\n6. Loan Purpose\n7. Property Type\n");
        System.out.println("Which filter would you like to add (enter a number)?");
        String option = in.nextLine();

        switch(option) {
            case "1":
                // provide a list of msamd with names if they have them, or numbers if there
                // is no name, the user should be able to pick any one or more of them. (allow list of
                // acceptable MSAMD)
                System.out.println("Enter MSAMD(s), type -1 when the list is complete:");
                String msamd = in.nextLine();
                while (!msamd.equals("-1")) {
                    System.out.println("Added MSAMD: " + msamd);
                    System.out.print("Enter another MSAMD or -1 to finish: ");
                    msamd = in.nextLine();
                }
                break;
            case "2": 
                // (for this project you can use
                // applicant income / loan amount to compute this ratio) 
                System.out.print("Enter minimum income-to-debt ratio: ");
                double minIncomeToDebtRatio = in.nextDouble();
                System.out.print("Enter maximum income-to-debt ratio: ");
                double maxIncomeToDebtRatio = in.nextDouble();
                System.out.println("Added Income to Debt Ratio range: " + minIncomeToDebtRatio + " to " + maxIncomeToDebtRatio);
                break;
            case "3": 
                System.out.println("Enter County Name(s), type -1 when the list is complete:");
                String county = in.nextLine();
                while (!county.equals("-1")) {
                    System.out.println("Added County: " + county);
                    System.out.print("Enter another County or -1 to finish: ");
                    county = in.nextLine();
                }
                break;
            case "4":
                System.out.println("Enter Loan Type(s), type -1 when the list is complete: ");
                String loanType = in.nextLine();
                while (!loanType.equals("-1")) {
                    System.out.println("Added Loan Type: " + loanType);
                    System.out.print("Enter another Loan Type or -1 to finish: ");
                    loanType = in.nextLine();
                }
                break;
            case "5":
                System.out.print("Enter minimum tract to MSAMD income ratio: ");
                double minTractRatio = in.nextDouble();
                System.out.print("Enter maximum tract to MSAMD income ratio: ");
                double maxTractRatio = in.nextDouble();
                in.nextLine(); // Consume newline
                System.out.println("Added Tract to MSAMD Income range: " + minTractRatio + " to " + maxTractRatio);
                break;
            
            case "6":
                System.out.println("Enter Loan Purpose(s), type -1 when the list is complete: ");
                String loanPurpose = in.nextLine();
                while (!loanPurpose.equals("-1")) {
                    System.out.println("Added Loan Purpose: " + loanPurpose);
                    System.out.print("Enter another Loan Purpose or -1 to finish: ");
                    loanPurpose = in.nextLine();
                }
                break;
            
            case "7":
                System.out.println("Enter Property Type(s), type -1 when the list is complete: ");
                String propertyType = in.nextLine();
                while (!propertyType.equals("-1")) {
                    System.out.println("Added Property Type: " + propertyType);
                    System.out.print("Enter another Property Type or -1 to finish: ");
                    propertyType = in.nextLine();
                }
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
        
    }

    private static void deleteFilter() {
        System.out.println("delete filter to be implemented...");
    
    }

    private static void calculateRate() {
        // NEED TO UPDATE CALCULATE RATE ONCE ADD FILTER IS WORKING!!!

        String query = """
            SELECT a.loan_amount_000s, a.rate_spread, a.lien_status, a.application_id
            FROM application a JOIN ActionTaken at ON a.action_taken = at.action_taken
            WHERE at.action_taken = 1 AND a.purchaser_type IN (0, 1, 2, 3, 4, 8);
            """;

        try {
            Connection conn = getConnection();
            if(conn == null) {
                System.out.println("Database connection failed...");
                return;
            }

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            // variables to calculate weighted avg of rates (weighted by loan amt) AND sum of all mortgages as cost of securitization
            double weightedRateSum = 0.0;
            long loanSum = 0;

            // making a list of application_ids to know which ones to update if user accepts
            ArrayList<Integer> applicationIds = new ArrayList<>();

            while(rs.next()) {
                // multiplied loan amount by 1000s because it was stored in the database in 1000s!!!
                int loanAmount = rs.getInt("loan_amount_000s") * 1000;
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

                applicationIds.add(rs.getInt("application_id"));
            }

            if(loanSum > 0) { // just to make sure no division by 0
                // to fully understand how the weighted avg rate works, i used this site:
                // https://www.savingforcollege.com/article/how-to-calculate-the-weighted-average-interest-rate
                double weightedRateAvg = weightedRateSum / loanSum;
                System.out.println("\n=-=-=-=-Rate Calculation-=-=-=-=");
                System.out.printf("   Total Loan Amount: %d\n   Weighted Average Rate: %.3f\n", loanSum, weightedRateAvg);

                System.out.print("\nDo you accept this rate? (yes/no): ");
                String response = in.nextLine().trim().toLowerCase();

                if(response.equals("yes")) {
                    updateDatabase(conn, applicationIds);
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

        } catch (Exception e) {
            System.err.println("An error occurred during rate calculation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void updateDatabase(Connection conn, ArrayList<Integer> ids) {
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

            System.out.printf("Updated %d loans to 'Private Securitization'.\n", count);
            if(!failed.isEmpty()) {
                System.err.println("Failed to update the following application IDsP: " + failed);
            }

        } catch (SQLException e) {
            System.err.println("Failed to update database: " + e.getMessage());
        }
    }
}
