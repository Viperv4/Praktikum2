package studiplayer.audio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PlayList implements Iterable {
	private LinkedList<AudioFile> list = new LinkedList<>();
	private int current;
	
	public PlayList() {
	}
	
	public PlayList(String m3uPathname) {
		loadFromM3U(m3uPathname);
	}
	
	public void loadFromM3U(String pathname) {
		Scanner scanner = null;
		list = new LinkedList<>();
		current = 0;
		
		try {
			// open the file for reading
			scanner = new Scanner(new File(pathname));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (!(line.charAt(0) == '#' || line.isBlank())) {
					list.add(AudioFileFactory.createAudioFile(line));
				}
			}
		} catch (Exception e) {
			new NotPlayableException(pathname, "");
		} finally {
			try {
				scanner.close();
			} catch (Exception e) {
			}
		}
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
	
	public void saveAsM3U(String pathname) {
		FileWriter w = null;
		String sep = System.getProperty("line.separator");
		try {
			w = new FileWriter(pathname);
			for (Object file : list) {
				String p = ((AudioFile) file).getPathname();
				w.write(p + sep);
			}
		} catch (IOException e) {
			throw new RuntimeException("Unable to write file " + pathname + "!");
		} finally {
			try {
				w.close();
			} catch (IOException e) {
			}
		}
		
	}
	
	public List<AudioFile> getList() {
		return list;
	}
	
	private enum SortCriterion {
		DEFAULT, AUTHOR, TITLE, ALBUM, DURATION
	}
	
	
}


//Die Flippers: 40 Jahre die Flippers

//Wir sagen danke schön, 40 Jahre "Die Flippers"
//Was wär'n wir ohne unsre Freunde, ohne euch, die lieben Fans

//Es ist schon ziemlich lang her, da fing alles einmal an
//Keiner ahnte damals, was da für uns begann
//Ja, es war'n oft wunderschöne Zeiten
//Und so viel Erinnerungen bleiben
//Unser Traum wurde Wirklichkeit

//Wir sagen danke schön, 40 Jahre "Die Flippers"
//Und dass wir heute wieder hier sind, das verdanken wir nur euch
//Wir glauben's selber kaum, 40 Jahre "Die Flippers"
//Was wär'n wir ohne unsre Freunde, ohne euch, die lieben Fans

//"Weine nicht kleine Eva", mit dem Lied ging's richtig los
//Und die rote Sonne, die schien auf Barbados
//"Mexico", "Trinidad" und "Roma"
//"St. Tropez" und "Alles La Paloma"
//Liebe ist, wenn man sich zärtlich küsst

//Wir sagen danke schön, 40 Jahre "Die Flippers"
//Und dass wir heute wieder hier sind, das verdanken wir nur euch
//Wir glauben's selber kaum, 40 Jahre "Die Flippers"
//Was wär'n wir ohne unsre Freunde, ohne euch, die lieben Fans