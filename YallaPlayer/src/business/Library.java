package business;

import persistence.ContainerDTO;
import persistence.LibraryDAO;
import persistence.XMLAccess;

public class Library {

	private LibraryDAO libraryDAO = new XMLAccess();

	public void readLibrary(String path) {
		libraryDAO.readLibrary(path);
	}

	public ContainerDTO getLibrary() {
		return libraryDAO.getLibrary();
	}

	public void writeContainerToXML(ContainerDTO containerDTO) {
		libraryDAO.writeContainerToXML(containerDTO);
	}
}
