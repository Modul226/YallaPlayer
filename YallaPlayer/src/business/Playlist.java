package business;

import java.util.ArrayList;

import persistence.PlaylistDAO;
import persistence.PlaylistDTO;
import persistence.SongDTO;
import persistence.XMLAccess;


public class Playlist {
	private PlaylistDAO playlistDAO = new XMLAccess();

	public void add(String name, ArrayList<SongDTO> titles) {
		playlistDAO.addNewPlaylist(name, titles);
	}

	public PlaylistDTO getAllPlaylists() {
		return playlistDAO.getAllPlaylists();
	}

	public void playPlaylist(PlaylistDTO playlist) {
		
	}

	public void stopPlaylist(PlaylistDTO playlist) {

	}

}
