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

package main.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The {@code DebugConsole} class represents a modal popup window that has to aid with debugging the game.
 * Note: Converted the {@code DebugConsole} to JavaFX.
 * 
 * @author Lim Tze Yang
 */
public class DebugConsole extends Stage {
	
	private Button btnSkipLevel, btnResetBalls;
	private Slider sliderBallSpeed;
	
	/**
	 * Creates a new instance of DebugConsole with the given stage and game.
	 * @param stage The owner stage of the console.
	 */
	public DebugConsole(Stage stage) {
		super(StageStyle.UTILITY);
		initOwner(stage);
		initModality(Modality.WINDOW_MODAL);
		setTitle("Debug Console");
		Parent root = null;
		Game game = Game.getGame();
		
		try {
			root = FXMLLoader.load(getClass().getResource("../view/fxml/DebugMenu.fxml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		setScene(new Scene(root));
		btnSkipLevel = (Button)root.lookup("#btnSkipLevel");
		btnSkipLevel.setOnAction(e -> {
			game.nextLevel();
		});
		btnResetBalls = (Button)root.lookup("#btnResetBalls");
		btnResetBalls.setOnAction(e -> {
			game.getPlayer().resetLives();
		});
		sliderBallSpeed = (Slider)root.lookup("#sliderBallSpeed");
		sliderBallSpeed.setValue(game.getBall().getSpeed());
		sliderBallSpeed.valueProperty().addListener((observableVal, oldVal, newVal) -> {
			game.getBall().setSpeed((double)newVal);
		});
	}
}