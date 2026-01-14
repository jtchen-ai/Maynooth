package experiment15_2;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        SportsMan sportsMan = new SportsMan();
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a ball to play:");
        System.out.println("1: Football");
        System.out.println("2: Basketball");
        int input = sc.nextInt();
        switch (input){
            case 1:
                sportsMan.setBall(new Football());
                break;
            case 2:
                sportsMan.setBall(new Basketball());
                break;
            default:
                System.out.println("Invalid input. No ball selected.");
                return;
        }
        sportsMan.show();
    }
}
