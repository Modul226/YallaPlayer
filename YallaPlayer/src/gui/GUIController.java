/**
 * @author erflo
 */

package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import business.Library;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
	private Pane settings;
	private String newPlaylistName;
	private Library library = new Library();

	@FXML
	private TextField playlistNameInput;
	@FXML
	private TextField searchInput;
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
	@FXML
	private ColorPicker colorPicker;
	@FXML
	private TabPane tabPaneLibrary;
	@FXML
	private Slider volumeSlider;
	@FXML
	private Label addPlaylistNameErrorLabel;

	private MediaPlayer mediaPlayer = null;
	private SongDTO songDtoPlaying = null;
	private String playStatus = null;
	private PlaylistDTO playlistPlaying = null;
	public javafx.scene.paint.Color accentColor;
	private ObservableList<SongDTO> titlesListForSearch = null;
	private ObservableList<TitledPane> albumsListForSearch = null;
	private ObservableList<TitledPane> interpretsListForSearch = null;
	private double volume = 0.5;

	public void showRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(this);
			loader.setLocation(GUIController.class.getResource("/gui/RootView.fxml"));
			rootLayout = (VBox) loader.load();

			if (accentColor != null) {
				rootLayout.setStyle("-fx-background-color: #" + Integer.toHexString(accentColor.hashCode()) + ";");
			}

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

	public void showSettingsView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(this);
			loader.setLocation(GUIController.class.getResource("/gui/Settings.fxml"));
			settings = (Pane) loader.load();

			Scene scene = new Scene(settings);
			primaryStage.setScene(scene);
			primaryStage.show();
			if (accentColor != null) {
				colorPicker.setValue(accentColor);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadDataIntoView() {

		ObservableList<TitledPane> playlistsListForView = FXCollections.observableArrayList();
		ObservableList<SongDTO> titlesListForView = FXCollections.observableArrayList();
		ObservableList<TitledPane> albumsListForView = FXCollections.observableArrayList();
		ObservableList<TitledPane> interpretsListForView = FXCollections.observableArrayList();

		TitledPane titledPaneToExpand = null;

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

				if (playlistPlaying != null) {
					if (playlist.getPlaylistID() == playlistPlaying.getPlaylistID()) {
						titledPaneToExpand = tp;
					}
				}
				playlistsListForView.add(tp);
			}
			accordionForPlaylists.getPanes().addAll(playlistsListForView);
			accordionForPlaylists.setExpandedPane(titledPaneToExpand);

		} else {
			accordionForPlaylists.setDisable(true);
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

		addTabChangeListener();
		addVolumeSliderListener();

		volumeSlider.setValue(volume * 100);

		titlesList.setItems(titlesListForView);
		addListenerOnListView(titlesList);
		albumsList.setItems(albumsListForView);
		interpretsList.setItems(interpretsListForView);

		interpretsListForSearch = interpretsListForView;
		albumsListForSearch = albumsListForView;
		titlesListForSearch = titlesListForView;

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

	private void addTabChangeListener() {
		tabPaneLibrary.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				titlesList.setItems(titlesListForSearch);
				albumsList.setItems(albumsListForSearch);
				interpretsList.setItems(interpretsListForSearch);
				searchInput.clear();
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
		newPlaylistName = newPlaylistName.trim();
		if(!newPlaylistName.equals("")){
			showAddPlaylistAddTitlesDialog();
		} else {
			addPlaylistNameErrorLabel.setText("Please enter a playlist-name");
		}

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

	public void savePickedColor() {
		accentColor = colorPicker.getValue();
		System.out.println(accentColor.toString());

	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("YallaPlayer");
		showRootLayout();
	}

	public void playlistAdd() {
		ObservableList<SongDTO> clickedSongs = selectTitlesForPlaylistList.getSelectionModel().getSelectedItems();
		if(!clickedSongs.isEmpty()){
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

	}

	public void removeTitleFromPlaylist() {
		TitledPane expandedPane = accordionForPlaylists.getExpandedPane();
		if (expandedPane != null) {
			@SuppressWarnings("unchecked")
			ListView<SongDTO> lv = (ListView<SongDTO>) expandedPane.getContent();
			SongDTO clickedSong = lv.getSelectionModel().getSelectedItem();
			if (clickedSong != null) {
				library.removeTitleFromPlaylist(clickedSong.getPlaylist(), clickedSong.getSongID());

				library.writeContainerToXML();
				library.readLibrary();
				showRootLayout();
			}
		}
	}

	public void removeFullPlaylist() {
		TitledPane expandedPane = accordionForPlaylists.getExpandedPane();
		if (expandedPane != null) {
			@SuppressWarnings("unchecked")
			ListView<SongDTO> lv = (ListView<SongDTO>) expandedPane.getContent();
			SongDTO clickedSong = lv.getSelectionModel().getSelectedItem();
			if (clickedSong != null) {
				library.removePlaylist(clickedSong.getPlaylist());
				library.writeContainerToXML();
				library.readLibrary();
				if (playlistPlaying.getPlaylistID() == clickedSong.getPlaylist()) {
					playlistPlaying = null;
				}
				showRootLayout();
			}
		}
	}

	private void playSong(SongDTO song) {
		if (song.getPlaylist() == -1) {
			playlistPlaying = null;
		}
		songDtoPlaying = song;
		playStatus = songDtoPlaying.getName() + " - Playing";
		playStatusLabel.setText(playStatus);
		File resource = new File(song.getPath());
		String tmpString = resource.toURI().toString();
		Media media = new Media(tmpString);
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(volume);
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

	public void searchActiveTab() {

		Tab tabActive = tabPaneLibrary.getTabs().get(tabPaneLibrary.getSelectionModel().getSelectedIndex());

		if (tabActive.getText().equals("Titles")) {
			ObservableList<SongDTO> filterListTitles = titlesListForSearch;
			titlesList.setItems(filterListTitles.filtered(
					SongDTO -> SongDTO.getName().toUpperCase().contains(searchInput.getText().toUpperCase())));
		}
		if (tabActive.getText().equals("Albums")) {
			ObservableList<TitledPane> filterListAlbums = albumsListForSearch;
			albumsList.setItems(filterListAlbums.filtered(
					TitledPane -> TitledPane.getText().toUpperCase().contains(searchInput.getText().toUpperCase())));
		}

		if (tabActive.getText().equals("Artists")) {
			ObservableList<TitledPane> filterListArtists = interpretsListForSearch;
			interpretsList.setItems(filterListArtists.filtered(
					TitledPane -> TitledPane.getText().toUpperCase().contains(searchInput.getText().toUpperCase())));
		}

	}

	public void playPlaylist(SongDTO song) {
		playlistPlaying = library.getLibrary().getPlaylist(song.getPlaylist());
		playSong(song);
	}

	public void addEndSongListener() {
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				if (playlistPlaying != null) {
					int nextSongIndex = playlistPlaying.getSongs().indexOf(songDtoPlaying.getSongID()) + 1;
					if (playlistPlaying.getSongs().size() >= nextSongIndex + 1) {
						SongDTO nextSong = library.getLibrary().getSong(playlistPlaying.getSongs().get(nextSongIndex));
						nextSong.setPlaylist(playlistPlaying.getPlaylistID());
						playSong(nextSong);
					}
				} else {
					SongDTO lastSongFromLibrary = library.getLibrary().getSong(songDtoPlaying.getSongID());
					int nextSongIndex = library.getLibrary().getSongs().indexOf(lastSongFromLibrary) + 1;

					if (library.getLibrary().getSongs().size() >= nextSongIndex + 1) {
						SongDTO nextSong = library.getLibrary().getSong(nextSongIndex);
						playSong(nextSong);
					}
				}
			}
		});
	}

	public void addVolumeSliderListener() {
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable ov) {
				if (volumeSlider.isValueChanging()) {
					volume = volumeSlider.getValue() / 100.0;
					if(mediaPlayer != null){
						mediaPlayer.setVolume(volume);
					}
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}