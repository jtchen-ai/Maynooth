package experiment14_2;

import experiment3_1.User;

public class Test {
    public static void main(String[] args) {
        Up up = new Up();
        Observer a = new UserA();
        Observer b = new UserB();
        up.register(a);
        up.register(b);
        up.notifyRegisteredUsers("Learn Java Observer Pattern");
        System.out.println();
        up.unRegister(a);
        up.notifyRegisteredUsers("Advanced Java Programming");
    }
}