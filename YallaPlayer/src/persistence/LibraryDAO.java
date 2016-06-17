/**
 * DAO that allows access to the methods for reading the library and writing the containerDTO to an XML
 * @author erflo
 */

package persistence;

public interface LibraryDAO {
	/**
	 * @return Returns the full container
	 */
	public ContainerDTO getLibrary();

	/**
	 * @param path
	 */
	public void readLibrary(String path);

	/**
	 * Reads the folder and the MP3 files in it
	 */
	public void readLibrary();

	/**
	 * @param containerDTO
	 */
	public void writeContainerToXML(ContainerDTO containerDTO);
}
