package persistence;

public class XMLAccess implements LibraryDAO {


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
