package com.example.demo;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        System.out.println("calculate rate to be implemented...");
    }
}
