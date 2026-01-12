package experiment14_1;

class BinaryObserver implements Observer{
    private String name;
    public BinaryObserver(String name){
        this.name = name;
    }
    @Override
    public void update(int updateValue) {
        System.out.println(name + " " + Integer.toBinaryString(updateValue));
    }
}
