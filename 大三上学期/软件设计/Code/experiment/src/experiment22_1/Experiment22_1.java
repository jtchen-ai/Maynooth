package experiment22_1;

public class Experiment22_1 {
    public static void main(String[] args) {
        GroupLeader leader = new GroupLeader();
        Manager manager = new Manager();
        CFO cfo = new CFO();

        leader.setNextHandler(manager);
        manager.setNextHandler(cfo);

        System.out.println(String.format("I needs to buy a Mac laptop with a budget of: %d", 11000));
        if (leader.handle(11000)) {
            System.out.println("thanks");
        } else {
            System.out.println("unfortunately");
        }
    }
}
