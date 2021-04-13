package hw4;

import java.awt.Color;

import graph.Cell;
import main.Config;
import state.Snake;
import state.SnakeSegment;
import state.State;
/**
 * 
 * @author Henry Kansanback
 * This class handles the functionality of SnakeHead
 */
public class SnakeHead implements State, Snake{
	public SnakeHead()
	{
		
	}
	private int timer = 0;
	private int length = 4;
	/**
	 * handles the Collision and movement of the snake
	 * @param cell - the cell that is affected by this method
	 */
	@Override
	public void handle(Cell cell){
		timer++;
		if(timer>= Config.MAX_SNAKE_TIMER)
		{
			timer = 0;
		}
		if(timer == 0 && !(cell.getRandomCloser() == null))
		{
			Cell newCell = cell.getRandomCloser();
			
			if(newCell.getState() instanceof Food)
			{
				SnakeSegment segment = new SnakeSegment(this);
				cell.moveState(newCell);
				cell.setState(segment);
				length++;
			}
			else{
				SnakeSegment segment = new SnakeSegment(this);
				cell.moveState(newCell);
				cell.setState(segment);
			}
		}
		// no Random cell available
		if(timer == 0 && cell.getRandomCloser() == null)
		{
			if(!(cell.getRandomOpen() == null))
			{
				// cell we are using
				Cell newCell = cell.getRandomOpen();
				// if cell is food
				if(newCell.getState() instanceof Food){
					SnakeSegment segment = new SnakeSegment(this);
					cell.moveState(newCell);
					cell.setState(segment);
					length++;
				}
				// keep moving
				else{
					SnakeSegment segment = new SnakeSegment(this);
					cell.moveState(newCell);
					cell.setState(segment);
				}
			}
			// no open cell
			else{
				Config.endGame(length-4);
			}
		}
	}
	/**
	 * Retrieves the length of the snake (i.e the score)
	 */
	@Override
	public int getLength() {
		return length;
	}
	/**
	 * Retrieves the color of the snake.
	 */
	@Override
	public Color getColor() {
		return Color.blue;
	}
	/**
	 * Determines whether or not the snake is Passable or not
	 */
	@Override
	public boolean isPassable() {
		return false;
	}
	/**
	 * 'S' is the indicator for snakehead
	 */
	@Override
	public char toChar() {
		return 'S';
	}



}
