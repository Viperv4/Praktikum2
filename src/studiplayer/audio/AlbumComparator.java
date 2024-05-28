package studiplayer.audio;

import java.util.Comparator;

public class AlbumComparator implements Comparator<AudioFile> {
	
	@Override
	public int compare(AudioFile o1, AudioFile o2) {
		try {
			TaggedFile o11 = (TaggedFile) o1;
			TaggedFile o21 = (TaggedFile) o2;
			return o11.getAlbum().compareTo(o21.getAlbum());
		} catch (Exception e) {
		}
		return -1;
	}
}