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
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 
		
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts) throws IllegalArgumentException
	{
		super(pts);
		algorithm = "quick sort";
		outputFileName = "quick.txt";
	}
		

	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public QuickSorter(String inputFileName) throws FileNotFoundException
	{
		super(inputFileName);
		algorithm = "quick sort";
		outputFileName = "quick.txt";
	}


	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
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
			quickSortRec(0, points.length-1);
			sortingTime = System.nanoTime();
		}
		if(order == 2)
		{
			sortByAngle = true;
			setComparator();
			quickSortRecA(0, points.length-1);
			sortingTime = System.nanoTime();
		}
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if(first >= last)
		{
			return;
		}
		
		int middle = partitionx(first, last);
		quickSortRec(first, middle);
		quickSortRec(middle+1, last);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partitionx(int first, int last)
	{
		Point pivot = new Point(points[first].getX(), points[first].getY());
		int left = first;
		int right = last;
		
		while(true)
		{
			//while(points[left].compareTo(pivot) == -1)
			while(pointComparator.compare(points[left], pivot) == -1)
			{
				left++;
			}
			//while(points[right].compareTo(pivot) == 1)
			while(pointComparator.compare(points[right], pivot) == 1)
			{
				right--;
			}
			if(left < right)
			{
				Point c = new Point(points[left].getX(), points[left].getY());
				points[left++] = points[right];
				points[right--] = c;
			}
			else
			{
				break;
			}
		}
		return right; 
	}	
	
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRecA(int first, int last)
	{
		if(first >= last)
		{
			return;
		}
		
		int middle = partitionA(first, last);
		quickSortRecA(first, middle);
		quickSortRecA(middle+1, last);
	}
	
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partitionA(int first, int last)
	{
		Point pivot = new Point(points[first].getX(), points[first].getY());
		int left = first;
		int right = last;
		
		while(true)
		{
			while(pointComparator.compare(points[left], pivot) == -1)
			{
				left++;
			}
			while(pointComparator.compare(points[right], pivot) == 1)
			{
				right--;
			}
			if(left < right)
			{
				Point c = new Point(points[left].getX(), points[left].getY());
				points[left++] = points[right];
				points[right--] = c;
			}
			else
			{
				break;
			}
		}
		return right; 
	}


	
	// Other private methods in case you need ...
}
