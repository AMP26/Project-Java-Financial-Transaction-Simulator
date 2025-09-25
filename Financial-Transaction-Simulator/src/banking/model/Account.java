package banking.model;

public class Account {
    private long accountNumber;
    private double balance;
    private User user;

    public Account(long accountNumber, User user) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.user = user;
    }

    public void deposit(double balance) {
        this.balance += balance;
    }
}
