package banking.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;
    private char gender;
    private final List<String> transactionLogs = new ArrayList<>();

    public User() {}

    public User(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() { return name; }

    public int getAge() { return age; }

    public char getGender() { return gender; }

    public void addTransactionLog(String log) { transactionLogs.add(log); }

    public List<String> getTransactionLogs() { return transactionLogs; }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
