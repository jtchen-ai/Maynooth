package experiment18_2;

public class PlaylistTester {
    public static void main(String[] args) {
        MusicPlaylist musicPlaylist = new MusicPlaylist();
        Track track1 = new Track("蜃楼", "周深", "3'46''");
        Track track2 = new Track("嗨", "周深", "4'00''");
        Track track3 = new Track("虚构", "周深", "5'56''");
        musicPlaylist.addTrack(track1);
        musicPlaylist.addTrack(track2);
        musicPlaylist.addTrack(track3);

        MusicPlaylistIterator iterator = musicPlaylist.getIterator();
        while (iterator.hasNext()){
            Track currentTrack = iterator.next();
            System.out.println("Playing: " + currentTrack);
        }
        iterator.reset();
        System.out.println("Reset playlist.");
        System.out.println("Now playing: " + iterator.next());
    }
}
