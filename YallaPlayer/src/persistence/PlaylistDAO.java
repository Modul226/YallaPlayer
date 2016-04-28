package persistence;

import java.util.ArrayList;

public interface PlaylistDAO {
	public void addNewPlaylist(String name, ArrayList<SongDTO> titles);

	public PlaylistDTO getAllPlaylists();
}
