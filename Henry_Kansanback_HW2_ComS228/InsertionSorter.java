package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 


/**
 *  
 * @author Henry Kansanback
 *
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	// Other private instance variables if you need ... 
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points. 
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) throws IllegalArgumentException
	{
		super(pts);
		algorithm = "insertion sort";
		outputFileName = "insert.txt";
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public InsertionSorter(String inputFileName) throws FileNotFoundException
	{
		super(inputFileName);
		algorithm = "insertion sort";
		outputFileName = "insert.txt";
	}
	
	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 */
	@Override 
	public void sort(int order) throws IllegalArgumentException
	{
		if(order < 1 || order > 2)
		{
			throw new IllegalArgumentException();
		}
		if(order == 1)
		{
			sortByAngle = false;
			setComparator();	
			for(int i = 1; i < points.length; i++)
			{
				int pos = i;
				while(pos > 0 && pointComparator.compare(points[pos], points[pos - 1]) == -1)
				{
					swap(pos, pos - 1);
					pos--;
				}
			}
			sortingTime = System.nanoTime();
		}
		if(order == 2)
		{
			sortByAngle = true;
			setComparator();
			for(int i = 1; i < points.length; i++)
			{
				int pos = i;
				while(pos > 0 && pointComparator.compare(points[pos-1], points[pos]) == -1)
				{
					swap(pos, pos - 1);
					pos--;
				}
			}
			sortingTime = System.nanoTime();
		}
		// && points[pos].getX() < points[pos - 1].getX() && points[pos].getY() < points[pos - 1].getY()
	}		
}
