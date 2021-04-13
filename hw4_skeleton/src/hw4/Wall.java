package hw4;

import java.awt.Color;

import graph.Cell;
import state.State;

public class Wall implements State{
	public Wall()
	{
		
	}
	@Override
	public void handle(Cell cell) {
	}

	@Override
	public Color getColor() {
		return Color.white;
	}

	@Override
	public boolean isPassable() {
		return false;
	}

	@Override
	public char toChar() {
		return '#';
	}

}
