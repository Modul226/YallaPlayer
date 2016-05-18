package persistence;

import java.util.ArrayList;

public class ContainerDTO {
	private ArrayList<SongDTO> songs = new ArrayList<SongDTO>();
	private ArrayList<AlbumDTO> albums = new ArrayList<AlbumDTO>();
	private ArrayList<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();
	private ArrayList<InterpretDTO> interprets = new ArrayList<InterpretDTO>();

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

	public void addPlaylist(String name,ArrayList<Integer> playlist){
		playlists.add(new PlaylistDTO(playlists.size() + 1, name, playlist));
	}

	public ArrayList<InterpretDTO> getInterprets() {
		return interprets;
	}

}
