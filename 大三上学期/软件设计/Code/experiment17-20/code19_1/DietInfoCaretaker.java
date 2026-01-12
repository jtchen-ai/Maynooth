package experiment19_1;

class DietInfoCaretaker {
    private DietInfo.DietInfoMemento memento;
    public void saveState(DietInfo dietInfo){
        memento = dietInfo.save();
    }
    public void restoreState(DietInfo dietInfo){
        dietInfo.restore(memento);
    }
}
