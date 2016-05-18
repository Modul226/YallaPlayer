package persistence;

import java.util.ArrayList;

public interface PlaylistDAO {
	public void addPlaylist(String name, ArrayList<Integer> titles);
}
