package persistence;

import java.util.ArrayList;

public class PlaylistDTO {
	public int playlistID;
	public ArrayList<Integer> songs = new ArrayList<Integer>();
	public String name;

	public PlaylistDTO(int playlistID, ArrayList<Integer> songs, String name) {
		this.playlistID = playlistID;
		this.songs = songs;
		this.name = name;
	}

	public int getPlaylistID() {
		return playlistID;
	}

	public ArrayList<Integer> getSongs() {
		return songs;
	}

	public String getName() {
		return name;
	}
}
