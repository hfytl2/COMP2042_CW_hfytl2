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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.util.Pair;
import main.model.Player;

/**
 * A controller class that handles events in the GameOverMenu.
 * Note: Added this controller to adhere to MVC pattern and apply SOLID principles.
 * 
 * @author Lim Tze Yang
 */
public class GameOverMenuController {	
	
	AudioClip buttonPressedSFX = null;
	
	@FXML private URL location;
    @FXML private ResourceBundle resources;
	@FXML private GridPane gameOverRoot;
	@FXML private VBox inputContainer, inputHeading, inputFieldContainer;
	@FXML private Text title, subtitle;
	@FXML private Label inputFieldLabel;
	@FXML private TextField inputField;
	@FXML private Button btnInputSubmit;	
    
	/**
	 * Creates a new instance of GameOverMenuController.
	 */
    public GameOverMenuController() {}
    
    @FXML
    private void initialize() {
    	try {
    		buttonPressedSFX = new AudioClip(getClass().getClassLoader().getResource("main/assets/buttonpress.mp3").toURI().toString());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	subtitle.setText("Your score: " + Player.getPlayer().getScore());
    }
    
    /**
     * Save player score and name from input to {@code highscore.txt} and show HighScoreMenu when submit button is pressed.
     */
    @FXML
    private void submitButtonPressed() throws URISyntaxException {    	
    	buttonPressedSFX.play();
    	Player.getPlayer().setName(inputField.getText());
    	Parent root = null;
    	File highScoreFile = new File("highscore.txt");
    	
    	try {    		    	
    		if (highScoreFile.createNewFile()) {
    			FileWriter fwriter = new FileWriter(highScoreFile);
    			fwriter.write(Player.getPlayer().getName() + ", " + Player.getPlayer().getScore() + System.lineSeparator());
    			fwriter.close();
    		} else {    			
    			ArrayList<Pair<String, Integer>> flines = new ArrayList<Pair<String, Integer>>();
    			
    			try (Scanner fscanner = new Scanner(highScoreFile)) {	    			
	    			while (fscanner.hasNextLine()) {
	    				String[] rawdata = fscanner.nextLine().split(",");
	    				flines.add(new Pair<String, Integer>(rawdata[0], Integer.parseInt(rawdata[1].stripLeading())));
	    			}
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			
    			flines.add(new Pair<String, Integer>(Player.getPlayer().getName(), Player.getPlayer().getScore()));
    			Collections.sort(flines, Collections.reverseOrder(new Comparator<Pair<String, Integer>>() {
    				
    				@Override
    				public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {			
    					if (o1.getValue().equals(o2.getValue())) {
    						return o1.getKey().compareToIgnoreCase(o2.getKey());
    					} else {
    						return o1.getValue().compareTo(o2.getValue());
    					}    				
    				}
    			}));
    			
	    		try (BufferedWriter fwriter = new BufferedWriter(new FileWriter(highScoreFile))) {
	    			for (Pair<String, Integer> entry : flines) {
	    				fwriter.write(entry.getKey() + "," + entry.getValue());
	    				fwriter.newLine();
	    				fwriter.flush();
	    			}	    			    			
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
    		}
    		
    		root = FXMLLoader.load(getClass().getClassLoader().getResource("main/view/fxml/HighScoreMenu.fxml"));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	((StackPane)gameOverRoot.getParent()).getChildren().add(root);
    	root.lookup("#btnTryAgain").requestFocus();
    	((StackPane)gameOverRoot.getParent()).getChildren().remove(gameOverRoot);
    }
}