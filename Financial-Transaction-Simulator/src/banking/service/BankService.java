package banking.service;

import banking.model.Account;
import banking.model.User;

import java.util.HashMap;
import java.util.Map;

public class BankService {
    Map<Long, Account> accounts = new HashMap<>();
    private long nextAccountNumber = 10000L;

    public long createUser(String name, int age, char gender) {
        User user = new User(name, age, gender);
        long accountNumber = nextAccountNumber++;

        Account account = new Account(accountNumber, user);
        accounts.put(accountNumber, account);

        return accountNumber;
    }

    public void deposit(long accountNumber, long amount) {
        Account account = accounts.get(accountNumber);
        if(amount < 0) {
            System.out.println("Invalid amount");
            return;
        }
        else {
            account.deposit(amount);
            System.out.println("Deposited " + amount + " to account " + accountNumber);
        }
    }
}