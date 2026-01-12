package experiment3_1;

public class User {
    private String lastName;
    private String firstName;

    private String name = "";
    private int age = 0;
    private String telephoneNumber = "";

    public User(Builder builder) {
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.name = builder.name;
        this.age = builder.age;
        this.telephoneNumber = builder.telephoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}

