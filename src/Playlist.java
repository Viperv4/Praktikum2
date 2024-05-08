import java.util.LinkedList;
import java.util.List;

public class PlayList {
	private LinkedList list = new LinkedList<>();
	private int current;
	
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
		return current;
	}
	
	public void setCurrent(int current) {
		this.current = current;
	}
	
	public AudioFile currentAudioFile() {
		try {
			return (AudioFile) list.get(current);
		} catch (Exception e) {
			return null;
		}
	}
	
	public void nextSong() {
		current++;
		if (current >= list.toArray().length || current < 0) {
			current = 0;
		}
	}
	
	public void loadFromM3U(String pathname) {
	}
	
	public void saveAsM3U(String pathname) {
	}
	
	public List<AudioFile> getList() {
		return list;
	}
}
