package studiplayer.audio;

public class AudioFileFactory {
	public static AudioFile createAudioFile(String path) throws NotPlayableException {
		String[] path1 = path.split("\\.");
		String end = path1[path1.length - 1];
		end = end.toLowerCase();
		if (end.equals("mp3") || end.equals("ogg")) {
			return new TaggedFile(path);
		} else if (end.equals("wav")) {
			return new WavFile(path);
		} else {
			throw new NotPlayableException(path, "Unknown suffix for AudioFile \"" + path + "\"");
		}
	}
}