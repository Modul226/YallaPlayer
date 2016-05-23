package persistence;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.beaglebuddy.mp3.MP3;

public class XMLAccess implements LibraryDAO {

	private ContainerDTO container;
	private ArrayList<SongDTO> songs = new ArrayList<>();
	private ArrayList<AlbumDTO> albums = new ArrayList<>();
	private ArrayList<InterpretDTO> interprets = new ArrayList<>();

	@Override
	public ContainerDTO getLibrary() {
		return container;
	}

	@Override
	public void writeContainerToXML(ContainerDTO containerDTO) {
		containerDTO = container;
		try {

			File file = new File("container.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ContainerDTO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Formatting
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(containerDTO, file);
			jaxbMarshaller.marshal(containerDTO, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void readLibrary() {
		// Serializing
		try {

			File file = new File("container.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ContainerDTO.class);

			// Deserializing
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ContainerDTO cResult = (ContainerDTO) jaxbUnmarshaller.unmarshal(file);

			this.container = cResult;

		} catch (JAXBException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void readLibrary(String path) {
		String pathDefault = System.getProperty("user.home") + "/TestBibliothek";
		File[] listOfFiles;
		File folder = null;
		String format = null;
		int songID = 0;
		int interpretID = 0;
		int albumID = 0;

		try {
			folder = new File(pathDefault);
			listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
				int i = file.getAbsolutePath().lastIndexOf('.');
				if (i > 0) {
					format = file.getAbsolutePath().substring(i + 1);
				}
				if (file.isFile() && format.equals("mp3")) {
					try {
						MP3 mp3 = new MP3(file.getAbsolutePath());

						if (mp3.getBand() != null) {
							// System.out.println(countElements + " " +
							// mp3.getTitle() + " - " + mp3.getBand());

							InterpretDTO interpret = new InterpretDTO(interpretID, mp3.getBand());
							if (!this.interprets.contains(mp3.getBand())) {
								this.interprets.add(interpret);
								interpretID++;
							}

							SongDTO song = new SongDTO(songID, interpret.getInterpretID(), mp3.getTitle(),
									file.getAbsolutePath());
							this.songs.add(song);

						} else {
							// System.out.println(countElements + " " +
							// mp3.getTitle() + " - " +
							// mp3.getID3v1Tag().getArtist());

							InterpretDTO interpret = new InterpretDTO(interpretID, mp3.getID3v1Tag().getArtist());
							if (!this.interprets.contains(mp3.getID3v1Tag().getArtist())) {
								this.interprets.add(interpret);
								interpretID++;
							}

							SongDTO song = new SongDTO(songID, interpret.getInterpretID(), mp3.getTitle(),
									file.getAbsolutePath());
							this.songs.add(song);
							songID++;
						}

						ArrayList<Integer> songs = new ArrayList<>();
						songs.add(songID);

						if (mp3.getAlbum() != null) {
							AlbumDTO album = new AlbumDTO(albumID, mp3.getAlbum(), songs);
							if (!this.albums.contains(mp3.getAlbum())) {
								this.albums.add(album);
								albumID++;
							}
						}
						container = new ContainerDTO(this.songs, this.albums, null, this.interprets);
						writeContainerToXML(container);

					} catch (Exception e) {
						// System.out.println("Probleme mit File " +
						// file.getAbsolutePath() + "\n");
						e.printStackTrace();
					}
				} else {
					// System.out.println("Keine Mp3-Datei! Dies war eine " +
					// format + " Datei!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Angegebener Pfad existiert nicht!");
		} finally {
			if (folder != null) {
				folder.deleteOnExit();
			}
		}

	}

	// public static void main(String[] args) {
	// new XMLAccess().test();

	// }

}