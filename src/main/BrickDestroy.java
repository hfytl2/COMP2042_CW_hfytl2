package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;

/**
 * Entry point for the game.
 * 
 * @author Lim Tze Yang
 */
public class BrickDestroy extends Application {	
	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("view/fxml/HomeMenu.fxml"));
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/icon.jpg")));
		primaryStage.setTitle("Brick Destroy");
		primaryStage.setScene(new Scene(root, 600, 450));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}