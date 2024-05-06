import studiplayer.basic.TagReader;

import java.util.Map;

public class TaggedFile extends SampledFile {
    private String album;

    public TaggedFile() {
    }

    public TaggedFile(String path) {
        super(path);
        readAndStoreTags();
    }

    public String getAlbum() {
        return album;
    }

    public void readAndStoreTags() {
        String author;
        String title;
        long duration;
        Map<String, Object> tags = TagReader.readTags(getPathname());
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
