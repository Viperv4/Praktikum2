import static studiplayer.basic.WavParamReader.*;

public class WavFile extends SampledFile {
	
	public WavFile() {
	}
	
	public WavFile(String path1) {
		super(path1);
		readAndSetDurationFromFile();
	}
	
	public void readAndSetDurationFromFile() {
		readParams(getPathname());
		float fps = getFrameRate();
		long fap = getNumberOfFrames();
		setDuration(computeDuration(fap, fps));
	}
	
	public static long computeDuration(long numberOfFrames, float frameRate) {
		long tipa = 0;
		tipa = (long) ((numberOfFrames / frameRate) * 1000000);
		return tipa;
	}
	
	public String toString() {
		if (getAuthor() == "") {
			return getTitle() + " - " + formatDuration();
		} else {
			return getAuthor() + " - " + getTitle() + " - " + formatDuration();
		}
	}
}


