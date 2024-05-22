package studiplayer.audio;

public class NotPlayableException extends Exception {
	private String path;
	
	NotPlayableException(String pathname, String msg) {
		super(msg);
		path = pathname;
	}
	
	NotPlayableException(String pathname, Throwable t) {
		super(t);
		path = pathname;
	}
	
	NotPlayableException(String pathname, String msg, Throwable t) {
		super(msg, t);
		path = pathname;
	}
}
