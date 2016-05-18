package persistence;

import java.util.ArrayList;

public class XMLAccess implements LibraryDAO, PlaylistDAO {

	@Override
	public void addPlaylist(String name, ArrayList<Integer> titles) {
		// TODO neue playlist hinzuf�gen

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

	@Override
	public void writeContainerToXML(ContainerDTO containerDTO) {
		// TODO Auto-generated method stub

	}
}
