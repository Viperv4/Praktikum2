package studiplayer.audio;

import studiplayer.basic.BasicPlayer;

import static studiplayer.basic.BasicPlayer.getPosition;

public abstract class SampledFile extends AudioFile {
	
	BasicPlayer player = new studiplayer.basic.BasicPlayer();
	private String pathname;
	private long duration;
	
	public SampledFile() {
	}
	
	public SampledFile(String path) {
		super(path);
		pathname = path;
	}
	
	public void play() throws NotPlayableException {
		try {
			
			player.play(pathname);
			
		} catch (Exception e) {
			throw new NotPlayableException(pathname, "play");
		}
	}
	
	public void togglePause() {
		player.togglePause();
	}
	
	public void stop() {
		player.stop();
	}
	
	public String formatDuration() {
		return timeFormatter(getDuration());
	}
	
	public String formatPosition() {
		return timeFormatter(getPosition());
	}
	
	public static String timeFormatter(long timeInMicroSeconds) {
		long fD = timeInMicroSeconds;
		String min1;
		String sek1;
		if (fD < 0) {
			throw new RuntimeException("Chef, Laufzeit ist negativ");
		}
		fD = fD / 1000000;
		if (fD >= 6000) {
			throw new RuntimeException("Chef, Laufzeit zu lang");
		}
		int min = (int) (fD / 60);
		int sek = (int) (fD - min * 60);
		if (min < 10) {
			min1 = "0" + String.valueOf(min);
		} else {
			min1 = String.valueOf(min);
		}
		if (sek < 10) {
			sek1 = "0" + String.valueOf(sek);
		} else {
			sek1 = String.valueOf(sek);
		}
		return min1 + ":" + sek1;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public void setDuration(long e) {
		duration = e;
	}
}


//Udo Jürgens: Griechischer Wein

//Es war schon dunkel
//Als ich durch Vorstadtstraßen heimwärts ging
//Da war ein Wirtshaus
//Aus dem das Licht noch auf den Gehsteig schien
//Ich hatte Zeit und mir war kalt, drum trat ich ein

//Da saßen Männer mit braunen
//Augen und mit schwarzem Haar
//Und aus der Jukebox erklang Musik
//Die fremd und südlich war
//Als man mich sah
//Stand einer auf und lud mich ein

//Griechischer Wein ist
//So wie das Blut der Erde
//Komm', schenk dir ein
//Und wenn ich dann traurig werde
//Liegt es daran
//Dass ich immer träume von daheim
//Du musst verzeihen

//Griechischer Wein
//Und die altvertrauten Lieder
//Schenk' nochmal ein
//Denn ich fühl' die Sehnsucht
//Wieder, in dieser Stadt
//Werd' ich immer nur ein Fremder sein, und allein

//Und dann erzählten sie mir von grünen Hügeln, Meer und Wind
//Von alten Häusern und jungen Frauen, die alleine sind
//Und von dem Kind das seinen Vater noch nie sah

//Sie sagten sich immer wieder
//Irgendwann geht es zurück
//Und das Ersparte genügt zu
//Hause für ein kleines Glück
//Und bald denkt keiner mehr daran
//Wie es hier war

//Griechischer Wein ist
//So wie das Blut der Erde
//Komm', schenk dir ein
//Und wenn ich dann traurig werde
//Liegt es daran
//Dass ich immer träume von daheim
//Du musst verzeihen

//Griechischer Wein
//Und die altvertrauten Lieder
//Schenk' nochmal ein,
//Denn ich fühl' die Sehnsucht
//Wieder, in dieser Stadt
//Werd' ich immer nur ein Fremder sein, und allein