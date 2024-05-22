package studiplayer.audio;

import studiplayer.basic.TagReader;

import java.util.Map;

public class TaggedFile extends SampledFile {
	private String album;
	
	public TaggedFile() {
	}
	
	public TaggedFile(String path) throws NotPlayableException {
		super(path);
		readAndStoreTags();
	}
	
	public void readAndStoreTags() throws NotPlayableException {
		String author;
		String title;
		long duration;
		Map<String, Object> tags;
		try {
			tags = TagReader.readTags(getPathname());
		} catch (Exception e) {
			throw new NotPlayableException(getPathname(), "");
		}
		title = (String) tags.get("title");
		author = (String) tags.get("author");
		album = (String) tags.get("album");
		duration = (long) tags.get("duration");
		if (author != null) {
			author = author.trim();
		}
		if (title != null) {
			title = title.trim();
		}
		if (album != null) {
			album = album.trim();
		}
		setAuthor(author);
		setTitle(title);
		setDuration(duration);
	}
	
	public String getAlbum() {
		return album;
	}
	
	public String toString() {
		if (album == null) {
			if (getAuthor() == "") {
				return getTitle() + " - " + formatDuration();
			} else {
				return getAuthor() + " - " + getTitle() + " - " + formatDuration();
			}
		} else {
			if (getAuthor() == "") {
				return getTitle() + " - " + album + " - " + formatDuration();
			} else {
				return getAuthor() + " - " + getTitle() + " - " + album + " - " + formatDuration();
			}
		}
	}
}


//Die B-migos: In meiner Hose wohnt ein Iltis

//Als ich einer junger Knabe war
//Am Schopfe blond, am Sack kein Haar
//In meinem Zimmer meist allein
//Kein Mädchen ließ mich zu sich rein
//Doch eines Tages wurde ich gewahr
//Dass unten etwas unruhig war
//In meinem Beinkleid wurd es laut
//Drum hab ich einmal nachgeschaut
//Und ich sah...

//In meiner Hose wohnt ein Iltis
//Ich hab ihn lang nicht mehr gesehn
//Doch manchmal höre ich ihn knabbern
//Und mir auf die Eier gehn
//In meiner Hose wohnt ein Iltis
//Er ist so still wie eine Maus
//Doch manchmal ist er kaum zu halten
//Und dann lasse ich ihn raus

//Mit 37 war's soweit
//Ich traf ein Mädchen, groß und breit
//Sie war nicht hübsch, doch auch allein
//Drum wollten wir zusammen sein
//Sie zog zu mir und dann sich aus
//Und ich gestand ihr frei heraus
//Du musst es wissen, liebes Kind
//Dass wir nicht ganz alleine sind
//Denn schau mal

//In meiner Hose wohnt ein Iltis
//Ich hab ihn lang nicht mehr gesehn
//Doch manchmal höre ich ihn knabbern
//Und mir auf die Eier gehn
//In meiner Hose wohnt ein Iltis
//Mal liegt er rum mal muss er stehn
//Doch wenn Besuch kommt wird er unruhig
//Und schleicht sich raus, um was zu sehen

//Oh ja und als meine Frau mich dann verließ
//Da war der Iltis mein einziger und treuster Freund
//Ich hatte sehr oft feuchte Hosen
//Ich glaub er hat um mich geweint

//In meiner Hose wohnt ein Iltis
//Ich hab ihn lang nicht mehr gesehn
//Doch manchmal höre ich ihn knabbern
//Und mir auf die Eier gehn
//In meiner Hose wohnt ein Iltis
//Er ist schon alt und kann schlecht sehn
//Hat nur ein Auge und ne Glatze
//Doch ich find ihn wunderschön