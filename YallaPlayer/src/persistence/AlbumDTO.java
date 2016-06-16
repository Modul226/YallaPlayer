/**
 * @author erflo
 */

package persistence;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class AlbumDTO {
	private int albumID;
	private String name;
	private ArrayList<Integer> songs = new ArrayList<Integer>();

	public AlbumDTO() {
	}

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

	@Override
	public String toString() {
		return name;
	}

	@XmlElement
	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public void setSongs(ArrayList<Integer> songs) {
		this.songs = songs;
	}

}
