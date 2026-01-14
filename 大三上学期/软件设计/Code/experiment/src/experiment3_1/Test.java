package experiment3_1;

public class Test {
    public static void main(String[] args) {
        Builder userBuilder1 = new ComputerUser("Doe", "John");
        User user1 = userBuilder1.build();
        System.out.println(user1);

        Builder userBuilder2 = new ComputerUser("Brown", "Charlie");
        User user2 = userBuilder2
                .setAge(12)
                .setTelephoneNumber("18527463390")
                .setName("Charlie Brown")
                .build();

        System.out.println(user2);


    }
}
