/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2021  Lim Tze Yang
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package main.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.model.Game;

/**
 * A controller class that handles events in the HighScoreMenu.
 * Note: Added this controller to adhere to MVC pattern and apply SOLID principles.
 * 
 * @author Lim Tze Yang
 */
public class HighScoreMenuController {
	
	AudioClip buttonPressedSFX = new AudioClip(new File("src/main/assets/buttonpress.mp3").toURI().toString());
	String gameFXML = "../view/fxml/GameFrame.fxml";
	String homeFXML = "../view/fxml/HomeMenu.fxml";
	static final double FADE_TIME = 250;
	
	@FXML private URL location;	
    @FXML private ResourceBundle resources;
	@FXML private GridPane highScoreRoot, leaderboardContainer;
	@FXML private Text title;
	@FXML private VBox heading;
	@FXML private ScrollPane leaderboard;
	@FXML private Button btnTryAgain, btnMainMenu;
    
	/**
	 * Creates a new instance of HighScoreMenuController.
	 */
    public HighScoreMenuController() {}
    
    @FXML
    private void initialize() {
    	File highScoreFile = new File("src/main/highscore.txt"); 	
    	
    	try (Scanner fscanner = new Scanner(highScoreFile)) {    		
    		Text ranking, name, score;
    		int rank = 1;
    		
    		while (fscanner.hasNextLine()) {
    			String[] rawdata = fscanner.nextLine().split(",");
    			ranking = new Text(Integer.toString(rank));
    			ranking.getStyleClass().add("leaderboardtext");
    			name = new Text(rawdata[0]);
    			name.getStyleClass().add("leaderboardtext");
    			score = new Text(rawdata[1]);
    			score.getStyleClass().add("leaderboardtext");
    			leaderboardContainer.addRow(rank, ranking, name, score);
    			rank++;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Brings player back to game frame and restarts the game when try again button is pressed.
     */
    @FXML
    private void tryAgainButtonPressed() {
    	buttonPressedSFX.play();
    	Parent gameRoot = null;
    	Game.getGame().restartGame();
    	
    	try {
    		gameRoot = FXMLLoader.load(getClass().getResource(gameFXML));
    		FadeTransition fadeGame = new FadeTransition(Duration.millis(FADE_TIME), gameRoot);
    		FadeTransition fadeHighScore = new FadeTransition(Duration.millis(FADE_TIME), highScoreRoot);
    		gameRoot.setOpacity(0);
	    	((StackPane)highScoreRoot.getParent()).getChildren().add(gameRoot);    	
	    	fadeGame.setFromValue(0);
	    	fadeGame.setToValue(1);
	    	fadeHighScore.setFromValue(1);
	    	fadeHighScore.setToValue(0);
	    	fadeHighScore.setOnFinished(e -> {
	    		fadeGame.play();
	    	});
	    	gameRoot.requestFocus();
	    	fadeHighScore.play();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}    	
    }
    
    /**
     * Brings player back to main menu after main menu button is pressed.
     */
    @FXML
    private void mainMenuButtonPressed(ActionEvent event) {
    	buttonPressedSFX.play();
    	GridPane homeRoot = (GridPane)highScoreRoot.getParent().lookup("#homeRoot");    	
    	FadeTransition fadeHome = new FadeTransition(Duration.millis(FADE_TIME), homeRoot);
		FadeTransition fadeHighScore = new FadeTransition(Duration.millis(FADE_TIME), highScoreRoot);
		fadeHome.setFromValue(0);
    	fadeHome.setToValue(1);
    	fadeHome.setOnFinished(e -> {
    		homeRoot.lookup("#play").setFocusTraversable(true);
    		homeRoot.lookup("#help").setFocusTraversable(true);
    		homeRoot.lookup("#exit").setFocusTraversable(true);
    		homeRoot.lookup("#play").requestFocus();
    		Game.getGame().restartGame();
    	});
    	fadeHighScore.setFromValue(1);
    	fadeHighScore.setToValue(0);
    	fadeHighScore.setOnFinished(e -> {
    		fadeHome.play();
    		((StackPane)highScoreRoot.getParent()).getChildren().remove(highScoreRoot);
    	});
    	homeRoot.requestFocus();
    	fadeHighScore.play();
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }
}