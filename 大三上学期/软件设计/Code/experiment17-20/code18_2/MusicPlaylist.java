package experiment18_2;

import java.util.ArrayList;
import java.util.List;

class MusicPlaylist {
    private List<Track> trackList;
    public MusicPlaylist(){
        this.trackList = new ArrayList<Track>();
    }
    public void addTrack(Track track){
        trackList.add(track);
    }
    public MusicPlaylistIterator getIterator(){
        return new MusicPlaylistIterator(trackList);
    }
}
