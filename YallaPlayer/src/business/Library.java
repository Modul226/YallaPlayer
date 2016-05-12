package business;

import java.util.ArrayList;

import persistence.ContainerDTO;
import persistence.LibraryDAO;
import persistence.PlaylistDTO;
import persistence.SongDTO;
import persistence.XMLAccess;

public class Library {

	private LibraryDAO libraryDAO = new XMLAccess();

	public void readLibrary(String path) {
		libraryDAO.readLibrary(path);
	}

	public ContainerDTO getLibrary() {
		return libraryDAO.getLibrary();
	}

	public void addPlaylist(String name, ArrayList<SongDTO> titles) {
		this.getLibrary().playlists.add(new PlaylistDTO(name, titles));
		libraryDAO.writeContainerToXML();
	}
}
