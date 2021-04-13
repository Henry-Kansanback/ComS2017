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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	private int low;
    private int high;
    private int mid;
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 * in the array. 
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) throws IllegalArgumentException
	{
		super(pts);
		algorithm = new String("merge sort");
		outputFileName = "merge.txt";
	}
	
	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public MergeSorter(String inputFileName) throws FileNotFoundException
	{
		super(inputFileName);
		algorithm = new String("merge sort");
		outputFileName = "merge.txt";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
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
			mergeSortRec(points);
			sortingTime = System.nanoTime();
		}
		if(order == 2)
		{
			sortByAngle = true;
			setComparator();
			mergeSortRecA(points);
			sortingTime = System.nanoTime();
		}
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * Personal adjustment - I've decided to utilize this method to only divide the array
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		low = 0;
		int p;
		high = pts.length;
		mid = (high+low)/2;
		if(pts.length<2)
		{
			return;
		}
		else
		{
			Point[] l = new Point[mid];
			Point[] r = new Point[high-mid];
			for(p=0; p < mid; p++)
			{
				int tempx = pts[p].getX();
				int tempy = pts[p].getY();
				l[p] = new Point(tempx, tempy);
			}
			for(int q = 0; q < high-mid; q++, p++)
			{
				int tempx = pts[p].getX();
				int tempy = pts[p].getY();
				r[q] = new Point(tempx, tempy);
			}
			
			mergeSortRec(l);
			mergeSortRec(r);
			mergeSort(pts, l, r);
		}
	}

	private void mergeSort(Point[] pt, Point[] left, Point[] right)
	{
		int baseL = 0; // iterator for Left array
		int baseR = 0; // iterator for Right array
		int k = 0;
		
		while(baseL < left.length && baseR < right.length)
		{
			if(pointComparator.compare(left[baseL], right[baseR]) == -1)
			{
				points[k] = new Point(left[baseL].getX(), left[baseL].getY());
				baseL++;
				k++;
			}
			else
			{
				points[k] = new Point(right[baseR].getX(), right[baseR].getY());
				baseR++;
				k++;
			}
		}
		while(baseL < left.length)
		{
			points[k] = new Point(left[baseL].getX(), left[baseL].getY());
			baseL++;
			k++;
		}
		
		while(baseR < right.length)
		{
			points[k] = new Point(right[baseR].getX(), right[baseR].getY());
			baseR++;
			k++;
		}
	}
	// Other private methods in case you need ...

	private void mergeSortRecA(Point[] pts)
	{
		low = 0;
		int p;
		high = pts.length;
		mid = (high+low)/2;
		if(pts.length<2)
		{
			return;
		}
		else
		{
			Point[] l = new Point[mid];
			Point[] r = new Point[high-mid];
			for(p=0; p < mid; p++)
			{
				int tempx = pts[p].getX();
				int tempy = pts[p].getY();
				l[p] = new Point(tempx, tempy);
			}
			for(int q = 0; q< high-mid; q++, p++)
			{
				int tempx = pts[p].getX();
				int tempy = pts[p].getY();
				r[q] = new Point(tempx, tempy);
			}
			
			mergeSortRecA(l);
			mergeSortRecA(r);
			mergeSortA(pts, l, r);
		}
	}

	private void mergeSortA(Point[] pt, Point[] left, Point[] right)
	{
		int baseL = 0; // iterator for Left array
		int baseR = 0; // iterator for Right array
		int k = 0;
		
		while(baseL < left.length && baseR < right.length)
		{
			if(pointComparator.compare(left[baseL], right[baseR]) == -1)
			{
				points[k] = new Point(left[baseL].getX(), left[baseL].getY());
				baseL++;
				k++;
			}
			else
			{
				points[k] = new Point(right[baseR].getX(), right[baseR].getY());
				baseR++;
				k++;
			}
		}
		while(baseL < left.length)
		{
			points[k] = new Point(left[baseL].getX(), left[baseL].getY());
			baseL++;
			k++;
		}
		
		while(baseR < right.length)
		{
			points[k] = new Point(right[baseR].getX(), right[baseR].getY());
			baseR++;
			k++;
		}
	}
}
