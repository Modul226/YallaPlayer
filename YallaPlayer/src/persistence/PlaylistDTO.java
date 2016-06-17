/**
 * DTO that contains a playlist
 * @author erflo
 */

package persistence;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class PlaylistDTO {
	private int playlistID;
	private ArrayList<Integer> songs = new ArrayList<Integer>();
	private String name;

	/**
	 * 
	 */
	public PlaylistDTO() {
	}

	/**
	 * @param playlistID
	 * @param name
	 * @param songs
	 */
	public PlaylistDTO(int playlistID, String name, ArrayList<Integer> songs) {
		this.playlistID = playlistID;
		this.songs = songs;
		this.name = name;
	}

	/**
	 * @return playlistID
	 */
	public int getPlaylistID() {
		return playlistID;
	}

	/**
	 * @return songs
	 */
	public ArrayList<Integer> getSongs() {
		return songs;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * @param playlistID
	 */
	@XmlElement
	public void setPlaylistID(int playlistID) {
		this.playlistID = playlistID;
	}

	/**
	 * @param songs
	 */
	@XmlElement
	public void setSongs(ArrayList<Integer> songs) {
		this.songs = songs;
	}

	/**
	 * @param name
	 */
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param songID
	 */
	public void removeSong(int songID) {
		songs.remove(songs.indexOf(songID));
	}

}
