package studiplayer.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import studiplayer.audio.PlayList;

import java.net.URL;

public class Player extends Application {
	public static String DEFAULT_PLAYLIST = "playlists/playList.cert.m3u";
	private static String PLAYLIST_DIRECTORY;
	private static String INITIAL_PLAY_TIME_LABEL;
	private static String NO_CURRENT_SONG;
	
	private boolean useCertPlayList = true;
	private PlayList playList = new PlayList();
	private Button playButton;
	;
	private Button stopButton;
	private Button pauseButton;
	private Button nextButton;
	private Label playListLabel;
	private Label playTimeLabel;
	private Label currentSongLabel;
	private ChoiceBox sortChoiceBox;
	private TextField searchTextField;
	private Button filterButton;
	private String path;
	
	public Player() {
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		loadPlayList(path);
		BorderPane mainPane = new BorderPane();
		TitledPane tp = new TitledPane();
		GridPane gp = new GridPane();
		GridPane gp2 = new GridPane();
		GridPane gp3 = new GridPane();
		VBox vb = new VBox();
		sortChoiceBox = new ChoiceBox<>();
		filterButton = new Button("Ballers");
		searchTextField = new TextField();
		gp.add(sortChoiceBox, 0, 0);
		gp.add(searchTextField, 1, 0);
		gp.add(filterButton, 2, 0);
		currentSongLabel = new Label(playList.currentAudioFile().getTitle());
		playTimeLabel = new Label(playList.currentAudioFile().formatDuration());
		playListLabel = new Label(path);
		gp2.add(currentSongLabel, 0, 0);
		gp2.add(playListLabel, 1, 0);
		gp2.add(playTimeLabel, 2, 0);
		playButton = createButton("play.jpg");
		pauseButton = createButton("pause.jpg");
		stopButton = createButton("stop.jpg");
		nextButton = createButton("next.jpg");
		gp3.add(playButton, 0, 0);
		gp3.add(stopButton, 1, 0);
		gp3.add(pauseButton, 2, 0);
		gp3.add(nextButton, 3, 0);
		vb.getChildren().add(gp2);
		vb.getChildren().add(gp3);
		tp.setContent(gp);
		SongTable st;
		mainPane.setTop(tp);
		try {
			st = new SongTable(playList);
			mainPane.setCenter(st);
		} catch (Exception e) {
		}
		mainPane.setBottom(vb);
		stage.setTitle("Balls");
		Scene scene = new Scene(mainPane, 600, 400);
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
	
	public void setUseCertPlayList(boolean b) {
		useCertPlayList = b;
	}
	
	public void setPlayList(PlayList e) {
		playList = e;
	}
}