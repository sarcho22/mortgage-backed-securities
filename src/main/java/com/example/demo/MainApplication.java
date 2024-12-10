package com.example.demo;

import java.util.*;

public class MainApplication {
    private static final String URL = "jdbc:postgresql://localhost:5432/mbs";
    private static final String USERNAME = "project";
    private static final String PASSWORD = "project2";

    private static final Scanner in = new Scanner(System.in);
    // referenced https://mkyong.com/jdbc/how-do-connect-to-postgresql-with-jdbc-driver-java/ to establish connection
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

        // try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

        //     if (conn != null) {
        //         System.out.println("Connected to the database!");
        //     } else {
        //         System.out.println("Failed to make connection!");
        //     }

        // } catch (SQLException e) {
        //     System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

    // display the menu for the three functionalities
    private static void displayMenu() {
        System.out.println("\n=-=-=-=-=-Main Menu-=-=-=-=-=");
        System.out.println("Active Filters: ");
        //System.out.println("   [insert active filters]");
        displayActiveFilters();
        // need to display which filters are active

        System.out.println("\nAvailable Options:");
        System.out.println("1. Add Filter\n2. Delete Filter\n3. Calculate Rate\n4. Exit\n");
        System.out.print("Choose an option (enter a number): ");
    }

    private static Map<String, List<String>> currentFilters = new HashMap<>();

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
                addingSpecFilter("MSAMD", "Enter MSAMD(s), type -1 when the list is complete:");
                break;
            case "2": 
                // (for this project you can use
                // applicant income / loan amount to compute this ratio) 
                System.out.print("Enter minimum income-to-debt ratio: ");
                double minIncomeToDebtRatio = in.nextDouble();
                System.out.print("Enter maximum income-to-debt ratio: ");
                double maxIncomeToDebtRatio = in.nextDouble();
                in.nextLine();

                List<String> filters1 = Arrays.asList(String.valueOf(minIncomeToDebtRatio), String.valueOf(maxIncomeToDebtRatio));
                currentFilters.put("Income to Debt Ratio", filters1);
                System.out.println("Added Income to Debt Ratio filter: " + minIncomeToDebtRatio + " to " + maxIncomeToDebtRatio);
                break;
            case "3":
                addingSpecFilter("County", "Enter County Name(s), type -1 when the list is complete:");
                break;
            case "4":
                addingSpecFilter("Loan Type", "Enter Loan Type(s), type -1 when the list is complete: ");
                break;
            case "5":
                System.out.print("Enter minimum tract to MSAMD income ratio: ");
                double minTractRatio = in.nextDouble();
                System.out.print("Enter maximum tract to MSAMD income ratio: ");
                double maxTractRatio = in.nextDouble();
                in.nextLine();

                List<String> filters2 = Arrays.asList(String.valueOf(minTractRatio), String.valueOf(maxTractRatio));
                currentFilters.put("Tract to MSAMD Income", filters2);
                System.out.println("Added Tract to MSAMD Income range: " + minTractRatio + " to " + maxTractRatio);
                break;
            
            case "6":
                addingSpecFilter("Loan Purpose", "Enter Loan Purpose(s), type -1 when the list is complete: ");
                break;
            case "7":
                addingSpecFilter("Property Type", "Enter Property Type(s), type -1 when the list is complete: ");
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
        
    }

    private static void addingSpecFilter(String filterName, String output) {
        System.out.println(output);
        List<String> filters = new ArrayList<>();
        String input = in.nextLine();
        while(!input.equals("-1")){
            filters.add(input);
            System.out.println("Enter another " + filterName + " or -1 to finish.");
            input = in.nextLine();
        }
        currentFilters.put(filterName, filters);
        System.out.println("Added: " + filterName + " filter for: " + String.join(", ",filters));
    }

    private static void displayActiveFilters()
    {
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
            System.out.println(String.join(" AND ", filterStrings));
        }
    }

    private static void deleteFilter() {
        System.out.println("delete filter to be implemented...");
    
    }

    private static void calculateRate() {
        System.out.println("calculate rate to be implemented...");
    }
}
