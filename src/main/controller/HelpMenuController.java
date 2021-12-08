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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

/**
 * A controller class that handles events in the HelpMenu.
 * Note: Added this controller to adhere to MVC pattern and apply SOLID principles.
 * 
 * @author Lim Tze Yang
 */
public class HelpMenuController {
	
	AudioClip buttonPressedSFX = new AudioClip(getClass().getResource("../assets/buttonpress.mp3").toString());
	String homeFXML = "../view/fxml/HomeMenu.fxml";
	double fadetime = 250;
	
	@FXML private URL location;	
    @FXML private ResourceBundle resources;
	@FXML private GridPane helpRoot;
	@FXML private Button back;	
    
	/**
	 * Creates a new instance of HelpMenuController.
	 */
    public HelpMenuController() {}
    
    @FXML
    private void initialize() {}
	
    /**
     * Switches to the HomeMenu when back button is pressed.
     */
	@FXML
    private void backButtonPressed() {
		buttonPressedSFX.play();
    	Scene scene = back.getScene();
    	StackPane parentContainer = (StackPane)scene.getRoot();
    	GridPane homeRoot = (GridPane)parentContainer.lookup("#homeRoot");
    	Button play = (Button)homeRoot.lookup("#play");
    	Button help = (Button)homeRoot.lookup("#help");
    	Button exit = (Button)homeRoot.lookup("#exit");
    	FadeTransition fadeHome = new FadeTransition(Duration.millis(fadetime), homeRoot);
    	FadeTransition fadeHelp = new FadeTransition(Duration.millis(fadetime), helpRoot);
    	
    	fadeHome.setFromValue(0);
    	fadeHome.setToValue(1);
    	fadeHome.setOnFinished(e -> {
    		play.setFocusTraversable(true);
    		help.setFocusTraversable(true);
    		exit.setFocusTraversable(true);
    		play.requestFocus();
    	});
    	fadeHelp.setFromValue(1);
    	fadeHelp.setToValue(0);
    	fadeHelp.setOnFinished(e -> {
    		fadeHome.play();
    		parentContainer.getChildren().remove(helpRoot);
    	});
    	parentContainer.requestFocus();
    	fadeHelp.play();
    }
}