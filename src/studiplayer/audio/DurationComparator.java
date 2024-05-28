package studiplayer.audio;

import java.util.Comparator;

public class DurationComparator implements Comparator<AudioFile> {
	@Override
	public int compare(AudioFile o1, AudioFile o2) {
		try {
			SampledFile o11 = (SampledFile) o1;
			SampledFile o21 = (SampledFile) o2;
			if (o11.getDuration() < o21.getDuration()) {
				return -1;
			} else if (o11.getDuration() == o21.getDuration()) {
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
		}
		return -1;
	}
}