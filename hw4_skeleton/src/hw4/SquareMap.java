package hw4;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import graph.Cell;
import graph.GraphMap;

public class SquareMap extends GraphMap{

	private double x_diff = getDistance();
	private int y_diff = getDistance();
	
	@Override
	public int getPixelWidth() {
		return (int) (((this.getCells()[0].length-1) * x_diff) + x_diff);
	}

	@Override
	public int getPixelHeight() {
		return ((this.getCells().length+1) * getDistance()) + getDistance();
	}

	@Override
	public Cell[] createNeighbors(int col, int row) {
		
		Cell[][] map = getCells();
		
		int[][] relative = {{0, -1},{0, 1},{-1,0},{1,0}};
		if (col % 2 == 1) {
			for(int i = 0; i < 4; i++){
				relative[i][0] += 1;
			}
		}

		List<Cell> cells = new ArrayList<Cell>();
		for(int i = 0; i < relative.length; i++){
			int yCoor = row + relative[i][0];
			int xCoor = col + relative[i][1];
			if (yCoor >= 0 && yCoor < map.length){
				if (xCoor >= 0 && xCoor < map[yCoor].length){
					cells.add(map[yCoor][xCoor]);
				}
			}
		}
		
		return cells.toArray(new Cell[0]);
	}

	@Override
	protected Point selectClosestIndex(int x, int y) {
		x -= (getDistance()-x_diff/2);
		y -= getDistance()/2;
		x /= x_diff;
		y /= (getDistance() + y_diff);

		return new Point(x, y);
	}

	@Override
	public Polygon createPolygon(int x, int y) {
		int distance = getDistance();
		
		double xOffset = distance + x * x_diff;
		double yOffset = distance + y * (distance + y_diff);
		double yOffset0 = -distance/2;
		double yOffset1 = distance/2 + y_diff;
		double yOffset2 = distance/2;
		if ((x + y) % 2 == 1) {
			yOffset0 = distance/2 + y_diff;
			yOffset1 = -distance/2;
		}
		int[] xCoor = { (int) (xOffset - x_diff), (int) xOffset,
				(int) (xOffset + x_diff) };
		int[] yCoor = { (int) (yOffset + yOffset0), (int) (yOffset + yOffset1),
				(int) (yOffset + yOffset0) };
		return new Polygon(xCoor, yCoor, 3);
	}

}
