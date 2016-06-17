/**
 * DTO that contains the full library-data
 * @author erflo
 */

package persistence;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContainerDTO {
	private ArrayList<SongDTO> songs = new ArrayList<SongDTO>();
	private ArrayList<AlbumDTO> albums = new ArrayList<AlbumDTO>();
	private ArrayList<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();
	private ArrayList<InterpretDTO> interprets = new ArrayList<InterpretDTO>();

	/**
	 * 
	 */
	public ContainerDTO() {
	}

	/**
	 * @param songs
	 * @param albums
	 * @param playlists
	 * @param interprets
	 */
	public ContainerDTO(ArrayList<SongDTO> songs, ArrayList<AlbumDTO> albums, ArrayList<PlaylistDTO> playlists,
			ArrayList<InterpretDTO> interprets) {
		this.songs = songs;
		this.albums = albums;
		this.playlists = playlists;
		this.interprets = interprets;
	}

	/**
	 * @return songs
	 */
	public ArrayList<SongDTO> getSongs() {
		return songs;
	}

	/**
	 * @param songID
	 * @return song
	 */
	public SongDTO getSong(int songID) {
		for (SongDTO song : songs) {
			if (song.getSongID() == songID) {
				return song;
			}
		}
		return null;
	}

	/**
	 * @param interpretID
	 * @return interpret
	 */
	public InterpretDTO getInterpret(int interpretID) {
		for (InterpretDTO interpret : interprets) {
			if (interpret.getInterpretID() == interpretID) {
				return interpret;
			}
		}
		return null;
	}

	/**
	 * @param playlistID
	 * @return playlist
	 */
	public PlaylistDTO getPlaylist(int playlistID) {
		for (PlaylistDTO playlist : playlists) {
			if (playlist.getPlaylistID() == playlistID) {
				return playlist;
			}
		}
		return null;
	}


	/**
	 * @param interpretID
	 * @return interpretSongs
	 */
	public ArrayList<SongDTO> getInterpretTitles(int interpretID) {
		ArrayList<SongDTO> interpretSongs = new ArrayList<SongDTO>();
		for (SongDTO song : songs) {
			if (song.getInterpret() == interpretID) {
				interpretSongs.add(song);
			}
		}
		return interpretSongs;
	}

	/**
	 * @return albums
	 */
	public ArrayList<AlbumDTO> getAlbums() {
		return albums;
	}

	/**
	 * @return playlists
	 */
	public ArrayList<PlaylistDTO> getPlaylists() {
		return playlists;
	}

	/**
	 * @return interprets
	 */
	public ArrayList<InterpretDTO> getInterprets() {
		return interprets;
	}

	/**
	 * @param songs
	 */
	@XmlElement
	public void setSongs(ArrayList<SongDTO> songs) {
		this.songs = songs;
	}

	/**
	 * @param albums
	 */
	@XmlElement
	public void setAlbums(ArrayList<AlbumDTO> albums) {
		this.albums = albums;
	}

	/**
	 * @param playlists
	 */
	@XmlElement
	public void setPlaylists(ArrayList<PlaylistDTO> playlists) {
		this.playlists = playlists;
	}

	/**
	 * @param interprets
	 */
	@XmlElement
	public void setInterprets(ArrayList<InterpretDTO> interprets) {
		this.interprets = interprets;
	}

	/**
	 * @param name
	 * @param playlist
	 */
	public void addPlaylist(String name, ArrayList<Integer> playlist) {
		if (playlists == null) {
			playlists = new ArrayList<PlaylistDTO>();
		}
		playlists.add(new PlaylistDTO(playlists.size() + 1, name, playlist));
	}

	/**
	 * @param playlist
	 * @param songID
	 */
	public void removeTitleFromPlaylist(int playlist, int songID) {
		PlaylistDTO playlistDTO = getPlaylist(playlist);
		int playlistIndex = playlists.indexOf(playlistDTO);
		playlists.get(playlistIndex).removeSong(songID);
		if(playlists.get(playlistIndex).getSongs().size() < 1){
			removePlaylist(playlistDTO.getPlaylistID());
		}
	}

	/**
	 * @param playlist
	 */
	public void removePlaylist(int playlist) {
		PlaylistDTO playlistDTO = getPlaylist(playlist);
		playlists.remove(playlists.indexOf(playlistDTO));
	}
}
