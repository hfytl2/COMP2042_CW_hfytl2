package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.Parent;

/**
 * Entry point and application launcher for the game.
 * 
 * @author Lim Tze Yang
 */
public class BrickDestroy extends Application {	
	
	/**
	 * Main driver method to launch the Brick Destroy game application.
	 * @param args Unused arguments.
	 */
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("view/fxml/HomeMenu.fxml"));
		Font.loadFont(getClass().getResourceAsStream("assets/ARCADE_N.TTF"), 10);
		primaryStage.centerOnScreen();
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/icon.jpg")));
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setTitle("Brick Destroy");
		primaryStage.setScene(new Scene(root, 600, 450));
		primaryStage.setResizable(false);
		primaryStage.setOnHiding(hiding -> {
			Stage newstage = new Stage();
			newstage.initOwner(primaryStage);
			newstage.setX(primaryStage.getX());
			newstage.setY(primaryStage.getY());
	    	newstage.getIcons().add(new Image(getClass().getResourceAsStream("assets/icon.jpg")));
	    	newstage.setTitle("Brick Destroy");
	    	newstage.setScene(primaryStage.getScene());
	    	newstage.setResizable(false);
	    	newstage.setOnHiding(hidegame -> {
	    		primaryStage.setScene(newstage.getScene());
	    		primaryStage.show();
	    	});
	    	newstage.setOnCloseRequest(closegame -> {
	    		Platform.exit();
	    	});
	    	newstage.show();
		});
		primaryStage.show();
	}
}