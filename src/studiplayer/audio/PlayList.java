package studiplayer.audio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PlayList implements Iterable<AudioFile> {
	private LinkedList<AudioFile> list = new LinkedList<>();
	private String search;
	private SortCriterion sortCriterion;
	private studiplayer.audio.ControllablePlayListIterator it;
	private AudioFile curr;
	
	public PlayList() {
		sortCriterion = SortCriterion.DEFAULT;
	}
	
	public PlayList(String m3uPathname) {
		loadFromM3U(m3uPathname);
		sortCriterion = SortCriterion.DEFAULT;
	}
	
	public void loadFromM3U(String pathname) {
		Scanner scanner = null;
		list = new LinkedList<>();
		
		try {
			// open the file for reading
			scanner = new Scanner(new File(pathname));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (!(line.charAt(0) == '#' || line.isBlank())) {
					try {
						list.add(AudioFileFactory.createAudioFile(line));
					} catch (NotPlayableException e) {
					}
				}
			}
		} catch (Exception e) {
			try {
				throw new NotPlayableException(pathname, e);
			} catch (NotPlayableException ex) {
				throw new RuntimeException(ex);
			}
//			throw new RuntimeException(e);
		} finally {
			try {
				scanner.close();
			} catch (Exception e) {
			}
		}
		it = new ControllablePlayListIterator(list);
	}
	
	public void add(AudioFile file) {
		list.add(file);
		
		it = new ControllablePlayListIterator(list, search, sortCriterion);
		it.next();
	}
	
	public void remove(AudioFile file) {
		list.remove(file);
	}
	
	public int size() {
		return list.toArray().length;
	}
	
	public AudioFile currentAudioFile() {
		if (!list.isEmpty()) {
			if (curr != null) {
				return curr;
			} else {
				while (it.hasNext()) {
					curr = it.next();
				}
				curr = it.next();
				return curr;
			}
		} else {
			return null;
		}
	}
	
	public void nextSong() {
		if (it != null) {
			curr = it.next();
		}
	}
	
	public void jumpToAudioFile(AudioFile tf2) {
		curr = it.jumpToAudioFile(tf2);
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
	
	public String getSearch() {
		return search;
	}
	
	public void setSearch(String e) {
		search = e;
		it = new ControllablePlayListIterator(list, search, sortCriterion);
		curr = it.next();
	}
	
	public SortCriterion getSortCriterion() {
		return sortCriterion;
	}
	
	public void setSortCriterion(SortCriterion e) {
		sortCriterion = e;
		it = new ControllablePlayListIterator(list, search, sortCriterion);
		curr = it.next();
	}
	
	public Iterator<AudioFile> iterator() {
		it = new ControllablePlayListIterator(list, search, sortCriterion);
		return it;
	}
	
	@Override
	public String toString() {
		String str = "[";
		for (AudioFile af : list) {
			if (str == "[") {
				str = str + af.toString();
			} else {
				str = str + ", " + af.toString();
			}
		}
		return str + "]";
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