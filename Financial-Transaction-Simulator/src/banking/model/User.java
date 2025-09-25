package banking.model;

public class User {
    private String name;
    private int age;
    private char gender;

    public User(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public char getGender() { return gender; }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
