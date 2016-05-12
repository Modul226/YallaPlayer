package persistence;

import java.util.ArrayList;

public class AlbumDTO {
	public int albumID;
	public String name;
	public ArrayList<Integer> songs = new ArrayList<Integer>();

	public AlbumDTO(int albumID, String name, ArrayList<Integer> songs) {
		this.albumID = albumID;
		this.songs = songs;
		this.name = name;
	}

	public int getAlbumID() {
		return albumID;
	}

	public ArrayList<Integer> getSongs() {
		return songs;
	}

	public String getName() {
		return name;
	}
}
