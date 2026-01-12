package experiment5_2;

public class SummerSkinFactory implements SkinFactory{
    @Override
    public Buttons createButtons() {
        return new BlueButtons();
    }

    @Override
    public ComboBoxes createComboBoxes() {
        return new BlueComboBoxes();
    }

    @Override
    public TextBoxes createTextBoxes() {
        return new BlueTextBoxes();
    }
}
