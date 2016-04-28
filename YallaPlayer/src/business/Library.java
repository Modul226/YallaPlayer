package business;

import persistence.LibraryDAO;
import persistence.XMLAccess;

public class Library {

	private LibraryDAO libraryDAO = new XMLAccess();

	public void readLibrary(String path) {
		libraryDAO.readLibrary(path);
	}
	
	public void getLibrary(String path) {
		libraryDAO.getLibrary();
	}
}
