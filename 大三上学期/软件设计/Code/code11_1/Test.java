package experiment11_1;

public class Test {
    public static void main(String[] args) {
        Headquarters headquarter = new Headquarters("Headquarters");

        Headquarters branch1 = new Headquarters("East China Branch");
        Headquarters branch2 = new Headquarters("South China Branch");

        Department department1 = new Department("Finance Department");
        Department department2 = new Department("Personnel Department");

        branch1.add(department1);
        branch1.add(department2);

        branch2.add(department1);
        branch2.add(department2);

        headquarter.add(department1);
        headquarter.add(department2);
        headquarter.add(branch1);
        headquarter.add(branch2);

        System.out.println("Notifying all units:");
        headquarter.inform(0);
    }
}
