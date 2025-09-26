package banking.controller;

import banking.model.User;
import banking.service.BankService;
import banking.sessionmanager.SessionManager;
import banking.transactionlogger.TransactionLogger;

import java.util.List;
import java.util.Scanner;

public class Controller {

    private final BankService bankService;
    private final SessionManager sessionManager;
    private final TransactionLogger transactionLogger;
    private final Scanner sc;

    public Controller(BankService bankService, SessionManager sessionManager, TransactionLogger transactionLogger, Scanner scanner) {
        this.bankService = bankService;
        this.sessionManager = sessionManager;
        this.transactionLogger = transactionLogger;
        this.sc = scanner;
    }

    public void accountCreation() {
        System.out.print("Name: ");
        String name = sc.next();

        System.out.print("Age: ");
        int age = validateChoice();

        System.out.print("Gender (M/F): ");
        char gender = sc.next().charAt(0);

        long accountNumber = bankService.createUser(name, age, gender);
        User user = bankService.getUserByAccountNumber(accountNumber);

        transactionLogger.log(user, "Account created for user: " + name + ", Account Number: " + accountNumber);
        System.out.println("Account created successfully! Your Account Number: " + accountNumber);
    }

    public void depositAmount() {
        if (!checkLoggedIn()) return;

        System.out.print("Enter amount to deposit: ");
        int amount = sc.nextInt();

        Long accNum = sessionManager.getCurrentAccountNumber();
        bankService.deposit(accNum, amount);

        User user = bankService.getUserByAccountNumber(accNum);
        transactionLogger.log(user, "Deposit of ₹" + amount + " made to Account Number: " + accNum);
    }

    public void withdrawAmount() {
        if (!checkLoggedIn()) return;

        System.out.print("Enter amount to withdraw: ");
        int amount = sc.nextInt();

        Long accNum = sessionManager.getCurrentAccountNumber();
        bankService.withdraw(accNum, amount);

        User user = bankService.getUserByAccountNumber(accNum);
        transactionLogger.log(user, "Withdrawal of ₹" + amount + " made from Account Number: " + accNum);
    }

    public void showAccountSummary() {
        if (!checkLoggedIn()) return;

        Long accNum = sessionManager.getCurrentAccountNumber();
        bankService.accountSummary(accNum);

        User user = bankService.getUserByAccountNumber(accNum);
        transactionLogger.log(user, "Account summary viewed for Account Number: " + accNum);
    }

    public void viewUserTransactionHistory() {
        if (!checkLoggedIn()) return;

        Long accNum = sessionManager.getCurrentAccountNumber();
        User user = bankService.getUserByAccountNumber(accNum);

        if (user == null) {
            System.out.println("Account not found.");
            return;
        }

        List<String> logs = user.getTransactionLogs();
        if (logs.isEmpty()) {
            System.out.println("No transactions recorded for this account.");
        } else {
            System.out.println("\nTransaction History for Account: " + accNum);
            logs.forEach(System.out::println);
        }
    }

    private boolean checkLoggedIn() {
        if (!sessionManager.isLoggedIn()) {
            System.out.println("No account selected. Please create an account or login first.");
            return false;
        }
        return true;
    }

    private int validateChoice() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }
}

