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
//import main.GameFrame;
import javafx.scene.Scene;

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
    	parentcontainer.requestFocus();
    	fadeHome.play();
    }
    
    @FXML
    private void helpButtonPressed() throws IOException {
    	Scene scene = parentcontainer.getScene();
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
    		scene.setOnKeyPressed(key -> {
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
    	parentcontainer.requestFocus();
    	fadeHome.play();
    }   
    
    @FXML
    private void exitButtonPressed() {
    	System.out.println("Goodbye " + System.getProperty("user.name"));
    	System.exit(0);
    }    
}