package edu.iastate.cs228.hw2;

/**
 *  
 * @author Henry Kansanback
 *
 */

import java.util.Comparator;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later on the sorted) sequence and records the employed sorting algorithm, 
 * the comparison method, and the time spent on sorting. 
 *
 */


public abstract class AbstractSorter
{
	
	protected Point[] points;    // Array of points operated on by a sorting algorithm. 
	                             // The number of points is given by points.length.
	
	protected String algorithm = null; // "selection sort", "insertion sort",  
	                                   // "merge sort", or "quick sort". Initialized by a subclass 
									   // constructor.
	protected boolean sortByAngle;     // true if last sort was done by polar angle and false 
									   // if by x-coordinate 
	
	protected String outputFileName;   // "select.txt", "insert.txt", "merge.txt", or "quick.txt"
	
	protected long sortingTime; 	   // execution time in nanoseconds. 
	 
	protected Comparator<Point> pointComparator;  // comparator which compares polar angle if 
												  // sortByAngle == true and x-coordinate if 
												  // sortByAngle == false 
	
	private Point lowestPoint; 	    // lowest point in the array, or in case of a tie, the
									// leftmost of the lowest points. This point is used 
									// as the reference point for polar angle based comparison.

	
	// Add other protected or private instance variables you may need. 
	
	protected AbstractSorter()
	{
		// No implementation needed. Provides a default super constructor to subclasses. 
		// Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	}
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * Sets the instance variable lowestPoint.
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		points = new Point[pts.length];
		Point lowest = new Point(pts[0]);
		if(pts == null || pts.length == 0)
		{
			throw new IllegalArgumentException();
		}
		for(int i = 0; pts.length-1 >= i; i++)
		{
			int tempx = pts[i].getX();
			int tempy = pts[i].getY();
			if(tempx < lowest.getX() && tempy <= lowest.getY())
			{
				lowest = new Point(tempx, tempy);
			}
			/*else if(pts[i].getX() < lowest.getX())
			{
				lowest = new Point(tempx, tempy);
			}*/
			points[i] = new Point(tempx, tempy);
			
		}
		lowestPoint = lowest;
	}

	
	/**
	 * This constructor reads points from a file. Sets the instance variables lowestPoint and 
	 * outputFileName.
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   when the input file contains an odd number of integers
	 */
	protected AbstractSorter(String inputFileName) throws FileNotFoundException, InputMismatchException
	{
		File input = new File(inputFileName);
		Scanner sc = new Scanner(input);
		Point[] pts;
		int countout = 0;
		while(sc.hasNextInt())
		{
			countout++;
		}
		if(countout%2 == 0)
		{
			points = new Point[countout/2];
			sc.reset();
		}
		else
		{
			throw new InputMismatchException();
		}
		sc = new Scanner(input);
		int i = 0;
		while(sc.hasNextInt())
		{
			int tempx = -51;
			if(sc.hasNextInt())
			{
				tempx = sc.nextInt();
			}
			int tempy = -51;
			if(!sc.hasNextInt())
			{
				throw new InputMismatchException();
			}
			if(sc.hasNextInt() && tempx != -51)
			{
				tempy = sc.nextInt();
			}
			points[i] = new Point(tempx, tempy);
			i++;
		}
		sc.close();
	}
	

	/**
	 * Sorts the elements in points[]. 
	 * 
	 *     a) in the non-decreasing order of x-coordinate if order == 1
	 *     b) in the non-decreasing order of polar angle w.r.t. lowestPoint if order == 2 
	 *        (lowestPoint will be at index 0 after sorting)
	 * 
	 * Sets the instance variable sortByAngle based on the value of order. Calls the method 
	 * setComparator() to set the variable pointComparator and use it in sorting.    
	 * Records the sorting time (in nanoseconds) using the System.nanoTime() method. 
	 * (Assign the time to the variable sortingTime.)  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle w.r.t lowestPoint 
	 *
	 * @throws IllegalArgumentException if order is less than 1 or greater than 2
	 */
	public abstract void sort(int order) throws IllegalArgumentException; 
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the assignment description. 
	 */
	public String stats()
	{
		String out = (algorithm + "\t" + points.length + "\t" + sortingTime);
		return out; 
		
	}
	
	
	/**
	 * Write points[] to a string.  When printed, the points will appear in order of increasing
	 * index with every point occupying a separate line.  The x and y coordinates of the point are 
	 * displayed on the same line with exactly one blank space in between. 
	 */
	@Override
	public String toString()
	{
		String out = "";
		for(int i = 0; i < points.length; i++)
		{
			out = (points[i].getX() + " " + points[i].getY() + "\n");
		}
		return out; 
	}

	
	/**
	 *  
	 * This method, called after sorting, writes point data into a file by outputFileName. It will 
	 * be used for Mathematica plotting to verify the sorting result.  The data format depends on 
	 * sortByAngle.  It is detailed in Section 4.1 of the assignment description assn2.pdf. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writePointsToFile() throws FileNotFoundException
	{
		
		File output = new File(outputFileName);
		File output2 = new File("sortedPoints.txt.txt");
		FileOutputStream fos2 = new FileOutputStream(output2, false);
		FileOutputStream fos = new FileOutputStream(output, false);
		PrintWriter pw = new PrintWriter(fos);
		PrintWriter pw2 = new PrintWriter(fos2);
		if(sortByAngle == false)
		{
			for(int i = 0; i < points.length; i++)
			{
				pw.write(points[i].getX() + " " + points[i].getY() + "\r\n");
				pw2.write(points[i].getX() + " " + points[i].getY() + "\r\n");
			}
			pw.close();
			pw2.close();
		}
		if(sortByAngle == true)
		{
			pw.write(points[0].getX() + " " + points[0].getY() + "\r\n");
			pw2.write(points[0].getX() + " " + points[0].getY() + "\r\n");
			for(int i = 2; i < points.length; i++)
			{
				pw.write(points[i-1].getX() + " " + points[i-1].getY() + " " + points[0].getX() + " " + points[0].getY() + " " + points[i-1].getX() + " " + points[i-1].getY() + "\r\n");
				pw2.write(points[i-1].getX() + " " + points[i-1].getY() + " " + points[0].getX() + " " + points[0].getY() + " " + points[i-1].getX() + " " + points[i-1].getY() + "\r\n");
			}
			pw.close();
			pw2.close();
		}
	}	

	
	/**
	 * Generates a comparator on the fly that compares by polar angle if sortByAngle == true
	 * and by x-coordinate if sortByAngle == false. Set the protected variable pointComparator
	 * to it. Need to create an object of the PolarAngleComparator class and call the compareTo() 
	 * method in the Point class, respectively for the two possible values of sortByAngle.  
	 * 
	 */
	protected void setComparator() 
	{
		
		if(sortByAngle == true)
		{
			PolarAngleComparator com = new PolarAngleComparator(lowestPoint);
			pointComparator = com;
		}
		
		if(sortByAngle == false)
		{
			Comparator<Point> comp = new Comparator<Point>()
			{
				@Override
				public int compare(Point a, Point b)
				{
					return a.compareTo(b);
				}
			};
			pointComparator = comp;
		}
	}

	
	/**
	 * Swap the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		int tempx1 = points[i].getX();
		int tempy1 = points[i].getY();
		int tempx2 = points[j].getX();
		int tempy2 = points[j].getY();
		points[i] = new Point(tempx2, tempy2);
		points[j] = new Point(tempx1, tempy1);
	}	
}
