package experiment18_2;

class Track {
    private String title;
    private String artist;
    private String duration;
    public Track(String title, String artist, String duration){
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }
    public String getTitle(){
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDuration() {
        return duration;
    }
    @Override
    public String toString() {
        return "Track: " + title + ", Artist: " + artist + ", Duration: " + duration;
    }
}
