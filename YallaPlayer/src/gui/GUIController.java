package gui;

import java.io.IOException;
import java.util.ArrayList;

import business.Playlist;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import persistence.SongDTO;

public class GUIController extends Application {

	private Stage primaryStage;
	private VBox rootLayout;
	private GridPane dialog;
	private String newPlaylistName;
	private TextField playlistNameInput;
	private Playlist playlist = new Playlist();


	public void showRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(this);
			loader.setLocation(GUIStarter.class.getResource("/gui/FirstView.fxml"));
			rootLayout = (VBox) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAddPlaylistNameDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(this);
			loader.setLocation(GUIStarter.class.getResource("/gui/AddPlaylistNameDialog.fxml"));
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
			loader.setLocation(GUIStarter.class.getResource("/gui/AddPlaylistAddTitlesDialog.fxml"));
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
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("YallaPlayer");
		showRootLayout();
	}

	public void playlistAdd(){
		//create ArrayList from selected elements
		ArrayList<SongDTO> titles = new ArrayList<SongDTO>();
		playlist.add(newPlaylistName, titles);
	}

	public static void main(String[] args) {
		launch(args);
	}
}