package studiplayer.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import studiplayer.audio.AudioFile;
import studiplayer.audio.NotPlayableException;
import studiplayer.audio.PlayList;
import studiplayer.audio.SortCriterion;

import java.net.URL;

public class Player extends Application {
	//public static final String DEFAULT_PLAYLIST = "playlists/playList.cert.m3u";
	public static final String DEFAULT_PLAYLIST = "playlists/DefaultPlayList.m3u";
	private static final String PLAYLIST_DIRECTORY = "playlists/";
	private static final String INITIAL_PLAY_TIME_LABEL = "00:00";
	private static final String NO_CURRENT_SONG = "ncs";
	PlayerThread pt = new PlayerThread();
	TimerThread tt = new TimerThread();
	private boolean useCertPlayList = true;
	private PlayList playList = new PlayList();
	private Button playButton;
	private Button stopButton;
	private Button pauseButton;
	private Button nextButton;
	private Label playListLabel;
	private Label playTimeLabel;
	private Label currentSongLabel;
	private ChoiceBox<SortCriterion> sortChoiceBox;
	private TextField searchTextField;
	private Button filterButton;
	private String path;
	private SongTable st;
	private boolean curpl = false;
	
	public Player() {
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		loadPlayList(path);
		BorderPane mainPane = new BorderPane();
		BorderPane.setAlignment(mainPane, Pos.CENTER);
		TitledPane tp = new TitledPane();
		tp.setExpanded(false);
		GridPane gp = new GridPane();
		GridPane gp2 = new GridPane();
		GridPane gp3 = new GridPane();
		VBox vb = new VBox();
		sortChoiceBox = new ChoiceBox<SortCriterion>();
		sortChoiceBox.getItems().addAll(SortCriterion.DEFAULT, SortCriterion.AUTHOR, SortCriterion.TITLE, SortCriterion.ALBUM, SortCriterion.DURATION);
		filterButton = new Button("Ballers");
		searchTextField = new TextField();
		gp.add(sortChoiceBox, 0, 0);
		gp.add(searchTextField, 0, 1);
		gp.add(filterButton, 1, 1);
		gp.setMargin(sortChoiceBox, new Insets(5, 5, 5, 5));
		gp.setMargin(searchTextField, new Insets(5, 5, 5, 5));
		gp.setMargin(filterButton, new Insets(5, 5, 5, 5));
		if (playList.currentAudioFile() != null) {
			currentSongLabel = new Label(playList.currentAudioFile().getTitle());
		} else {
			currentSongLabel = new Label(NO_CURRENT_SONG);
		}
		if (playList.currentAudioFile() != null) {
			playTimeLabel = new Label(playList.currentAudioFile().formatDuration());
		} else {
			playTimeLabel = new Label(INITIAL_PLAY_TIME_LABEL);
		}
		playListLabel = new Label(path);
		gp2.add(currentSongLabel, 0, 0);
		gp2.add(playListLabel, 1, 0);
		gp2.add(playTimeLabel, 2, 0);
		gp2.add(new Label(path), 3, 0);
		playButton = createButton("play.jpg");
		pauseButton = createButton("pause.jpg");
		stopButton = createButton("stop.jpg");
		nextButton = createButton("next.jpg");
		gp3.add(playButton, 1, 0);
		gp3.add(stopButton, 0, 0);
		gp3.add(pauseButton, 2, 0);
		gp3.add(nextButton, 3, 0);
		gp3.setMargin(playButton, new Insets(5, 5, 5, 5));
		gp3.setMargin(stopButton, new Insets(5, 5, 5, 5));
		gp3.setMargin(pauseButton, new Insets(5, 5, 5, 5));
		gp3.setMargin(nextButton, new Insets(5, 5, 5, 5));
		gp3.setAlignment(Pos.CENTER);
		vb.getChildren().add(gp2);
		vb.getChildren().add(gp3);
		vb.setAlignment(Pos.CENTER);
		tp.setContent(gp);
		mainPane.setTop(tp);
		try {
			st = new SongTable(playList);
			mainPane.setCenter(st);
		} catch (Exception e) {
		}
		mainPane.setBottom(vb);
		stage.setTitle("APA Player");
		Scene scene = new Scene(mainPane, 600, 400);
		buttonSetUp();
		setButtonStates(true, false, false, true);
		stage.setScene(scene);
		stage.show();
	}
	
	public void loadPlayList(String path) {
		this.path = path;
		if (path != null) {
			playList.loadFromM3U(path);
		} else {
			playList.loadFromM3U(DEFAULT_PLAYLIST);
		}
	}
	
	private Button createButton(String iconfile) {
		Button button = null;
		try {
			URL url = getClass().getResource("/icons/" + iconfile);
			Image icon = new Image(url.toString());
			ImageView imageView = new ImageView(icon);
			imageView.setFitHeight(20);
			imageView.setFitWidth(20);
			button = new Button("", imageView);
			button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			button.setStyle("-fx-background-color: #fff;");
		} catch (Exception e) {
			System.out.println("Image " + "icons/"
					+ iconfile + " not found!");
			System.exit(-1);
		}
		return button;
	}
	
	private void buttonSetUp() {
		filterButton.setOnAction(e -> {
			playList.setSearch(searchTextField.getText());
			playList.setSortCriterion(sortChoiceBox.getValue());
			if (st != null) {
				st.refreshSongs();
			}
		});
		
		playButton.setOnAction(e -> {
			try {
				playCur();
			} catch (NotPlayableException ex) {
				throw new RuntimeException(ex);
			}
		});
		pauseButton.setOnAction(e -> {
			try {
				pause();
			} catch (NotPlayableException ex) {
				throw new RuntimeException(ex);
			}
		});
		stopButton.setOnAction(e -> {
			stopCur();
		});
		nextButton.setOnAction(e -> {
			try {
				next();
			} catch (NotPlayableException ex) {
				throw new RuntimeException(ex);
			}
		});
		
		if (st != null) {
			st.setRowSelectionHandler(e -> {
				playList.currentAudioFile().stop();
				playList.jumpToAudioFile(st.getSelectionModel().getSelectedItem().getAudioFile());
				try {
					playList.currentAudioFile().play();
				} catch (NotPlayableException ex) {
					throw new RuntimeException(ex);
				}
				curpl = true;
			});
		}
	}
	
	private void setButtonStates(boolean playButtonState, boolean pauseButtonState, boolean stopButtonState, boolean nextButtonState) {
		playButton.setDisable(!playButtonState);
		pauseButton.setDisable(!pauseButtonState);
		stopButton.setDisable(!stopButtonState);
		nextButton.setDisable(!nextButtonState);
	}
	
	public void playCur() throws NotPlayableException {
		setButtonStates(false, true, true, true);
		curpl = true;
		startThreads(false);
	}
	
	public void pause() throws NotPlayableException {
		if (curpl) {
			curpl = false;
			playList.currentAudioFile().togglePause();
			terminateThreads(true);
			setButtonStates(false, true, true, true);
		} else {
			curpl = true;
			startThreads(true);
		}
	}
	
	public void stopCur() {
		curpl = false;
		terminateThreads(false);
		playList.currentAudioFile().stop();
		setButtonStates(true, false, false, true);
	}
	
	public void next() throws NotPlayableException {
		curpl = false;
		terminateThreads(false);
		playList.currentAudioFile().stop();
		setButtonStates(true, false, false, true);
		playList.nextSong();
		startThreads(true);
		playCur();
	}
	
	private void startThreads(boolean onlyTimer) {
		tt = new TimerThread();
		tt.start();
		if (!onlyTimer) {
			pt = new PlayerThread();
			pt.start();
		}
	}
	
	private void terminateThreads(boolean onlyTimer) {
		if (tt != null) {
			tt.terminate();
			tt = null;
		}
		if (!onlyTimer) {
			if (pt != null) {
				pt.terminate();
				pt = null;
			}
		}
	}
	
	private void updateSongInfo(AudioFile af) {
		Platform.runLater(() -> {
			if (af == null) {
				currentSongLabel.setText(NO_CURRENT_SONG);
				playTimeLabel.setText(INITIAL_PLAY_TIME_LABEL);
			} else {
				currentSongLabel.setText(af.getTitle());
				playTimeLabel.setText(playList.currentAudioFile().formatPosition());
			}
		});
	}
	
	public void setUseCertPlayList(boolean b) {
		useCertPlayList = b;
		if (useCertPlayList) {
			playList.loadFromM3U(DEFAULT_PLAYLIST);
		}
	}
	
	public void setPlayList(PlayList e) {
		playList = e;
	}
	
	private class PlayerThread extends Thread {
		private boolean stopped = false;
		
		public PlayerThread() {
		}
		
		public void run() {
			while (!stopped) {
				if (st != null) {
					st.selectSong(playList.currentAudioFile());
				}
				try {
					playList.currentAudioFile().play();
				} catch (NotPlayableException e) {
					throw new RuntimeException(e);
				}
				if (!stopped) {
					playList.nextSong();
					updateSongInfo(playList.currentAudioFile());
					setButtonStates(true, false, false, true);
				}
			}
		}
		
		public void terminate() {
			stopped = true;
		}
	}
	
	private class TimerThread extends Thread {
		boolean stopped;
		
		@Override
		public void run() {
			while (!stopped) {
				updateSongInfo(playList.currentAudioFile());
				try {
					sleep(100);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		public void terminate() {
		
		}
	}
}