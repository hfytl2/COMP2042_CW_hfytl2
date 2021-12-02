package main.model;

import java.util.ArrayList;
import javafx.geometry.Point2D;

interface Crackable {
	public static final int CRACK_DEPTH = 1;
    public static final int CRACK_STEPS = 35;
	
	public ArrayList<Crack> getCracks();
	public void addCrack(Point2D impact, String direction);
	public void removeCracks();
}