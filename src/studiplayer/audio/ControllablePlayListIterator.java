package studiplayer.audio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ControllablePlayListIterator implements Iterator {
	public List<AudioFile> list;
	public int in = 0;
	private boolean first = true;
	
	public ControllablePlayListIterator(List<AudioFile> list) {
		this.list = list;
	}
	
	public ControllablePlayListIterator(List<AudioFile> list2, String search, SortCriterion sortCriterion) {
		list = new ArrayList<>();
		if (search != null) {
			for (AudioFile f : list2) {
				if (f.getTitle().contains(search) || f.getAuthor().contains(search)) {
					list.add(f);
				}
				try {
					TaggedFile f1 = (TaggedFile) f;
					if (f1.getAlbum().contains(search)) {
						if (!list.contains(f)) {
							list.add(f);
						}
					}
				} catch (Exception e) {
				}
			}
		} else {
			list.addAll(list2);
		}
		
		if (list != null) {
			if (sortCriterion == SortCriterion.DURATION) {
				list.sort(new DurationComparator());
			} else if (sortCriterion == SortCriterion.ALBUM) {
				list.sort(new AlbumComparator());
			} else if (sortCriterion == SortCriterion.AUTHOR) {
				list.sort(new AuthorComparator());
			} else if (sortCriterion == SortCriterion.TITLE) {
				list.sort(new TitleComparator());
			}
		}
	}
	
	public AudioFile jumpToAudioFile(AudioFile audioFile) {
		in = list.indexOf(audioFile);
		return audioFile;
	}
	
	@Override
	public boolean hasNext() {
		return !(list.size() == in + 1);
	}
	
	@Override
	public AudioFile next() {
		if (!(list.isEmpty())) {
			if (first) {
				first = false;
				return list.get(in);
			} else {
				if (!hasNext()) {
					in = 0;
					return list.get(in);
				} else {
					in++;
					return list.get(in);
				}
			}
		} else {
			return null;
		}
	}
}