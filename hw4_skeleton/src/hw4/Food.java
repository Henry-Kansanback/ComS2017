package hw4;

import java.awt.Color;
import graph.Cell;
import main.Config;
import state.State;
/**
 * 
 * @author Henry Kansanback
 *This Class handles the functionality of Food
 */
public class Food implements State{
	public Food()
	{

	}
	private int counter = 0;
	/**
	 * @param cell - Indicates the cell being affected by this method
	 * Controls the timer
	 */
	@Override
	public void handle(Cell cell) {
		counter += 1;
		if(counter >= Config.MAX_FOOD_TIMER)
		{
			counter = 0;
		}
	}
	/**
	 * 
	 * @return Color - the new color
	 */
	@Override
	public Color getColor() {
		return Config.FOOD_COLORS[counter];
	}
	/**
	 * 
	 * @return Passable - whether or not the State is passable
	 */
	@Override
	public boolean isPassable() {
		return true;
	}
	/**
	 * 
	 * @return char - returns the Character for the state
	 */
	@Override
	public char toChar() {
		return 'F';
	}

}
