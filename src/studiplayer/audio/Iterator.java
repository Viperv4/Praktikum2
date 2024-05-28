package studiplayer.audio;

public interface Iterator {
	boolean hasNext();
	
	AudioFile next();
}