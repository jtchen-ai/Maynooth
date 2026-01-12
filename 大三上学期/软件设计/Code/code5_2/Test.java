package experiment5_2;

public class Test {
    public static void main(String[] args) {
        Buttons buttons1, buttons2;
        ComboBoxes comboBoxes1, comboBoxes2;
        TextBoxes textBoxes1, textBoxes2;
        SkinFactory skinFactory1, skinFactory2;

        System.out.println("Spring Style");
        skinFactory1 = new SpringSkinFactory();
        buttons1 = skinFactory1.createButtons();
        buttons1.produceButtons();
        comboBoxes1 = skinFactory1.createComboBoxes();
        comboBoxes1.produceComboBoxes();
        textBoxes1 = skinFactory1.createTextBoxes();
        textBoxes1.produceTextBoxes();

        System.out.println();

        System.out.println("Summer Style");
        skinFactory2 = new SummerSkinFactory();
        buttons2 = skinFactory2.createButtons();
        buttons2.produceButtons();
        comboBoxes2 = skinFactory2.createComboBoxes();
        comboBoxes2.produceComboBoxes();
        textBoxes2 = skinFactory2.createTextBoxes();
        textBoxes2.produceTextBoxes();

    }
}
