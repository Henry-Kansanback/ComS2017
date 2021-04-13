package hw3;

import java.util.ArrayList;
import api.Cell;
import api.Flow;
import java.awt.Color;

/**
 * Game state for a Flow Free game.
 */
public class FlowGame {
	private int width = 0;
	private int height = 0;
	private Cell cell;
	private Flow flow;
	//private Color color;
	private Flow[] flows;
	private boolean occupied;

	/**
	 * Constructs a FlowGame to use the given array of Flows and the given width
	 * and height. Client is responsible for ensuring that all cells in the
	 * given flows have row and column values within the given width and height.
	 * 
	 * @param givenFlows
	 *            an array of Flow objects
	 * @param givenWidth
	 *            width to use for the game
	 * @param givenHeight
	 *            height to use for the game
	 */
	public FlowGame(Flow[] givenFlows, int givenWidth, int givenHeight) {
		flows = givenFlows;
		width = givenWidth;
		height = givenHeight;
	}

	/**
	 * Constructs a FlowGame from the given descriptor.
	 * 
	 * @param descriptor
	 *            array of strings representing initial endpoint positions
	 */
	public FlowGame(String[] descriptor) {
		flows = Util.createFlowsFromStringArray(descriptor);
		width = descriptor[0].length();
		height = descriptor.length;
	}

	/**
	 * Returns the width for this game.
	 * 
	 * @return width for this game
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height for this game.
	 * 
	 * @return height for this game
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the current cell for this game, possible null.
	 * 
	 * @return current cell for this game
	 */
	public Cell getCurrent() throws NullPointerException {
		Cell current = new Cell(cell.getRow(), cell.getCol(), cell.getColor());
		for(int i = 0; i < flows.length; i++)
		{
			for(int d = 0; d < flows[i].getCells().size(); d++)
			{
				if(current == flows[i].getCells().get(d))
				{
					flow = flows[i];
					cell = flows[i].getCells().get(d);
				}
			}
		}
		return current;
	}

	/**
	 * Returns all flows for this game. Client should not modify the returned
	 * array or lists.
	 * 
	 * @return array of flows for this game
	 */
	public Flow[] getAllFlows() {
		return flows;
	}

	/**
	 * Returns the number of occupied cells in all flows (including endpoints).
	 * 
	 * @return occupied cells in this game
	 */
	public int getCount() {
		int occ = 0;
		for(int i = 0; i < flows.length; i++)
		{
			Flow f = flows[i];
			for(int b = 0; b < f.getCells().size(); b++)
			{
				if(isOccupied(f.getCells().get(b).getRow(), f.getCells().get(b).getCol()))
				{
					occ++;
				}
			}
		}
		return occ;
	}

	/**
	 * Returns true if all flows are complete and all cells are occupied.
	 * 
	 * @return true if all flows are complete and all cells are occupied
	 */
	public boolean isComplete() {
		boolean finish = false;
		int done = flows.length;		
		for (int i = 0; i < flows.length; i++) {
			Flow flow = flows[i];
			for (int b = 0; b < flow.getCells().size(); b++) {
				if(flow.isComplete())
				{
					done = flows.length - (i+1);
				}
				if(done == 0)
				{
					finish = true;
				}
			}
		}
		return finish;
	}

	/**
	 * Attempts to set the "current" cell to be an existing cell at the given
	 * row and column. When using a GUI, this method is typically invoked when
	 * the mouse is pressed.
	 * <ul>
	 * <li>Any endpoint can be selected as the current cell. Selecting an
	 * endpoint clears the flow associated with that endpoint.
	 * <li>A non-endpoint cell can be selected as the current cell only if it is
	 * the last cell in a flow.
	 * </ul>
	 * If neither of the above conditions is met, this method does nothing.
	 * 
	 * @param row
	 *            given row
	 * @param col
	 *            given column
	 */
	public void startFlow(int row, int col) {
		
		for (int i = 0; i < flows.length; i++) {
			
			Flow flow = flows[i];
			Cell temp = new Cell(row, col, flow.getColor());
			Cell end1 = flow.getEndpoint(0);
			Cell end2 = flow.getEndpoint(1);
			if (temp.positionMatches(end1.getRow(), end1.getCol())
					|| temp.positionMatches(end2.getRow(), end2.getCol())) {
				cell = temp;
				this.flow = flows[i];
			}
			for (int n = 0; n < flow.getCells().size(); n++) {
				if (flow.getCells().get(n) == temp) {
					cell = temp;
					this.flow = flows[i];
				}
			}
		}
	}

	/**
	 * Clears the "current" cell. That is, directly after invoking this method,
	 * <code>getCurrent</code> returns null. When using a GUI, this method is
	 * typically invoked when the mouse is released.
	 */
	public void endFlow() {
		cell = null;
	}

	/**
	 * Attempts to add a new cell to the flow containing the current cell. When
	 * using a GUI, this method is typically invoked when the mouse is dragged.
	 * In order to add a cell, the following conditions must be satisfied:
	 * <ol>
	 * <li>The current cell is non-null
	 * <li>The given position is horizontally or vertically adjacent to the
	 * current cell
	 * <li>The given position either is not occupied OR it is occupied by an
	 * endpoint for the flow that is not already in the flow
	 * </ul>
	 * If the three conditions are met, a new cell with the given row/column and
	 * correct color is added to the current flow, and the current cell is
	 * updated to be the new cell.
	 * 
	 * @param row
	 *            given row for the new cell
	 * @param col
	 *            given column for the new cell
	 */
	public void addCell(int row, int col) throws NullPointerException {
		Cell temp = new Cell(row, col, flow.getColor());

		if (cell != null) {
			if ((col - 1) == (cell.getCol() - 1) || (row - 1) == (cell.getRow() - 1)) {
				if (flow.getEndpoint(0).getRow() == temp.getRow() && flow.getEndpoint(0).getCol() == temp.getCol()) {
					if (false == isOccupied(temp.getRow(), temp.getCol())) {
						if (occupied == true) {
							cell = null;
						}
						flow.add(new Cell(row, col, flow.getColor()));
						cell = new Cell(row, col, flow.getColor());
					}
				}
				if (flow.getEndpoint(1).getRow() == temp.getRow() && flow.getEndpoint(1).getCol() == temp.getCol()) {
					if (false == isOccupied(temp.getRow(), temp.getCol())) {
						if (occupied == true) {
							cell = null;
						}
						flow.add(new Cell(row, col, flow.getColor()));
						cell = new Cell(row, col, flow.getColor());
					}
				}
				if (flow.getEndpoint(1).positionMatches(row, col) == false
						|| flow.getEndpoint(0).positionMatches(row, col) == false) {
					if (false == isOccupied(temp.getRow(), temp.getCol())) {
						cell = temp;
						flow.add(new Cell(row, col, flow.getColor()));
						cell = new Cell(row, col, flow.getColor());
					}
				}
			}

			if ((col + 1) == (cell.getCol() + 1) || (row + 1) == (cell.getRow() + 1)) {
				if (flow.getEndpoint(0).positionMatches(row, col)) {
					if (false == isOccupied(temp.getRow(), temp.getCol())) {
						if (occupied == true) {
							cell = null;
						}
						flow.add(new Cell(row, col, flow.getColor()));
						cell = new Cell(row, col, flow.getColor());
					}
				}
				if (flow.getEndpoint(1).positionMatches(row, col)) {
					if (false == isOccupied(temp.getRow(), temp.getCol())) {
						if (occupied == true) {
							cell = null;
						}
						flow.add(new Cell(row, col, flow.getColor()));
						cell = new Cell(row, col, flow.getColor());
					}
				}
				if (flow.getEndpoint(1).positionMatches(row, col) == false
						|| flow.getEndpoint(0).positionMatches(row, col) == false) {
					if (false == isOccupied(temp.getRow(), temp.getCol())) {
						cell = temp;
						flow.add(new Cell(row, col, flow.getColor()));
						cell = new Cell(row, col, flow.getColor());
					}
				}
			}
		}
	}

	//

	/**
	 * Returns true if the given position is occupied by a cell in a flow in
	 * this game (possibly an endpoint).
	 * 
	 * @param row
	 *            given row
	 * @param col
	 *            given column
	 * @return true if any cell in this game has the given row and column, false
	 *         otherwise
	 */
	public boolean isOccupied(int row, int col) {
		occupied = false;
		for (int r = 0; r < flows.length; r++) 
		{
			Flow f = flows[r];
			for (int i = 0; i < f.getCells().size(); i++) 
			{
				if (f.getCells().get(i).positionMatches(row, col)) 
				{
					if (f.getEndpoint(0).positionMatches(row, col) || f.getEndpoint(1).positionMatches(row, col)) 
					{
						occupied = true;
					}
					return true;
				}
			}
		}
		occupied = false;
		return false;
	}

}
