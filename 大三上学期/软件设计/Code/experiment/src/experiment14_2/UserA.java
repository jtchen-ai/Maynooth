package experiment14_2;

class UserA implements Observer{
    @Override
    public void update(String updateValue) {
        System.out.println("A watches " + updateValue);
    }
}