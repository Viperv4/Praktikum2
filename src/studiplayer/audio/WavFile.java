package studiplayer.audio;

import static studiplayer.basic.WavParamReader.*;

public class WavFile extends SampledFile {
	
	public WavFile() {
	}
	
	public WavFile(String path1) throws NotPlayableException {
		super(path1);
		readAndSetDurationFromFile();
	}
	
	public void readAndSetDurationFromFile() throws NotPlayableException {
		try {
			readParams(getPathname());
			float fps = getFrameRate();
			long fap = getNumberOfFrames();
			setDuration(computeDuration(fap, fps));
		} catch (Exception e) {
			throw new NotPlayableException(getPathname(), "");
		}
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


