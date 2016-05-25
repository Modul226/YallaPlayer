package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import business.Library;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
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
import persistence.InterpretDTO;
import persistence.PlaylistDTO;
import persistence.SongDTO;

public class GUIController extends Application {

	final int ROW_HEIGHT = 23;
	private Stage primaryStage;
	private VBox rootLayout;
	private GridPane dialog;
	private String newPlaylistName;
	private Library library = new Library();

	@FXML
	private TextField playlistNameInput;
	@FXML
	private ListView<SongDTO> titlesList;
	@FXML
	private ListView<TitledPane> interpretsList;
	@FXML
	private ListView<TitledPane> albumsList;
	@FXML
	private ListView<SongDTO> selectTitlesForPlaylistList;
	@FXML
	private Label playStatusLabel;
	@FXML
	private Accordion accordionForPlaylists;
	private MediaPlayer mediaPlayer = null;
	private SongDTO songDtoPlaying = null;
	private String playStatus = null;
	private PlaylistDTO playlistPlaying = null;

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
			if (playStatus != null) {
				playStatusLabel.setText(playStatus);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadDataIntoView() {

		ObservableList<SongDTO> titlesListForView = FXCollections.observableArrayList();
		ObservableList<TitledPane> albumsListForView = FXCollections.observableArrayList();
		ObservableList<TitledPane> playlistsListForView = FXCollections.observableArrayList();
		ObservableList<TitledPane> interpretsListForView = FXCollections.observableArrayList();

		if (library.getLibrary().getPlaylists() != null) {
			for (PlaylistDTO playlist : library.getLibrary().getPlaylists()) {
				ListView<SongDTO> singlePlaylistList = new ListView<SongDTO>();
				ObservableList<SongDTO> titlesListForPlaylist = FXCollections.observableArrayList();
				int playlistID = playlist.getPlaylistID();
				for (int songID : playlist.getSongs()) {
					SongDTO songDTO = library.getLibrary().getSong(songID);
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
		}

		for (SongDTO song : library.getLibrary().getSongs()) {
			titlesListForView.add(song);
		}

		for (InterpretDTO interpret : library.getLibrary().getInterprets()) {
			ListView<SongDTO> singleInterpretList = new ListView<SongDTO>();
			ObservableList<SongDTO> titlesListForInterpret = FXCollections.observableArrayList();
			for (SongDTO songDTO : library.getLibrary().getInterpretTitles(interpret.getInterpretID())) {
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

		for (AlbumDTO album : library.getLibrary().getAlbums()) {
			ListView<SongDTO> singleAlbumList = new ListView<SongDTO>();
			ObservableList<SongDTO> titlesListForAlbum = FXCollections.observableArrayList();
			for (int songID : album.getSongs()) {
				titlesListForAlbum.add(library.getLibrary().getSong(songID));
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
				if (song != null) {
					if (song.getPlaylist() == -1) {
						playSongClicked(song);
					} else {
						playPlaylistClicked(song);
					}
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

	private void showAddPlaylistAddTitlesDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(this);
			loader.setLocation(GUIController.class.getResource("/gui/AddPlaylistAddTitlesDialog.fxml"));
			dialog = (GridPane) loader.load();
			ObservableList<SongDTO> titlesListForView = FXCollections.observableArrayList();

			selectTitlesForPlaylistList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			for (SongDTO song : library.getLibrary().getSongs()) {
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

		library.addPlaylist(newPlaylistName, titles);
		library.writeContainerToXML();
		library.readLibrary();
		showRootLayout();
	}

	private void playSong(SongDTO song) {
		if (song.getPlaylist() == -1) {
			playlistPlaying = null;
		}
		songDtoPlaying = song;
		playStatus = songDtoPlaying.getName() + " - Playing";
		playStatusLabel.setText(playStatus);
		final File resource = new File(song.getPath());
		String tmpString = resource.toURI().toString();
		final Media media = new Media(tmpString);
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		addEndSongListener();
	}

	private void playSongClicked(SongDTO song) {
		System.out.println("Path: " + song.getPath());
		playSong(song);
		System.out.println("Playing...");
	}

	private void playPlaylistClicked(SongDTO song) {
		playPlaylist(song);
		System.out.println(song.getPlaylist());
		System.out.println("Playing...");
	}

	public void pauseSong() {
		if (mediaPlayer != null) {
			mediaPlayer.pause();
			playStatus = songDtoPlaying.getName() + " - Paused";
			playStatusLabel.setText(playStatus);
		}
	}

	public void playPausedSong() {
		if (mediaPlayer != null) {
			mediaPlayer.play();
			playStatus = songDtoPlaying.getName() + " - Playing";
			playStatusLabel.setText(playStatus);
		}
	}

	public void playPlaylist(SongDTO song) {
		playlistPlaying = library.getLibrary().getPlaylist(song.getPlaylist());
		playSong(song);
	}

	public void addEndSongListener(){
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				if (playlistPlaying != null) {
					ArrayList<Integer> songs = playlistPlaying.getSongs();
					for (int i : songs) {
						if (i == songDtoPlaying.getSongID()) {
							// NextTitle
							int nextSongId = playlistPlaying.getSongs().indexOf(i) + 1;
							SongDTO nextSong = library.getLibrary().getSong(playlistPlaying.getSongs().get(nextSongId));
							nextSong.setPlaylist(playlistPlaying.getPlaylistID());
							playSong(nextSong);
						}
					}
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}