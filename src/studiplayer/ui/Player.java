package studiplayer.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Player extends Application {
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
}