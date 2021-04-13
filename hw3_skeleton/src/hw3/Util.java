package hw3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import api.Cell;
import java.awt.Color;
import api.Flow;
import hw3.FlowGame;
import java.io.File;
import java.util.Scanner;

/**
 * Utility class with methods for creating Flows from string descriptors and
 * reading string descriptors from a file. A string descriptor is an array of
 * strings, all with the same length, in which an alphabetic character at a
 * given row and column represents a cell at that row and column. The color of
 * the cell is determined from the character by the Cell constructor. A
 * descriptor is invalid if not all strings are the same length or if there is
 * an alphabetic character that does not appear exactly twice.
 */
public class Util {
	private static String[] desc;
	private static int height;
	private static int width;
	private static Flow flow;
	private static Flow[] flows;
	private static Cell cell;
	private static int col;
	private static int row;
	private static int holder;
	private static int holder2;
	private static ArrayList<Flow> fl;
	private static int counter;

	/**
	 * Creates an array of Flow objects based on the string descriptor. If the
	 * descriptor is invalid, this method returns null.
	 * 
	 * @param descriptor
	 *            array of strings
	 * @return array of Flow objects determined by the descriptor
	 */
	public static Flow[] createFlowsFromStringArray(String[] descriptor) 
	{
		fl = new ArrayList<Flow>(descriptor.length);
		flows = new Flow[descriptor.length];
		desc = descriptor;
		height = descriptor.length;
		for (int i = 0; descriptor.length > i; i++) 
		{
			row++;
			String s = descriptor[i];
			if (width < s.length()) 
			{
				width = s.length();
			}
			for (int b = 0; s.length() > b; b++) 
			{
				col = b;
				char ch = s.charAt(b);
				if (ch != '-') 
				{
					if(b != s.length())
					{
						holder = b;
						holder2 = i;
					}
					if(b == s.length())
					{
						holder = 0;
						holder2 = row + 1;
					}
					Matcher(ch, descriptor);	
						
				}
				
			}
			col = 0;
		}
		flows = new Flow[counter];
		if(row == descriptor.length)
		{
			for(int i = 0; descriptor.length > i; i++)
			{
				flows[i] = fl.get(i);
			}
		}
		return flows;
	}

	private static void createFlows(Flow flow)
	{
		fl.add(flow);
	}
	
	private static void Matcher(char at, String[] s)
	{
		for(int t = holder2; t < s.length; t++)
		{
			String current = s[t];
			for(int i = holder; current.length() > i; i++)
			{
				char ch = current.charAt(i);
				if(Cell.getColor(ch) == Cell.getColor(at))
				{
					counter++;
					Cell cell1 = new Cell(t, i, ch);
					//flow.add(cell1);
					Cell cell2 = new Cell(holder2, holder, Cell.getColor(at));
					//flow.add(cell2);
					Flow flow = new Flow(cell1, cell2);
					createFlows(flow);
				}
			}
		}
	}

	/**
	 * Reads the given file and constructs a list of FlowGame objects, one for
	 * each descriptor in the file. Descriptors in the file are separated by
	 * some amount of whitespace, but the file need not end with whitespace and
	 * may have extra whitespace at the beginning. Invalid descriptors in the
	 * file are ignored, so the method may return an empty list.
	 * 
	 * @param filename
	 *            name of the file to read
	 * @return list of FlowGame objects created from the valid descriptors in
	 *         the file
	 * @throws FileNotFoundException
	 */
	public static ArrayList<FlowGame> readFile(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		String[] descr;
		int total = 0;
		while(sc.hasNextLine())
		{
			total++;
		}
		descr = new String[total];
		for(int i = 0; i < total; i++)
		{
			descr[i] = sc.nextLine();
		}
		FlowGame ft = new FlowGame(descr);
		ArrayList<FlowGame> fy = new ArrayList<>();
		fy.add(ft);
		return fy;
	}

}
