/**
 * DTO that contains a song
 * @author erflo
 */

package persistence;

import javax.xml.bind.annotation.XmlElement;

public class SongDTO {
	private int songID;
	private int interpret;
	private String name;
	private String path;

	// Is only used when in playlist will be inserted in GUIController.java
	private int playlist;


	public SongDTO() {
	}

	/**
	 * @param songID
	 * @param interpret
	 * @param name
	 * @param path
	 */
	public SongDTO(int songID, int interpret, String name, String path) {
		this.songID = songID;
		this.interpret = interpret;
		this.name = name;
		this.path = path;
		this.playlist = -1;

	}

	/**
	 * @return Returns path of the MP3
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return Returns songID of the Song
	 */
	public int getSongID() {
		return songID;
	}

	/**
	 * @return Returns interpret of the Song
	 */
	public int getInterpret() {
		return interpret;
	}

	/**
	 * @return Returns name of the Song
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gives back text shown in the ListViews
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * @return Returns playlistID of the Song
	 */
	public int getPlaylist() {
		return playlist;
	}

	/**
	 * @param playlist
	 */
	@XmlElement
	public void setPlaylist(int playlist) {
		this.playlist = playlist;
	}

	/**
	 * @param songID
	 */
	@XmlElement
	public void setSongID(int songID) {
		this.songID = songID;
	}

	/**
	 * @param interpret
	 */
	@XmlElement
	public void setInterpret(int interpret) {
		this.interpret = interpret;
	}

	/**
	 * @param name
	 */
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param path
	 */
	@XmlElement
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Is used for the search functionality
	 * 
	 * @param filter
	 * @return this
	 */
	public Object contains(String filter) {
		if (this.toString().trim().matches("%" + filter + "%")) {
			return this;
		} else {
			return null;
		}
	}

}
