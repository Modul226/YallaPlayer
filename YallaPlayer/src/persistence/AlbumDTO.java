package persistence;

import java.util.ArrayList;

public class AlbumDTO {
	public int albumID;
	public ArrayList<Integer> songs = new ArrayList<Integer>();

	public AlbumDTO(int albumID, ArrayList<Integer> songs) {
		this.albumID = albumID;
		this.songs = songs;
	}

	public int getAlbumID() {
		return albumID;
	}

	public ArrayList<Integer> getSongs() {
		return songs;
	}
}
