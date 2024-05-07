import java.util.LinkedList;
import java.util.List;

public class PlayList {
    private LinkedList list = new LinkedList<>();

    public PlayList() {
    }

    public PlayList(String m3uPathname) {
    }

    public void add(AudioFile file) {
        list.add(file);
    }

    public void remove(AudioFile file) {
        list.remove(file);
    }

    public int size() {
        return list.toArray().length;
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
        return list;
    }
}
