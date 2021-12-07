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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * A controller class that handles events in the HelpMenu.
 * Note: Added this controller to adhere to MVC pattern and apply SOLID principles.
 * 
 * @author Lim Tze Yang
 */
public class HelpMenuController {
	
	String homeFXML = "../view/fxml/HomeMenu.fxml";
	double fadetime = 250;
	
	@FXML private URL location;	
    @FXML private ResourceBundle resources;
	@FXML private GridPane helproot;
	@FXML private Button back;	
    
	/**
	 * Creates a new instance of HelpMenuController.
	 */
    public HelpMenuController() {}
    
    @FXML
    private void initialize() {}
	
    /**
     * Switches to the HomeMenu when back button is pressed.
     * 
     * @throws IOException
     */
	@FXML
    private void backButtonPressed() throws IOException {
    	Scene scene = back.getScene();
    	StackPane parentcontainer = (StackPane)scene.getRoot();
    	GridPane homeroot = (GridPane)parentcontainer.lookup("#homeroot");
    	Button play = (Button)homeroot.lookup("#play");
    	Button help = (Button)homeroot.lookup("#help");
    	Button exit = (Button)homeroot.lookup("#exit");
    	FadeTransition fadeHome = new FadeTransition(Duration.millis(fadetime), homeroot);
    	FadeTransition fadeHelp = new FadeTransition(Duration.millis(fadetime), helproot);
    	
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
    		parentcontainer.getChildren().remove(helproot);
    	});
    	parentcontainer.requestFocus();
    	fadeHelp.play();
    }
}