package studiplayer.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import studiplayer.audio.PlayList;

public class Player extends Application {
	public static String DEFAULT_PLAYLIST = "playlists/DefaultPlayList.m3u";
	private boolean useCertPlayList = true;
	private PlayList playList;
	
	public Player() {
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane mainPane = new BorderPane();
		stage.setTitle("Balls");
		Scene scene = new Scene(mainPane, 600, 400);
		stage.setScene(scene);
		stage.show();
	}
	
	public void loadPlayList(String path) {
		if (path != null) {
			playList.loadFromM3U(path);
		} else {
			playList.loadFromM3U(DEFAULT_PLAYLIST);
		}
	}
	
	public void setUseCertPlayList(boolean b) {
		useCertPlayList = b;
	}
	
	public void setPlayList(PlayList e) {
		playList = e;
	}
}