package persistence;

import java.util.ArrayList;

public class XMLAccess implements LibraryDAO, PlaylistDAO {

	@Override
	public void addNewPlaylist(String name, ArrayList<SongDTO> titles) {
		// TODO neue playlist hinzufügen
	}

	@Override
	public PlaylistDTO getAllPlaylists() {
		// TODO Alle playlisten auslesen
		return null;
	}

	@Override
	public ContainerDTO getLibrary() {
		// TODO Alles holen, bis auf Playlisten
		return null;
	}

	@Override
	public void readLibrary(String path) {
		// TODO Library neu auslesen
	}
}
