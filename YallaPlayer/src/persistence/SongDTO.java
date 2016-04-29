package persistence;

public class SongDTO {
	public int songID;
	public int interpret;
	public String name;

	public SongDTO(int songID, int interpret, String name) {
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
