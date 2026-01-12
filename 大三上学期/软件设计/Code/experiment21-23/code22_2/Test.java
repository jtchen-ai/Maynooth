package experiment22_2;

public class Test {
    public static void main(String[] args) {
        ReviewPerson tester = new Tester();
        ReviewPerson cto = new CTO();
        ReviewPerson boss = new Boss();

        tester.setPerson(cto);
        cto.setPerson(boss);

        tester.handle("bug");
        tester.handle("no bug");
    }
}
