package banking.cli;

import banking.controller.Controller;
import banking.sessionmanager.SessionManager;

import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in);
    private final SessionManager sessionManager;
    private final Controller controller;

    public Menu(SessionManager sessionManager, Controller controller) {
        this.sessionManager = sessionManager;
        this.controller = controller;
    }

    public void start() {
        while (!sessionManager.isLoggedIn()) {
            System.out.println("Welcome! Please select:");
            System.out.println("1. Create Account");
            System.out.println("2. Login with Account Number");
            System.out.print("Choice: ");
            int choice = validateChoice();

            if (choice == 1) { controller.accountCreation(); }
            else if (choice == 2) {
                System.out.print("Enter your Account Number: ");
                long accNum = sc.nextLong();
                if (sessionManager.login(accNum)) { System.out.println("Logged in with Account Number: " + accNum); }
                else { System.out.println("Account number not found, try again."); }
            }
            else { System.out.println("Invalid choice, try again."); }
        }

        while (true) {
            printMenu();
            int choice = validateChoice();

            switch (choice) {
                case 1 -> controller.depositAmount();
                case 2 -> controller.withdrawAmount();
                case 3 -> controller.showAccountSummary();
                case 4 -> controller.viewUserTransactionHistory();
                case 5 -> {
                    System.out.println("Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private int validateChoice() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private void printMenu() {
        System.out.println("\nMenu: ");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. View Account Summary");
        System.out.println("4. View Transaction History");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
}
