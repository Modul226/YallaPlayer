package business;

import java.util.ArrayList;

import persistence.PlaylistDAO;
import persistence.PlaylistDTO;
import persistence.XMLAccess;


public class Playlist {
	private PlaylistDAO playlistDAO = new XMLAccess();

	public void addPlaylist(String name, ArrayList<Integer> titles) {
		playlistDAO.addPlaylist(name, titles);
	}
	
	public void playPlaylist(PlaylistDTO playlist) {

	}

	public void stopPlaylist(PlaylistDTO playlist) {

	}

}
