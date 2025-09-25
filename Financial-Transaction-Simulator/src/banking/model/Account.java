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

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if(amount < 0 || amount > balance) {
            System.out.println("Invalid amount");
        }
        else { this.balance -= amount; }
    }
}
