package experiment3_2;

public class Test {
    public static void main(String[] args) {
        Builder humanBuilder = new Builder();
        Human human = humanBuilder
                .addhead("He has a head")
                .addbody("He has a body")
                .addhand("He has two hands")
                .addfoot("He has two feet")
                .build();
        System.out.println(human.toString());

    }
}
