package experiment14_2;

import java.util.ArrayList;
import java.util.List;

class Up implements Subject{
    private String videos;
    List<Observer> observerList = new ArrayList<Observer>();
    public void setVideos(String videos){
        this.videos = videos;
        notifyRegisteredUsers(videos);
    }
    public String getVideos(){
        return videos;
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
    public void notifyRegisteredUsers(String notifiedValue) {
        for(Observer observer: observerList){
            observer.update(notifiedValue);
        }
    }
}