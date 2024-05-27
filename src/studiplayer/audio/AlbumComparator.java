package studiplayer.audio;

import java.util.Comparator;

public class AlbumComparator implements Comparator<AudioFile> {
	@Override
	public int compare(AudioFile o1, AudioFile o2) {
		return o1.getAlbum().compareTo(o2.getAuthor());
	}
}
