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
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.scene.Parent;

/**
 * A controller class that handles events in the HomeMenu.
 * 
 * @author Lim Tze Yang
 */
public class HomeMenuController {
	
	String homeFXML = "../view/fxml/HomeMenu.fxml";
	String helpFXML = "../view/fxml/HelpMenu.fxml";
	String gameFXML = "../view/fxml/GameFrame.fxml";
	double fadetime = 250;
	
	@FXML private URL location;	
    @FXML private ResourceBundle resources;
	@FXML private StackPane parentcontainer;
	@FXML private GridPane homeroot;
	@FXML private Button play, help, exit;	
    
    public HomeMenuController() {}
    
    @FXML
    private void initialize() {}
    
    /**
     * Switches to the GameFrame when play button is pressed.
     * 
     * @throws IOException
     */
    @FXML
    private void playButtonPressed() throws IOException {
    	Parent gameroot = FXMLLoader.load(getClass().getResource(gameFXML));
    	FadeTransition fadeGame = new FadeTransition(Duration.millis(fadetime), gameroot);
    	FadeTransition fadeHome = new FadeTransition(Duration.millis(fadetime), homeroot);
    	
    	gameroot.setOpacity(0);
    	parentcontainer.getChildren().add(gameroot);
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
    	gameroot.requestFocus();
    	fadeHome.play();
    }
    
    /**
     * Switches to the HelpMenu when the help button is pressed.
     * 
     * @throws IOException
     */
    @FXML
    private void helpButtonPressed() throws IOException {
    	Parent helproot = FXMLLoader.load(getClass().getResource(helpFXML));
    	Button back = (Button)helproot.lookup("#back");
    	FadeTransition fadeHelp = new FadeTransition(Duration.millis(fadetime), helproot);
    	FadeTransition fadeHome = new FadeTransition(Duration.millis(fadetime), homeroot);
    	
    	helproot.setOpacity(0);
    	parentcontainer.getChildren().add(helproot);
    	fadeHelp.setFromValue(0);
    	fadeHelp.setToValue(1);
    	fadeHelp.setOnFinished(e -> {
    		back.requestFocus();
    		helproot.setOnKeyPressed(key -> {
        		if (key.getCode() == KeyCode.BACK_SPACE) {
        			if (parentcontainer.getChildren().contains(helproot)) {
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
	    		    		parentcontainer.getChildren().remove(helproot);
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
    	helproot.requestFocus();
    	fadeHome.play();
    }   
    
    /**
     * Exits the application when the exit button is pressed.
     */
    @FXML
    private void exitButtonPressed() {
    	System.out.println("Goodbye " + System.getProperty("user.name"));
    	System.exit(0);
    }    
}