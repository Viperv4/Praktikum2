package studiplayer.audio;

import java.util.List;

public class ControllablePlayListIterator implements Iterator {
	public List<AudioFile> list;
	public int in = -1;
	
	public ControllablePlayListIterator(List<AudioFile> list) {
		this.list = list;
	}
	
	public AudioFile jumpToAudioFile(AudioFile audioFile) {
		return audioFile;
	}
	
	@Override
	public boolean hasNext() {
		return !(list.size() == in + 1);
	}
	
	@Override
	public AudioFile next() {
		in++;
		return list.get(in);
	}
}
