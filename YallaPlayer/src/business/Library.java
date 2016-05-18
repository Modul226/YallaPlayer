package business;

import java.util.ArrayList;

import persistence.ContainerDTO;
import persistence.LibraryDAO;
import persistence.XMLAccess;

public class Library {

	private LibraryDAO libraryDAO = new XMLAccess();
	private ContainerDTO containerDTO;

	public Library(){
		readLibrary();
	}

	public void readLibrary(String path) {
		libraryDAO.readLibrary(path);
		this.containerDTO = libraryDAO.getLibrary();
	}

	public void addPlaylist(String name, ArrayList<Integer> titles){
		containerDTO.addPlaylist(name,titles);
		readLibrary();
	}

	public ContainerDTO getLibrary() {
		return this.containerDTO;
	}

	public void writeContainerToXML() {
		libraryDAO.writeContainerToXML(this.containerDTO);
	}
}
