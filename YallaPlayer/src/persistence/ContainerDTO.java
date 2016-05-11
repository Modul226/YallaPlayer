package persistence;

import java.util.ArrayList;

public class ContainerDTO {
	public ArrayList<SongDTO> songs = new ArrayList<SongDTO>();
	public ArrayList<AlbumDTO> albums = new ArrayList<AlbumDTO>();
	public ArrayList<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();
	public ArrayList<InterpretDTO> interprets = new ArrayList<InterpretDTO>();

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

	public ArrayList<AlbumDTO> getAlbums() {
		return albums;
	}

	public ArrayList<PlaylistDTO> getPlaylists() {
		return playlists;
	}

	public ArrayList<InterpretDTO> getInterprets() {
		return interprets;
	}

	public void addPlaylist(PlaylistDTO playlistDTO){
		playlists.add(playlistDTO);
	}

}
