/**
 * DTO that contains an Album
 * @author erflo
 */

package persistence;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class AlbumDTO {
	private int albumID;
	private String name;
	private ArrayList<Integer> songs = new ArrayList<Integer>();

	/**
	 * Empty constructor for JAXB
	 */
	public AlbumDTO() {
	}

	/**
	 * @param albumID
	 * @param name
	 * @param songs
	 */
	public AlbumDTO(int albumID, String name, ArrayList<Integer> songs) {
		this.albumID = albumID;
		this.songs = songs;
		this.name = name;
	}

	/**
	 * @return albumID
	 */
	public int getAlbumID() {
		return albumID;
	}

	/**
	 * @return songs
	 */
	public ArrayList<Integer> getSongs() {
		return songs;
	}

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
	 * @param albumID
	 */
	@XmlElement
	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}

	/**
	 * @param name
	 */
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param songs
	 */
	@XmlElement
	public void setSongs(ArrayList<Integer> songs) {
		this.songs = songs;
	}

}
