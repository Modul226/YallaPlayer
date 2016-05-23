package business;

import java.util.ArrayList;

import persistence.ContainerDTO;
import persistence.LibraryDAO;
import persistence.XMLAccess;

public class Library {

	private LibraryDAO libraryDAO = new XMLAccess();
	private ContainerDTO containerDTO;

	public Library(){
		readLibrary("adsf");
	}

	public void readLibrary(String path) {
		libraryDAO.readLibrary(path);
		this.containerDTO = libraryDAO.getLibrary();
	}

	public void readLibrary() {
		libraryDAO.readLibrary();
	}

	public void addPlaylist(String name, ArrayList<Integer> titles){
		containerDTO.addPlaylist(name,titles);
		libraryDAO.readLibrary();
	}

	public ContainerDTO getLibrary() {
		return this.containerDTO;
	}

	public void writeContainerToXML() {
		libraryDAO.writeContainerToXML(this.containerDTO);
	}
}
