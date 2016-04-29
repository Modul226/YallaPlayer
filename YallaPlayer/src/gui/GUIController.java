package gui;

import java.io.IOException;
import java.util.ArrayList;

import business.Library;
import business.Playlist;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
			// loadDataIntoView();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadDataIntoView() {
		// containerAuslesen

		for (PlaylistDTO playlist : container.getPlaylists()) {
			for (int songID : playlist.getSongs()) {
				SongDTO songDTO = container.getSong(songID);
				// TODO dieses Element mit entsprechenden Daten in View
				// einfügen

				/*
				 * <TitledPane animated="false" text="untitled 1"> <content>
				 * <AnchorPane minHeight="0.0" minWidth="0.0" prefHeight="180.0"
				 * prefWidth="200.0" style="-fx-background-color: D1D1D1;" />
				 * </content> </TitledPane>
				 */
			}
		}

		for (SongDTO song : container.getSongs()) {
			// TODO Element für die Liste in Titles erstellen und dann mit
			// daten befüllen
			// container.getInterpret(song.getInterpret());
		}

		for (InterpretDTO interpret : container.getInterprets()) {
			// TODO Element für die Liste in interprets erstellen und dann mit
			// daten befüllen
		}

		for (AlbumDTO album : container.getAlbums()) {
			// TODO Element für die Liste in albums erstellen und dann mit
			// daten befüllen
			for (int songID : album.getSongs()) {
				SongDTO songDTO = container.getSong(songID);
				// TODO dieses Element mit entsprechenden Daten in View
				// einfügen

				/*
				 * <TitledPane animated="false" text="untitled 1"> <content>
				 * <AnchorPane minHeight="0.0" minWidth="0.0" prefHeight="180.0"
				 * prefWidth="200.0" style="-fx-background-color: D1D1D1;" />
				 * </content> </TitledPane>
				 */
			}
		}
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
		this.container = library.getLibrary("PLATZHALTER");
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("YallaPlayer");
		showRootLayout();
	}

	public void playlistAdd(){
		// TODO create ArrayList from selected elements
		ArrayList<SongDTO> titles = new ArrayList<SongDTO>();
		playlist.add(newPlaylistName, titles);
		showRootLayout();
	}

	public void playSong(int songID) {
		// TODO getSong with ID, play it
	}

	public void stopSong() {
		// TODO stop all playing Songs
	}

	public static void main(String[] args) {
		launch(args);
	}
}