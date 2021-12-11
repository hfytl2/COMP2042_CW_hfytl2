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

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;

/**
 * A controller class that handles events in the PauseMenu.
 * Note: Added this controller to adhere to MVC pattern and apply SOLID principles.
 * 
 * @author Lim Tze Yang
 */
public class PauseMenuController {
	
	AudioClip buttonPressedSFX = null;
	
	@FXML private URL location;	
    @FXML private ResourceBundle resources;
	@FXML private GridPane pauseRoot;
	@FXML private Button resume, restart, quit;	
    
	/**
	 * Creates a new instance of PauseMenuController.
	 */
    public PauseMenuController() {}
    
    @FXML
    private void initialize() {
    	try {
			buttonPressedSFX = new AudioClip(getClass().getClassLoader().getResource("main/assets/buttonpress.mp3").toURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    	resume.focusedProperty().addListener((observableVal, oldVal, newVal) -> {
    		if (newVal) {
    			buttonOnFocusHover(resume);
    		} else {
    			buttonOnUnfocusUnhover(resume);
    		}
    	});
    	resume.setOnMouseEntered(e -> {
    		buttonOnFocusHover(resume);
    	});
    	resume.setOnMouseExited(e -> {
    		buttonOnUnfocusUnhover(resume);
    	});
    	restart.focusedProperty().addListener((observableVal, oldVal, newVal) -> {
    		if (newVal) {
    			buttonOnFocusHover(restart);
    		} else {
    			buttonOnUnfocusUnhover(restart);
    		}
    	});
    	restart.setOnMouseEntered(e -> {
    		buttonOnFocusHover(restart);
    	});
    	restart.setOnMouseExited(e -> {
    		buttonOnUnfocusUnhover(restart);
    	});
    	quit.focusedProperty().addListener((observableVal, oldVal, newVal) -> {
    		if (newVal) {
    			buttonOnFocusHover(quit);
    		} else {
    			buttonOnUnfocusUnhover(quit);
    		}
    	});
    	quit.setOnMouseEntered(e -> {
    		buttonOnFocusHover(quit);
    	});
    	quit.setOnMouseExited(e -> {
    		buttonOnUnfocusUnhover(quit);
    	});
    }
    
    /**
     * Resumes the game and removes the PauseMenu when the resume button is pressed.
     */
    @FXML
    private void resumeButtonPressed() {
    	buttonPressedSFX.play();
    	StackPane gameroot = (StackPane)pauseRoot.getParent();
    	gameroot.getChildren().remove(pauseRoot);    	
    }
    
    /**
     * Restarts the current level of the game and removes the PauseMenu.
     */
    @FXML
    private void restartButtonPressed() {
    	buttonPressedSFX.play();
    	StackPane gameroot = (StackPane)pauseRoot.getParent();
    	pauseRoot.setUserData("Restart");
    	gameroot.getChildren().remove(pauseRoot);
    }
    
    /**
     * Exits the application when the quit button is pressed.
     */
    @FXML
    private void quitButtonPressed() {
    	buttonPressedSFX.play();
    	System.out.println("Goodbye " + System.getProperty("user.name"));
    	Platform.exit();
    }
    
    private void buttonOnFocusHover(Button button) {
    	button.setStyle("-fx-text-fill: lime;-fx-border-style: solid;-fx-border-color: lime;-fx-label-padding: 3px;");
    }
    
    private void buttonOnUnfocusUnhover(Button button) {
    	if (!button.isFocused()) {
    		button.setStyle("-fx-text-fill: white;-fx-border-color: black;-fx-label-padding: 0;");
    	}
    }
}