package banking.cli;

import banking.service.BankService;

import java.util.Scanner;

public class Menu {

    private final BankService bankService = new BankService();
    private final Scanner sc = new Scanner(System.in);
    public void start() {
        while (true) {
            printMenu();
            int choice = validateChoice();

            switch (choice) {
                case 1 -> accountCreation();
                case 2 -> depositAmount();
                case 3 -> withdrawAmount();
                case 4 -> showAccountSummary();
                case 5 -> {
                    System.out.println("Thank you! Exiting.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private int validateChoice() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter A Valid Number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private void printMenu() {
        System.out.println("\nMenu: ");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. View Account Summary");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void accountCreation() {
        System.out.print("Name: ");
        String name = sc.next();

        System.out.print("Age: ");
        int age = validateChoice();

        System.out.print("Gender (M/F): ");
        char gender = sc.next().charAt(0);

        long accountNumber = bankService.createUser(name, age, gender);
        System.out.println("Account created successfully! Your Account Number: " + accountNumber);
    }

    private void depositAmount() {
        System.out.println("Enter Account Number");
        long accountNumber = sc.nextLong();

        System.out.println("Enter amount to deposit: ");
        int amount = sc.nextInt();

        bankService.deposit(accountNumber, amount);

    }

    private void withdrawAmount() {
        System.out.println("Enter Account Number");
        long accountNumber = sc.nextLong();

        System.out.println("Enter amount to withdraw: ");
        int amount = sc.nextInt();

        bankService.withdraw(accountNumber, amount);
    }

    private void showAccountSummary() {
        System.out.println("Enter Account Number");
        long accountNumber = sc.nextLong();


    }
}
