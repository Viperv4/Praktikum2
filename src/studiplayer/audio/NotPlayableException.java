package studiplayer.audio;

public class NotPlayableException extends Exception {
	private String path;
	
	public NotPlayableException(String pathname, String msg) {
		super(msg);
		path = pathname;
	}
	
	public NotPlayableException(String pathname, Throwable t) {
		super(t);
		path = pathname;
	}
	
	public NotPlayableException(String pathname, String msg, Throwable t) {
		super(msg, t);
		path = pathname;
	}
}
