package persistence;

public interface LibraryDAO {
	public ContainerDTO getLibrary();

	public void readLibrary(String path);

	public void readLibrary();

	public void writeContainerToXML(ContainerDTO containerDTO);
}
