package gui;

import java.io.IOException;
import java.util.ArrayList;

import business.Library;
import business.Playlist;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import persistence.AlbumDTO;
import persistence.ContainerDTO;
import persistence.InterpretDTO;
import persistence.PlaylistDTO;
import persistence.SongDTO;

public class GUIController extends Application {

	private Stage primaryStage;
	private VBox rootLayout;
	private GridPane dialog;
	private String newPlaylistName;
	private TextField playlistNameInput;
	private Playlist playlist = new Playlist();
	private Library library = new Library();
	private ContainerDTO container;

	@FXML
	private ListView<String> titlesList;
	@FXML
	private ListView<String> interpretsList;
	@FXML
	private ListView<String> albumsList;
	@FXML


	/*
	 * private ListView<String> albumsList; private ListView<String>
	 * artistsList;
	 */

	public void showRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(this);
			loader.setLocation(GUIController.class.getResource("/gui/RootView.fxml"));
			rootLayout = (VBox) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			loadDataIntoView();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadDataIntoView() {
		// Small Mock with some Dada
		ArrayList<SongDTO> songs = new ArrayList<SongDTO>();
		songs.add(new SongDTO(0, 0, "Same zelle", "PlatzhalterPfad"));
		songs.add(new SongDTO(1, 0, "Same gelle", "PlatzhalterPfad"));
		songs.add(new SongDTO(2, 0, "Same relle", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));

		ArrayList<AlbumDTO> albums = new ArrayList<AlbumDTO>();
		ArrayList<Integer> album1 = new ArrayList<Integer>();
		album1.add(0);
		album1.add(1);
		album1.add(2);
		albums.add(new AlbumDTO(0, "Babo de Bozz", album1));
		ArrayList<Integer> album2 = new ArrayList<Integer>();
		album2.add(3);
		album2.add(4);
		albums.add(new AlbumDTO(1, "Daim", album2));

		ArrayList<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();
		ArrayList<Integer> playlist1 = new ArrayList<Integer>();
		playlist1.add(0);
		playlist1.add(1);
		playlist1.add(2);
		playlist1.add(3);
		playlist1.add(4);
		playlists.add(new PlaylistDTO(0, "So gail", playlist1));

		ArrayList<InterpretDTO> interprets = new ArrayList<InterpretDTO>();
		interprets.add(new InterpretDTO(0, "Babo"));
		interprets.add(new InterpretDTO(1, "Yolo MC"));
		interprets.add(new InterpretDTO(2, "Dr boss"));

		container = new ContainerDTO(songs, albums, playlists, interprets);

		// containerAuslesen
		ObservableList<String> titlesListForView = FXCollections.observableArrayList();
		ObservableList<String> albumsListForView = FXCollections.observableArrayList();
		ObservableList<String> interpretsListForView = FXCollections.observableArrayList();

		for (PlaylistDTO playlist : container.getPlaylists()) {
			for (int songID : playlist.getSongs()) {
				SongDTO songDTO = container.getSong(songID);

				// TODO dieses Element mit entsprechenden Daten in View
				// einf�gen

				/*
				 * <TitledPane animated="false" text="untitled 1"> <content>
				 * <AnchorPane minHeight="0.0" minWidth="0.0" prefHeight="180.0"
				 * prefWidth="200.0" style="-fx-background-color: D1D1D1;" />
				 * </content> </TitledPane>
				 */
			}
		}

		for (SongDTO song : container.getSongs()) {
			titlesListForView.add(song.getSongID() + "\t" + container.getInterpret(song.getInterpret()).getName()
					+ " - " + song.getName());
		}

		for (InterpretDTO interpret : container.getInterprets()) {
			interpretsListForView.add(interpret.interpretID + "\t" + interpret.getName());
		}

		for (AlbumDTO album : container.getAlbums()) {
			albumsListForView.add(album.getAlbumID() + "\t" + album.getName());

			// TODO wie bei playlisten accordeons erstellen wenn m�glich
			/*
			 * for (int songID : album.getSongs()) { SongDTO songDTO =
			 * container.getSong(songID); // TODO dieses Element mit
			 * entsprechenden Daten in View // einf�gen
			 *
			 * }
			 */
		}

		titlesList.setItems(titlesListForView);
		albumsList.setItems(albumsListForView);
		interpretsList.setItems(interpretsListForView);
	}

	public void showAddPlaylistNameDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(this);
			loader.setLocation(GUIController.class.getResource("/gui/AddPlaylistNameDialog.fxml"));
			dialog = (GridPane) loader.load();

			Scene scene = new Scene(dialog);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void playlistNameIsSet() {
		newPlaylistName = playlistNameInput.getText();
		showAddPlaylistAddTitlesDialog();
	}

	public void showAddPlaylistAddTitlesDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(this);
			loader.setLocation(GUIController.class.getResource("/gui/AddPlaylistAddTitlesDialog.fxml"));
			dialog = (GridPane) loader.load();

			Scene scene = new Scene(dialog);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		this.container = library.getLibrary();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("YallaPlayer");
		showRootLayout();
	}

	public void playlistAdd() {
		// TODO create ArrayList from selected elements
		ArrayList<SongDTO> titles = new ArrayList<SongDTO>();
		library.addPlaylist(newPlaylistName, titles);
		showRootLayout();
	}

	public void playSong(String path) {
		path = "C:/Temp/TestBibliothek/02 Rosario Intro.mp3";
		Media media = new Media(path);
		MediaPlayer mp = new MediaPlayer(media);
		mp.play();
	}

	public void playSongClicked() {
		// TODO funktioniert noch nicht -> nullPointer
		int idOfClickedItem = titlesList.getSelectionModel().getSelectedItem().charAt(0);
		String path = container.getSong((Integer) idOfClickedItem).getPath();
		playSong(path);
		System.out.println("Playing...");
	}

	public void stopSong() {
		// TODO stop all playing Songs
	}

	public static void main(String[] args) {
		launch(args);
	}

}