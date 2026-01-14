package experiment5_2;

public class SpringSkinFactory implements SkinFactory{
    @Override
    public Buttons createButtons() {
        return new GreenButtons();
    }

    @Override
    public ComboBoxes createComboBoxes() {
        return new GreenComboBoxes();
    }

    @Override
    public TextBoxes createTextBoxes() {
        return new GreenTextBoxes();
    }
}
