package experiment14_1;

class HexaObserver implements Observer{
    private String name;
    public HexaObserver(String name){
        this.name = name;
    }
    @Override
    public void update(int updateValue) {
        System.out.println(name + " " + Integer.toHexString(updateValue));
    }
}
