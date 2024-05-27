package studiplayer.audio;

import java.util.List;

public class ControllablePlayListIterator implements Iterator {
	public ControllablePlayListIterator(List<AudioFile> list) {
	}
	
	public AudioFile jumpToAudioFile(AudioFile audioFile) {
		return audioFile;
	}
	
	@Override
	public boolean hasNext() {
		return false;
	}
	
	@Override
	public AudioFile next() {
		return null;
	}
}
