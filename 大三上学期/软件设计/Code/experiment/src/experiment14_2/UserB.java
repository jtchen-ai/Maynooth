package experiment14_2;

class UserB implements Observer{
    @Override
    public void update(String updateValue) {
        System.out.println("B watches " + updateValue);
    }
}