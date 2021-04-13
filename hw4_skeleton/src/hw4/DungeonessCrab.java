package hw4;

import java.awt.Color;
import graph.Cell;
import main.Config;
/**
 * 
 * @author Henry Kansanback
 * This class controls the functionality of the "Crab"/mobile food.
 */
public class DungeonessCrab extends Food{


	private int counter = 0;
	public DungeonessCrab(){
		super();
	}
	/**
	 * @param cell - the cell that is affected by this handle
	 */
	@Override
	public void handle(Cell cell) {
		counter += 1;
		if(!(cell.getRandomOpen() == null))
		{
			super.handle(cell.getRandomOpen());
			if(counter == 0)
			{
				Cell newCell = cell.getRandomOpen();
				cell.moveState(newCell);
			}
			counter = 0;
		}
	}
	/**
	 * Returns the character associated with the DungeonessCrab
	 */
	@Override
	public char toChar() {
		return 'D';
	}

}
