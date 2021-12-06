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
 * 
 * @author Lim Tze Yang
 */
public class DebugConsole extends Stage {
	
	private Button skiplevel, resetballs;
	private Slider ballspeedslider;
	
	/**
	 * Creates a new instance of DebugConsole with the given stage and game.
	 * @param stage The owner stage of the console.
	 * @param game The game that the console is used to debug.
	 */
	public DebugConsole(Stage stage, Game game) {
		super(StageStyle.UTILITY);
		initOwner(stage);
		initModality(Modality.WINDOW_MODAL);
		setTitle("Debug Console");
		Parent root = null;
		
		try {
			root = FXMLLoader.load(getClass().getResource("../view/fxml/DebugMenu.fxml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		setScene(new Scene(root));
		skiplevel = (Button)root.lookup("#skiplevel");
		skiplevel.setOnAction(e -> {
			game.nextLevel();
		});
		resetballs = (Button)root.lookup("#resetballs");
		resetballs.setOnAction(e -> {
			game.getPlayer().setLives(3);
		});
		ballspeedslider = (Slider)root.lookup("#ballspeedslider");
		ballspeedslider.setValue(game.getBall().getSpeed());
		ballspeedslider.valueProperty().addListener((ov, oldval, newval) -> {
			game.getBall().setSpeed((double)newval);
		});
	}
}