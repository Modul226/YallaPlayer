/**
 * Business object for saving the container / the data of the full library
 * Is also responsible for adding playlists, deleting playlists and editing playlists
 * @author erflo
 */

package business;

import java.util.ArrayList;

import persistence.ContainerDTO;
import persistence.LibraryDAO;
import persistence.XMLAccess;

public class Library {

	private LibraryDAO libraryDAO = new XMLAccess();
	private ContainerDTO containerDTO;

	/**
	 * 
	 */
	public Library(){
		readLibrary("adsf");
	}

	/**
	 * @param path
	 */
	public void readLibrary(String path) {
		libraryDAO.readLibrary(path);
		this.containerDTO = libraryDAO.getLibrary();
	}

	/**
	 * Reads library folder
	 */
	public void readLibrary() {
		libraryDAO.readLibrary();
	}

	/**
	 * @param name
	 * @param titles
	 */
	public void addPlaylist(String name, ArrayList<Integer> titles){
		containerDTO.addPlaylist(name,titles);
		libraryDAO.readLibrary();
	}

	/**
	 * @return containerDTO
	 */
	public ContainerDTO getLibrary() {
		return this.containerDTO;
	}

	/**
	 * Saves contianerDTO into an XML-file
	 */
	public void writeContainerToXML() {
		libraryDAO.writeContainerToXML(this.containerDTO);
	}

	/**
	 * @param playlist
	 * @param songID
	 */
	public void removeTitleFromPlaylist(int playlist, int songID) {
		containerDTO.removeTitleFromPlaylist(playlist, songID);
	}

	/**
	 * @param playlist
	 */
	public void removePlaylist(int playlist) {
		containerDTO.removePlaylist(playlist);

	}
}
