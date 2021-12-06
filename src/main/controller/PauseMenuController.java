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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * A controller class that handles events in the PauseMenu.
 * 
 * @author Lim Tze Yang
 */
public class PauseMenuController {
	
	@FXML private URL location;	
    @FXML private ResourceBundle resources;
	@FXML private GridPane pauseroot;
	@FXML private Button resume, restart, quit;	
    
    public PauseMenuController() {}
    
    @FXML
    private void initialize() {}
    
    /**
     * Resumes the game and removes the PauseMenu when the resume button is pressed.
     */
    @FXML
    private void resumeButtonPressed() {
    	StackPane gameroot = (StackPane)pauseroot.getParent();
    	gameroot.getChildren().remove(pauseroot);    	
    }
    
    /**
     * Restarts the current level of the game and removes the PauseMenu.
     */
    @FXML
    private void restartButtonPressed() {
    	StackPane gameroot = (StackPane)pauseroot.getParent();
    	pauseroot.setUserData("Restart");
    	gameroot.getChildren().remove(pauseroot);
    }
    
    /**
     * Exits the application when the quit button is pressed.
     */
    @FXML
    private void quitButtonPressed() {
    	System.out.println("Goodbye " + System.getProperty("user.name"));
    	Platform.exit();
    }
}