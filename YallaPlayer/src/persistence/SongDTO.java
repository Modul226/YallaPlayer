/**
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

	public SongDTO(int songID, int interpret, String name, String path) {
		this.songID = songID;
		this.interpret = interpret;
		this.name = name;
		this.path = path;
		this.playlist = -1;

	}

	public String getPath() {
		return path;
	}

	public int getSongID() {
		return songID;
	}

	public int getInterpret() {
		return interpret;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public int getPlaylist() {
		return playlist;
	}

	@XmlElement
	public void setPlaylist(int playlist) {
		this.playlist = playlist;
	}

	@XmlElement
	public void setSongID(int songID) {
		this.songID = songID;
	}

	@XmlElement
	public void setInterpret(int interpret) {
		this.interpret = interpret;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public void setPath(String path) {
		this.path = path;
	}

	public Object contains(String filter) {
		if (this.toString().matches("%" + filter + "%")) {
			return this;
		} else {
			return null;
		}
	}

}
