package persistence;

public class SongDTO {
	public int songID;
	public int interpret;
	public String name;
	public String path;

	public SongDTO(int songID, int interpret, String name, String path) {
		this.songID = songID;
		this.interpret = interpret;
		this.name = name;

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
}
