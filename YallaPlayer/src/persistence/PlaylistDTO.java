package persistence;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class PlaylistDTO {
	private int playlistID;
	private ArrayList<Integer> songs = new ArrayList<Integer>();
	private String name;

	public PlaylistDTO() {
	}

	public PlaylistDTO(int playlistID, String name, ArrayList<Integer> songs) {
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

	@Override
	public String toString() {
		return name;
	}
	@XmlElement
	public void setPlaylistID(int playlistID) {
		this.playlistID = playlistID;
	}
	@XmlElement
	public void setSongs(ArrayList<Integer> songs) {
		this.songs = songs;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public void removeSong(int songID) {
		songs.remove(songs.indexOf(songID));
	}

}
