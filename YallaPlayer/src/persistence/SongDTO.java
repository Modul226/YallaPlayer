package persistence;

public class SongDTO {
	private int songID;
	private int interpret;
	private String name;
	private String path;

	// Is only used when in playlist will be inserted in GUIController.java
	private int playlist;

	public SongDTO(int songID, int interpret, String name, String path) {
		this.songID = songID;
		this.interpret = interpret;
		this.name = name;
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

	public void setPlaylist(int playlist) {
		this.playlist = playlist;
	}
}
