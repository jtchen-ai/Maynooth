package experiment3_1;

public class Builder {

    protected String lastName;
    protected String firstName;


    protected String name = "";
    protected int age = 0;
    protected String telephoneNumber = "";


    public Builder(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }


    public Builder setName(String name) {
        this.name = name;
        return this;
    }

    public Builder setAge(int age) {
        this.age = age;
        return this;
    }

    public Builder setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
        return this;
    }


    public User build() {
        return new User(this);
    }
}
