package Model;

public class Playlist extends ModelBase {
    private String Name;
    public Music[] Musics;
    private boolean IsPublic;
    private User Owner;

    public boolean AddMusic(Music music) {
        // Add music to playlist
        return true;
    }

    public boolean DeleteMusic(Music music) {
        // Remove music from playlist
        return true;
    }
}
