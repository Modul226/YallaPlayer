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

	public ContainerDTO() {
	}

	public ContainerDTO(ArrayList<SongDTO> songs, ArrayList<AlbumDTO> albums, ArrayList<PlaylistDTO> playlists,
			ArrayList<InterpretDTO> interprets) {
		this.songs = songs;
		this.albums = albums;
		this.playlists = playlists;
		this.interprets = interprets;
	}

	public ArrayList<SongDTO> getSongs() {
		return songs;
	}

	public SongDTO getSong(int songID) {
		for (SongDTO song : songs) {
			if (song.getSongID() == songID) {
				return song;
			}
		}
		return null;
	}

	public InterpretDTO getInterpret(int interpretID) {
		for (InterpretDTO interpret : interprets) {
			if (interpret.getInterpretID() == interpretID) {
				return interpret;
			}
		}
		return null;
	}

	public PlaylistDTO getPlaylist(int playlistID) {
		for (PlaylistDTO playlist : playlists) {
			if (playlist.getPlaylistID() == playlistID) {
				return playlist;
			}
		}
		return null;
	}


	public ArrayList<SongDTO> getInterpretTitles(int interpretID) {
		ArrayList<SongDTO> interpretSongs = new ArrayList<SongDTO>();
		for (SongDTO song : songs) {
			if (song.getInterpret() == interpretID) {
				interpretSongs.add(song);
			}
		}
		return interpretSongs;
	}

	public ArrayList<AlbumDTO> getAlbums() {
		return albums;
	}

	public ArrayList<PlaylistDTO> getPlaylists() {
		return playlists;
	}

	public ArrayList<InterpretDTO> getInterprets() {
		return interprets;
	}

	@XmlElement
	public void setSongs(ArrayList<SongDTO> songs) {
		this.songs = songs;
	}

	@XmlElement
	public void setAlbums(ArrayList<AlbumDTO> albums) {
		this.albums = albums;
	}

	@XmlElement
	public void setPlaylists(ArrayList<PlaylistDTO> playlists) {
		this.playlists = playlists;
	}

	@XmlElement
	public void setInterprets(ArrayList<InterpretDTO> interprets) {
		this.interprets = interprets;
	}

	public void addPlaylist(String name, ArrayList<Integer> playlist) {
		if (playlists == null) {
			playlists = new ArrayList<PlaylistDTO>();
		}
		playlists.add(new PlaylistDTO(playlists.size() + 1, name, playlist));
	}

	public void removeTitleFromPlaylist(int playlist, int songID) {
		PlaylistDTO playlistDTO = getPlaylist(playlist);
		int playlistIndex = playlists.indexOf(playlistDTO);
		playlists.get(playlistIndex).removeSong(songID);
	}

	public void removePlaylist(int playlist) {
		PlaylistDTO playlistDTO = getPlaylist(playlist);
		playlists.remove(playlists.indexOf(playlistDTO));
	}
}
