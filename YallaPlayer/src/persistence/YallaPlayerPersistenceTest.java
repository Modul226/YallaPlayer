/**
 * @author erflo
 */

package persistence;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class YallaPlayerPersistenceTest {

	ContainerDTO containerDTO = new ContainerDTO();

	@Before
	public void setUp() {
		ArrayList<InterpretDTO> interprets = new ArrayList<InterpretDTO>();
		interprets.add(new InterpretDTO(0, "Justin Bieber"));

		ArrayList<SongDTO> songs = new ArrayList<SongDTO>();
		songs.add(new SongDTO(0, 0, "ShittySong", "path/path/path1"));
		songs.add(new SongDTO(1, 0, "ShittyerSong", "path/path/path2"));
		songs.add(new SongDTO(2, 0, "ShittyiestSong", "path/path/path3"));

		ArrayList<Integer> songsForAlbum = new ArrayList<Integer>();
		songsForAlbum.add(0);
		songsForAlbum.add(1);
		songsForAlbum.add(2);

		ArrayList<AlbumDTO> albums = new ArrayList<AlbumDTO>();
		albums.add(new AlbumDTO(0, "Shitty Songs EP", songsForAlbum));

		containerDTO.setAlbums(albums);
		containerDTO.setInterprets(interprets);
		containerDTO.setSongs(songs);

	}

	@Test
	public void getTitleFromContainer() {
		SongDTO songDTO = containerDTO.getSong(0);
		assertEquals(0, songDTO.getInterpret());
		assertEquals("path/path/path1", songDTO.getPath());
		assertEquals(-1, songDTO.getPlaylist());
		assertEquals("ShittySong", songDTO.getName());
	}

	@Test
	public void addPlaylistToContainer() {
		ArrayList<Integer> songs = new ArrayList<Integer>();
		songs.add(0);
		songs.add(1);
		songs.add(2);
		containerDTO.addPlaylist("Shittiest Playlist", songs);

		PlaylistDTO playlistFromContainer = containerDTO.getPlaylists().get(0);

		assertEquals("Shittiest Playlist", playlistFromContainer.getName());
		assertEquals(1, playlistFromContainer.getPlaylistID());
		assertEquals(0, playlistFromContainer.getSongs().get(0).intValue());
		assertEquals(1, playlistFromContainer.getSongs().get(1).intValue());
		assertEquals(2, playlistFromContainer.getSongs().get(2).intValue());
	}

}
