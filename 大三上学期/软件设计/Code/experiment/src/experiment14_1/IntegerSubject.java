package experiment14_1;

import java.util.ArrayList;
import java.util.List;

class IntegerSubject implements Subject{
    private int num;
    List<Observer> observerList = new ArrayList<Observer>();
    public void setNum(int num){
        this.num = num;
        notifyRegisteredUsers(num);
    }
    public int getNum(){
        return num;
    }

    @Override
    public void register(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unRegister(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyRegisteredUsers(int notifiedValue) {
        for(Observer observer: observerList){
            observer.update(notifiedValue);
        }
    }
}
