package business;

import persistence.ContainerDTO;
import persistence.LibraryDAO;
import persistence.XMLAccess;

public class Library {

	private LibraryDAO libraryDAO = new XMLAccess();

	public void readLibrary(String path) {
		libraryDAO.readLibrary(path);
	}
	
	public ContainerDTO getLibrary(String path) {
		return libraryDAO.getLibrary();
	}
}
