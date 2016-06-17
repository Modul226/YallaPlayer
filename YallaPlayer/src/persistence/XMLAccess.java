/**
 * Class that reads all the MP3 Files and generates an containerDTO
 * @author waleed, erflo)
 */

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistence.LibraryDAO#getLibrary()
	 */
	@Override
	public ContainerDTO getLibrary() {
		return container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistence.LibraryDAO#writeContainerToXML(persistence.ContainerDTO)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistence.LibraryDAO#readLibrary()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistence.LibraryDAO#readLibrary(java.lang.String)
	 */
	@Override
	public void readLibrary(String path) {
		String pathDefault = System.getProperty("user.home") + "/TestBibliothek";
		File[] listOfFiles;
		File folder = null;
		String format = null;
		int songID = 0;

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

							int newSongsInterpretID = containsInterpret(mp3.getBand());
							if (newSongsInterpretID == -1) {
								InterpretDTO interpret = new InterpretDTO(interprets.size() + 1, mp3.getBand());
								this.interprets.add(interpret);
								newSongsInterpretID = interprets.size();
							}

							SongDTO song = new SongDTO(songID, newSongsInterpretID, mp3.getTitle(),
									file.getAbsolutePath());

							this.songs.add(song);
							songID++;

							ArrayList<Integer> songs = new ArrayList<>();
							songs.add(songID);

							if (mp3.getAlbum() != null) {
								int newAlbumID = containsAlbum(mp3.getAlbum());
								if (newAlbumID == -1) {
									ArrayList<Integer> songsForAlbum = new ArrayList<Integer>();
									songsForAlbum.add(song.getSongID());
									AlbumDTO album = new AlbumDTO(albums.size() + 1, mp3.getAlbum(), songsForAlbum);
									this.albums.add(album);
								} else {
									AlbumDTO albumToAddSongTo = getAlbumById(newAlbumID);
									albumToAddSongTo.getSongs().add(song.getSongID());
								}
							}

						} else {
							int newSongsInterpretID = containsInterpret(mp3.getID3v1Tag().getArtist());
							if (newSongsInterpretID == -1) {
								InterpretDTO interpret = new InterpretDTO(interprets.size() + 1,
										mp3.getID3v1Tag().getArtist());
								this.interprets.add(interpret);
								newSongsInterpretID = interprets.size();
							}

							SongDTO song = new SongDTO(songID, newSongsInterpretID, mp3.getTitle(),
									file.getAbsolutePath());

							this.songs.add(song);
							songID++;

							ArrayList<Integer> songs = new ArrayList<>();
							songs.add(songID);

							if (mp3.getAlbum() != null) {
								int newAlbumID = containsAlbum(mp3.getAlbum());
								if (newAlbumID == -1) {
									ArrayList<Integer> songsForAlbum = new ArrayList<Integer>();
									songsForAlbum.add(song.getSongID());
									AlbumDTO album = new AlbumDTO(albums.size() + 1, mp3.getAlbum(), songsForAlbum);
									this.albums.add(album);
								} else {
									AlbumDTO albumToAddSongTo = getAlbumById(newAlbumID);
									albumToAddSongTo.getSongs().add(song.getSongID());
								}
							}

						}
						
						container = new ContainerDTO(this.songs, this.albums, null, this.interprets);
						writeContainerToXML(container);

					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (folder != null) {
				folder.deleteOnExit();
			}
		}

	}

	private int containsInterpret(String interpretName) {
		for (InterpretDTO interpret : interprets) {
			if (interpretName.equals(interpret.getName())) {
				return interpret.getInterpretID();
			}
		}
		return -1;
	}

	private AlbumDTO getAlbumById(int albumId) {
		for (AlbumDTO album : albums) {
			if (album.getAlbumID() == albumId) {
				return album;
			}
		}
		return null;
	}

	private int containsAlbum(String albumName) {
		for (AlbumDTO album : albums) {
			if (albumName.equals(album.getName())) {
				return album.getAlbumID();
			}
		}
		return -1;
	}
}