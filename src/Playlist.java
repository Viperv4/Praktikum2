import java.util.List;

public class PlayList {
    public PlayList() {
    }

    public PlayList(String m3uPathname) {
    }

    public void add(AudioFile file) {
    }

    public void remove(AudioFile file) {
    }

    public int size() {
        return 0;
    }

    public int getCurrent() {
        return 0;
    }

    public void setCurrent(int current) {
    }

    public AudioFile currentAudioFile() {
        return null;
    }

    public void nextSong() {
    }

    public void loadFromM3U(String pathname) {
    }

    public void saveAsM3U(String pathname) {
    }

    public List<AudioFile> getList() {
        return List.of();
    }
}
