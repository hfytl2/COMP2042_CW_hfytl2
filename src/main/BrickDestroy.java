package main;

import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.Parent;

/**
 * Entry point and application launcher for the game.
 * 
 * @author Lim Tze Yang
 */
public class BrickDestroy extends Application {
	
	Media bgm = new Media(getClass().getResource("assets/backgroundmusic.mp3").toURI().toString());
	MediaPlayer bgmPlayer = new MediaPlayer(bgm);
	
	/**
	 * Empty constructor class to throw URISyntaxException.
	 * @throws URISyntaxException Background music parsing exception.
	 */
	public BrickDestroy() throws URISyntaxException {}
	
	/**
	 * Main driver method to launch the Brick Destroy game application.
	 * @param args Unused arguments.
	 */
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) {
		Parent root = null;		
		bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		bgmPlayer.setAutoPlay(true);
		
		try {
			root = FXMLLoader.load(getClass().getResource("view/fxml/HomeMenu.fxml"));
			Font.loadFont(getClass().getResourceAsStream("assets/ARCADE_N.TTF"), 10);
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/icon.jpg")));
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setTitle("Brick Destroy");
			primaryStage.setScene(new Scene(root, 600, 450));
			primaryStage.setResizable(false);
			primaryStage.setOnHiding(hiding -> {
				Stage newStage = new Stage();
				newStage.initOwner(primaryStage);
				newStage.setX(primaryStage.getX());
				newStage.setY(primaryStage.getY());
		    	newStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/icon.jpg")));
		    	newStage.setTitle("Brick Destroy");
		    	newStage.setScene(primaryStage.getScene());
		    	newStage.setResizable(false);
		    	newStage.setOnHiding(hidingGame -> {
		    		primaryStage.setScene(((Stage)hidingGame.getSource()).getScene());		    		
		    		primaryStage.show();
		    	});
		    	newStage.setOnCloseRequest(closegame -> {
		    		Platform.exit();
		    	});
		    	newStage.focusedProperty().addListener((observableVal, oldVal, newVal) -> {
					if (newVal) {
						if (bgmPlayer.getStatus().equals(MediaPlayer.Status.PAUSED) || bgmPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
							bgmPlayer.play();
						}						
					} else {
						if (bgmPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
							bgmPlayer.pause();
						}						
					}
				});
		    	newStage.show();
			});
			primaryStage.focusedProperty().addListener((observableVal, oldVal, newVal) -> {
				if (newVal) {
					if (bgmPlayer.getStatus().equals(MediaPlayer.Status.PAUSED) || bgmPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
						bgmPlayer.play();
					}						
				} else {
					if (bgmPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
						bgmPlayer.pause();
					}						
				}
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {
		if (!bgmPlayer.getStatus().equals(MediaPlayer.Status.STOPPED)) {
			bgmPlayer.stop();
		}		
	}
}