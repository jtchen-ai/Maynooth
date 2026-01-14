package experiment19_1;

public class Test {
    public static void main(String[] args) {
        DietInfoCaretaker dietInfoCaretaker = new DietInfoCaretaker();

        DietInfoCaretaker dietInfoCaretaker1 = new DietInfoCaretaker();
        DietInfo dietInfo = new DietInfo("John Doe", 30, 75.5);
        System.out.println("Initial state:");
        System.out.println("Person: " + dietInfo.getPersonName() + ", Days: " + dietInfo.getDays() + ", Weight: " + dietInfo.getWeight());

        dietInfoCaretaker.saveState(dietInfo);

        dietInfo.setDays(25);
        System.out.println("\nState after changes:");
        dietInfoCaretaker1.saveState(dietInfo);
        System.out.println("Person: " + dietInfo.getPersonName() + ", Days: " + dietInfo.getDays() + ", Weight: " + dietInfo.getWeight());

        dietInfoCaretaker.restoreState(dietInfo);
        System.out.println("\nState after restoration:");
        System.out.println("Person: " + dietInfo.getPersonName() + ", Days: " + dietInfo.getDays() + ", Weight: " + dietInfo.getWeight());

        dietInfoCaretaker1.restoreState(dietInfo);
        System.out.println("\nState after restoration:");
        System.out.println("Person: " + dietInfo.getPersonName() + ", Days: " + dietInfo.getDays() + ", Weight: " + dietInfo.getWeight());
    }
}
