package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import business.Library;
import business.Playlist;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
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

	final int ROW_HEIGHT = 23;
	private Stage primaryStage;
	private VBox rootLayout;
	private GridPane dialog;
	private String newPlaylistName;
	private TextField playlistNameInput;
	private Playlist playlist = new Playlist();
	private Library library = new Library();
	private ContainerDTO containerDTO;

	@FXML
	private ListView<SongDTO> titlesList;
	@FXML
	private ListView<TitledPane> interpretsList;
	@FXML
	private ListView<TitledPane> albumsList;
	@FXML
	private ListView<SongDTO> selectTitlesForPlaylistList;
	@FXML
	private Accordion accordionForPlaylists;

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
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(3, 1, "jaja", "PlatzhalterPfad"));
		songs.add(new SongDTO(4, 1, "Test", "PlatzhalterPfad"));
		songs.add(new SongDTO(5, 2, "Test", "PlatzhalterPfad"));
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
		playlist1.add(1);
		playlist1.add(2);
		playlist1.add(3);
		playlist1.add(4);
		playlist1.add(1);
		playlist1.add(2);
		playlist1.add(3);
		playlist1.add(4);
		playlist1.add(1);
		playlist1.add(2);
		playlist1.add(3);
		playlist1.add(4);
		playlist1.add(1);
		playlist1.add(2);
		playlist1.add(3);
		playlist1.add(4);
		playlist1.add(1);
		playlist1.add(2);
		playlist1.add(3);
		playlist1.add(4);
		playlists.add(new PlaylistDTO(0, "So gail", playlist1));
		playlists.add(new PlaylistDTO(2, "So gaasdfil", playlist1));
		playlists.add(new PlaylistDTO(3, "So gaasdfil", playlist1));
		playlists.add(new PlaylistDTO(4, "So gail", playlist1));
		playlists.add(new PlaylistDTO(3, "So gasdfail", playlist1));

		ArrayList<InterpretDTO> interprets = new ArrayList<InterpretDTO>();
		interprets.add(new InterpretDTO(0, "Babo"));
		interprets.add(new InterpretDTO(1, "Yolo MC"));
		interprets.add(new InterpretDTO(2, "Dr boss"));

		containerDTO = new ContainerDTO(songs, albums, playlists, interprets);

		ObservableList<SongDTO> titlesListForView = FXCollections.observableArrayList();
		ObservableList<TitledPane> albumsListForView = FXCollections.observableArrayList();
		ObservableList<TitledPane> playlistsListForView = FXCollections.observableArrayList();
		ObservableList<TitledPane> interpretsListForView = FXCollections.observableArrayList();

		for (PlaylistDTO playlist : containerDTO.getPlaylists()) {
			ListView<SongDTO> singlePlaylistList = new ListView<SongDTO>();
			ObservableList<SongDTO> titlesListForPlaylist = FXCollections.observableArrayList();
			int playlistID = playlist.getPlaylistID();
			for (int songID : playlist.getSongs()) {
				SongDTO songDTO = containerDTO.getSong(songID);
				SongDTO newSongDTO = new SongDTO(songDTO.getSongID(), songDTO.getInterpret(), songDTO.getName(),
						songDTO.getPath());
				newSongDTO.setPlaylist(playlistID);
				titlesListForPlaylist.add(newSongDTO);
			}
			addListenerOnListView(singlePlaylistList);
			singlePlaylistList.setPrefHeight(titlesListForPlaylist.size() * ROW_HEIGHT);
			singlePlaylistList.setItems(titlesListForPlaylist);
			TitledPane tp = new TitledPane(playlist.getName(), singlePlaylistList);
			tp.setExpanded(false);
			playlistsListForView.add(tp);
		}

		for (SongDTO song : containerDTO.getSongs()) {
			titlesListForView.add(song);
		}

		for (InterpretDTO interpret : containerDTO.getInterprets()) {
			ListView<SongDTO> singleInterpretList = new ListView<SongDTO>();
			ObservableList<SongDTO> titlesListForInterpret = FXCollections.observableArrayList();
			for (SongDTO songDTO : containerDTO.getInterpretTitles(interpret.getInterpretID())) {
				titlesListForInterpret.add(songDTO);
			}
			singleInterpretList.setPrefHeight(titlesListForInterpret.size() * ROW_HEIGHT);
			singleInterpretList.setItems(titlesListForInterpret);
			TitledPane tp = new TitledPane(interpret.getName(), singleInterpretList);
			tp.setExpanded(false);
			tp.setMaxWidth(500.0);
			interpretsListForView.add(tp);
			addListenerOnListView(singleInterpretList);
		}

		for (AlbumDTO album : containerDTO.getAlbums()) {
			ListView<SongDTO> singleAlbumList = new ListView<SongDTO>();
			ObservableList<SongDTO> titlesListForAlbum = FXCollections.observableArrayList();
			for (SongDTO songDTO : containerDTO.getInterpretTitles(album.getAlbumID())) {
				titlesListForAlbum.add(songDTO);
			}
			singleAlbumList.setPrefHeight(titlesListForAlbum.size() * ROW_HEIGHT);
			singleAlbumList.setItems(titlesListForAlbum);
			TitledPane tp = new TitledPane(album.getName(), singleAlbumList);
			tp.setExpanded(false);
			tp.setMaxWidth(500.0);
			albumsListForView.add(tp);
			addListenerOnListView(singleAlbumList);
		}

		accordionForPlaylists.getPanes().addAll(playlistsListForView);
		titlesList.setItems(titlesListForView);
		addListenerOnListView(titlesList);
		albumsList.setItems(albumsListForView);
		interpretsList.setItems(interpretsListForView);

	}

	private void addListenerOnListView(ListView<SongDTO> lv) {
		lv.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				SongDTO song = lv.getSelectionModel().getSelectedItem();
				if (song.getPlaylist() == -1) {
					playSongClicked(song);
				} else {
					playPlaylistClicked(song);
				}
			}
		});
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
			ObservableList<SongDTO> titlesListForView = FXCollections.observableArrayList();

			selectTitlesForPlaylistList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			for (SongDTO song : containerDTO.getSongs()) {
				titlesListForView.add(song);
			}

			selectTitlesForPlaylistList.setCellFactory(lv -> {
				ListCell<SongDTO> cell = new ListCell<>();
				cell.textProperty().bind(cell.itemProperty().asString());

				cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
					selectTitlesForPlaylistList.requestFocus();
					if (!cell.isEmpty()) {
						int index = cell.getIndex();
						MultipleSelectionModel<SongDTO> selectionModel = selectTitlesForPlaylistList
								.getSelectionModel();
						if (selectionModel.getSelectedIndices().contains(index)) {
							selectionModel.clearSelection(index);
						} else {
							selectionModel.select(index);
						}
						event.consume();
					}
				});
				return cell;
			});

			selectTitlesForPlaylistList.setItems(titlesListForView);

			Scene scene = new Scene(dialog);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		this.containerDTO = library.getLibrary();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("YallaPlayer");
		showRootLayout();
	}

	public void playlistAdd() {
		ObservableList<SongDTO> clickedSongs = selectTitlesForPlaylistList.getSelectionModel().getSelectedItems();
		ArrayList<Integer> titles = new ArrayList<Integer>();

		for (SongDTO song : clickedSongs) {
			System.out.println(song.getSongID());
			titles.add(song.getSongID());
		}

		playlist.addPlaylist(newPlaylistName, titles);
		library.writeContainerToXML(containerDTO);
		showRootLayout();
	}

	public void playSong(String path) {
		final File resource = new File(path);
		String tmpString = resource.toURI().toString();
		final Media media = new Media(tmpString);
		final MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();

		primaryStage.setTitle("Audio Player 1");
		primaryStage.setWidth(200);
		primaryStage.setHeight(200);
		primaryStage.show();
	}

	public void playSongClicked(SongDTO song) {
		playSong(song.getPath());
		System.out.println(song.getSongID());
		System.out.println("Playing...");
	}

	public void playPlaylistClicked(SongDTO song) {
		playSong(song.getPath());
		System.out.println(song.getPlaylist());
		System.out.println("Playing...");
	}

	public void stopSong() {
		// TODO stop all playing Songs
	}

	public static void main(String[] args) {
		launch(args);
	}

}