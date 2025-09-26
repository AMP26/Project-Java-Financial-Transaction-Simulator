package banking.service;

import banking.model.Account;
import banking.model.User;

import java.util.HashMap;
import java.util.Map;

public class BankService {
    Map<Long, Account> accounts = new HashMap<>();
    private long nextAccountNumber = 100000L;

    public long createUser(String name, int age, char gender) {
        User user = new User(name, age, gender);
        long accountNumber = nextAccountNumber++;

        Account account = new Account(accountNumber, user);
        accounts.put(accountNumber, account);

        return accountNumber;
    }

    public void deposit(long accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        else if(amount < 0) {
            System.out.println("Invalid amount");
            return;
        }
        else {
            account.deposit(amount);
            System.out.println("Deposited " + amount + " to account " + accountNumber);
        }
    }

    public void withdraw(long accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        account.withdraw(amount);

        System.out.println(amount + " has been debited from account " + accountNumber);
    }

    public void accountSummary(long accountNumber) {
        Account account = accounts.get(accountNumber);
        if(account == null) {
            System.out.println("Account " + accountNumber + " not found");
            return;
        }

        System.out.println(account);
    }

    public User getUserByAccountNumber(long accountNumber) {
        Account account = accounts.get(accountNumber);

        if (account != null) { return account.getUser(); }

        return null;
    }
}