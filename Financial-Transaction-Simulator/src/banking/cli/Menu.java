package banking.cli;

import banking.model.User;
import banking.service.BankService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final BankService bankService = new BankService();
    private Long currentAccountNumber = null;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Scanner sc = new Scanner(System.in);
    public void start() {
        while (currentAccountNumber == null) {
            System.out.println("Welcome! Please select:");
            System.out.println("1. Create Account");
            System.out.println("2. Login with Account Number");
            System.out.print("Choice: ");
            int choice = validateChoice();

            if (choice == 1) { accountCreation(); }
            else if (choice == 2) {
                System.out.print("Enter your Account Number: ");
                long accNum = sc.nextLong();
                if (bankService.getUserByAccountNumber(accNum) != null) {
                    currentAccountNumber = accNum;
                    System.out.println("Logged in with Account Number: " + currentAccountNumber);
                }
                else { System.out.println("Account number not found, try again."); }
            }
            else { System.out.println("Invalid choice, try again."); }
        }

        while (true) {
            printMenu();
            int choice = validateChoice();

            switch (choice) {
                case 1 -> depositAmount();
                case 2 -> withdrawAmount();
                case 3 -> showAccountSummary();
                case 4 -> viewUserTransactionHistory();
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
            System.out.print("Enter A Valid Number: ");
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

    private void accountCreation() {
        System.out.print("Name: ");
        String name = sc.next();

        System.out.print("Age: ");
        int age = validateChoice();

        System.out.print("Gender (M/F): ");
        char gender = sc.next().charAt(0);

        long accountNumber = bankService.createUser(name, age, gender);

        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] Account created for user: %s, Account Number: %d", timestamp, name, accountNumber);

        System.out.println("Account created successfully! Your Account Number: " + accountNumber);
        System.out.println("Transaction Log: " + logMessage);

        User user = bankService.getUserByAccountNumber(accountNumber);
        if (user != null) { user.addTransactionLog(logMessage); }
    }

    private void depositAmount() {
        if (checkLoggedIn()) return;

        System.out.println("Enter amount to deposit: ");
        int amount = sc.nextInt();

        bankService.deposit(currentAccountNumber, amount);

        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] Deposit of ₹%d made to Account Number: %d", timestamp, amount, currentAccountNumber);

        System.out.println("Transaction Log: " + logMessage);

        User user = bankService.getUserByAccountNumber(currentAccountNumber);
        if (user != null) { user.addTransactionLog(logMessage); }
    }

    private void withdrawAmount() {
        if (checkLoggedIn()) return;

        System.out.println("Enter amount to withdraw: ");
        int amount = sc.nextInt();

        bankService.withdraw(currentAccountNumber, amount);

        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] Withdrawal of ₹%d made from Account Number: %d", timestamp, amount, currentAccountNumber);

        System.out.println("Transaction Log: " + logMessage);

        User user = bankService.getUserByAccountNumber(currentAccountNumber);
        if (user != null) { user.addTransactionLog(logMessage); }
    }

    private void showAccountSummary() {
        if (checkLoggedIn()) return;

        bankService.accountSummary(currentAccountNumber);

        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] Account summary viewed for Account Number: %d", timestamp, currentAccountNumber);

        User user = bankService.getUserByAccountNumber(currentAccountNumber);
        if (user != null) { user.addTransactionLog(logMessage); }
    }

    private void viewUserTransactionHistory() {
        if (checkLoggedIn()) return;

        User user = bankService.getUserByAccountNumber(currentAccountNumber);
        if (user == null) {
            System.out.println("Account not found.");
            return;
        }

        List<String> logs = user.getTransactionLogs();
        if (logs.isEmpty()) { System.out.println("No transactions recorded for this account."); }
        else {
            System.out.println("\nTransaction History for Account: " + currentAccountNumber);
            for (String log : logs) {
                System.out.println(log);
            }
        }
    }

    private boolean checkLoggedIn() {
        if (currentAccountNumber == null) {
            System.out.println("No account selected. Please create an account or login first.");
            return true;
        }
        return false;
    }
}
