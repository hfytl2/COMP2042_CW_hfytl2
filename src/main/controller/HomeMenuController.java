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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * A controller class that handles events in the HomeMenu.
 * Note: Added this controller to adhere to MVC pattern and apply SOLID principles.
 * 
 * @author Lim Tze Yang
 */
public class HomeMenuController {
	
	AudioClip buttonPressedSFX = new AudioClip(new File("src/main/assets/buttonpress.mp3").toURI().toString());
	String homeFXML = "../view/fxml/HomeMenu.fxml";
	String helpFXML = "../view/fxml/HelpMenu.fxml";
	String gameFXML = "../view/fxml/GameFrame.fxml";
	static final double FADE_TIME = 250;	
	
	@FXML private URL location;	
    @FXML private ResourceBundle resources;
	@FXML private StackPane parentContainer;
	@FXML private GridPane homeRoot;
	@FXML private Button play, help, exit;	
    
	/**
	 * Creates a new instance of HomeMenuController.
	 */
    public HomeMenuController() {}
    
    @FXML
    private void initialize() {}
    
    /**
     * Switches to the GameFrame when play button is pressed.
     */
    @FXML
    private void playButtonPressed(ActionEvent event) {
    	buttonPressedSFX.play();
    	Parent gameRoot = null;
    	
    	try {
	    	gameRoot = FXMLLoader.load(getClass().getResource(gameFXML));
	    	FadeTransition fadeGame = new FadeTransition(Duration.millis(FADE_TIME), gameRoot);
	    	FadeTransition fadeHome = new FadeTransition(Duration.millis(FADE_TIME), homeRoot);
	    	gameRoot.setOpacity(0);
	    	parentContainer.getChildren().add(gameRoot);    	
	    	fadeGame.setFromValue(0);
	    	fadeGame.setToValue(1);
	    	fadeHome.setFromValue(1);
	    	fadeHome.setToValue(0);
	    	fadeHome.setOnFinished(e -> {
	    		play.setFocusTraversable(false);
	    		help.setFocusTraversable(false);
	    		exit.setFocusTraversable(false);
	    		fadeGame.play();    		
	    	});
	    	gameRoot.requestFocus();
	    	fadeHome.play();
	    	((Node)(event.getSource())).getScene().getWindow().hide();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Switches to the HelpMenu when the help button is pressed.
     * 
     * @throws IOException
     */
    @FXML
    private void helpButtonPressed() throws IOException {
    	buttonPressedSFX.play();
    	Parent helpRoot = FXMLLoader.load(getClass().getResource(helpFXML));
    	FadeTransition fadeHelp = new FadeTransition(Duration.millis(FADE_TIME), helpRoot);
    	FadeTransition fadeHome = new FadeTransition(Duration.millis(FADE_TIME), homeRoot);
    	
    	helpRoot.setOpacity(0);
    	parentContainer.getChildren().add(helpRoot);
    	fadeHelp.setFromValue(0);
    	fadeHelp.setToValue(1);
    	fadeHelp.setOnFinished(e -> {
    		helpRoot.lookup("#back").requestFocus();
    		helpRoot.setOnKeyPressed(key -> {
        		if (key.getCode() == KeyCode.BACK_SPACE) {
        			if (parentContainer.getChildren().contains(helpRoot)) {
	    		    	fadeHome.setFromValue(0);
	    		    	fadeHome.setToValue(1);
	    		    	fadeHome.setOnFinished(e1 -> {
	    		    		play.setFocusTraversable(true);
	    		    		help.setFocusTraversable(true);
	    		    		exit.setFocusTraversable(true);
	    		    		play.requestFocus();
	    		    	});
	    		    	fadeHelp.setFromValue(1);
	    		    	fadeHelp.setToValue(0);
	    		    	fadeHelp.setOnFinished(e1 -> {
	    		    		fadeHome.play();
	    		    		parentContainer.getChildren().remove(helpRoot);
	    		    	});
	    		    	fadeHelp.play();
        			}
        	    }
        	});
    	});
    	fadeHome.setFromValue(1);
    	fadeHome.setToValue(0);
    	fadeHome.setOnFinished(e -> {
    		play.setFocusTraversable(false);
    		help.setFocusTraversable(false);
    		exit.setFocusTraversable(false);
    		fadeHelp.play();
    	});
    	helpRoot.requestFocus();
    	fadeHome.play();
    }   
    
    /**
     * Exits the application when the exit button is pressed.
     */
    @FXML
    private void exitButtonPressed() {
    	buttonPressedSFX.play();
    	System.out.println("Goodbye " + System.getProperty("user.name"));
    	Platform.exit();
    }    
}