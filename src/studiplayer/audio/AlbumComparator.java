package studiplayer.audio;

import java.util.Comparator;

public class AlbumComparator implements Comparator<AudioFile> {
	@Override
	public int compare(AudioFile o1, AudioFile o2) {
		TaggedFile o11 = null;
		TaggedFile o21 = null;
		WavFile o12 = null;
		WavFile o22 = null;
		int num1 = 0;
		int num2 = 0;
		if (o1.getClass() == TaggedFile.class) {
			o11 = (TaggedFile) o1;
			num1 = 1;
		} else {
			o12 = (WavFile) o1;
			num1 = 2;
		}
		
		if (o1.getClass() == TaggedFile.class) {
			o21 = (TaggedFile) o2;
			num2 = 1;
		} else {
			o22 = (WavFile) o2;
			num2 = 2;
		}
		if (num1 == 1 && num2 == 1) {
			return o11.getAlbum().compareTo(o21.getAlbum());
		}
//		  else if (num1 == 1 && num2 == 2) {
//			return o11.getAlbum().compareTo(o22.getAlbum());
//		} else if (num1 == 2 && num2 == 1) {
//			return o12.getAlbum().compareTo(o21.getAlbum());
//		} else if (num1 == 2 && num2 == 2) {
//			return o12.getAlbum().compareTo(o22.getAlbum());
//		}
		return 0;
	}
}