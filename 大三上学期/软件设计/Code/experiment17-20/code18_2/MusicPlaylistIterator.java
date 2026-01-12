package experiment18_2;

import java.util.ArrayList;
import java.util.List;

class MusicPlaylistIterator {
    private List<Track> trackList;
    private int position;
    public MusicPlaylistIterator(List<Track> trackList){
        this.trackList = trackList;
        position = 0;
    }
    public boolean hasNext(){
        if(position < trackList.size()){
            return true;
        }else{
            return false;
        }
    }
    public Track next(){
        return trackList.get(position++);
    }
    public void reset(){
        position = 0;
    }
}
