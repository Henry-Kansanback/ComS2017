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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
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
	public SelectionSorter(Point[] pts) throws IllegalArgumentException
	{
		super(pts);
		algorithm = "selection sort";
		outputFileName = "select.txt";
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public SelectionSorter(String inputFileName) throws FileNotFoundException
	{
		super(inputFileName);
		algorithm = "selection sort";
		outputFileName = "select.txt";
	}
	
	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 *
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
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
			for(int i = 0; i <= points.length-1; i++){
				int mini = i;
				for(int j = i + 1; j < points.length; j++){
					if (pointComparator.compare(points[j], points[mini]) == -1){
						mini = j;
					}
				}
				swap(i, mini);
			}
			sortingTime = System.nanoTime();
			
		}
		
		if(order == 2)
		{
			sortByAngle = true;
			setComparator();
			for(int i = 0; i < points.length; i++){
				int mini = i;
				for(int j = i + 1; j < points.length; j++){
					if (pointComparator.compare(points[j], points[mini]) == -1){
						mini = j;
					}
				}
				swap(i, mini);
			}
			sortingTime = System.nanoTime();
		}
	}	
}
