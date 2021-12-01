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

public class HelpMenuController {
	
	String homeFXML = "../view/fxml/HomeMenu.fxml";
	double fadetime = 250;
	
	@FXML private URL location;	
    @FXML private ResourceBundle resources;
	@FXML private GridPane helproot;
	@FXML private Button back;	
    
    public HelpMenuController() {}
    
    @FXML
    private void initialize() {}
	
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