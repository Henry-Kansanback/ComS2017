package edu.iastate.cs228.hw2;

/**
 *  
 * @author Henry Kansanback
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	//private Scanner sc;
	private int run;
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Perform the four sorting algorithms over each sequence of integers, comparing 
	 * points by x-coordinate or by polar angle with respect to the lowest point.  
	 * 
	 * @param args
	 * @throws FileNotFoundException 
	 **/
	public static void main(String[] args) throws FileNotFoundException 
	{		
		
		// 
		// Conducts multiple sorting rounds. In each round, performs the following: 
		// 
		//    a) If asked to sort random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		//    b) Reassigns to elements in the array sorters[] (declared below) the references to the 
		//       four newly created objects of SelectionSort, InsertionSort, MergeSort and QuickSort. 
		//    c) Based on the input point order, carries out the four sorting algorithms in a for 
		//       loop that iterates over the array sorters[], to sort the randomly generated points
		//       or points from an input file.  
		//    d) Meanwhile, prints out the table of runtime statistics.
		// 
		// A sample scenario is given in Section 2 of the assignment description. 
		// 	
		AbstractSorter[] sorters = new AbstractSorter[4];
		System.out.println("Comparison of Four Sorting Algorithms\n");
		System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)\n order: 1 (by x-coordinate) 2 (by polar angle) \n");
		System.out.println("Trial 1 => Enter key: ");
		Scanner sc = new Scanner(System.in);
		int in = sc.nextInt();
		int r = 0;
		if(in == 1)
		{
			System.out.println("Enter number of random points: ");
			r = sc.nextInt();
		}
		String inputfile = null;
		if(in == 2)
		{
			System.out.println("File name: ");
			inputfile = sc.next();
		}
		if(in == 3)
		{
			System.exit(0);
		}
		
		System.out.println("Order used in sorting: ");
		int order = sc.nextInt();
		sc.close();
		if(in == 1)
		{
			Random rand = new Random();
			Point[] one = generateRandomPoints(r, rand);
			SelectionSorter sel = new SelectionSorter(one);
			sorters[0] = sel;
			InsertionSorter ins = new InsertionSorter(one);
			sorters[1] = ins;
			MergeSorter mer = new MergeSorter(one);
			sorters[2] = mer;
			QuickSorter qui = new QuickSorter(one);
			sorters[3] = qui;
			for(int i = 0; i <= sorters.length-1; i++)
			{
				sorters[i].sort(order);
				sorters[i].writePointsToFile();
				System.out.println(sorters[i].stats());
			}
			
		}
		if(in == 2)
		{
			SelectionSorter sel = new SelectionSorter(inputfile);
			sorters[0] = sel;
			InsertionSorter ins = new InsertionSorter(inputfile);
			sorters[1] = ins;
			MergeSorter mer = new MergeSorter(inputfile);
			sorters[2] = mer;
			QuickSorter qui = new QuickSorter(inputfile);
			sorters[3] = qui;
			for(int i = 0; i <= sorters.length-1; i++)
			{
				sorters[i].sort(order);
				sorters[i].writePointsToFile();
				System.out.println(sorters[i].stats());
			}
		}
		// Within a sorting round, every sorter object write its output to the file 
		// "select.txt", "insert.txt", "merge.txt", or "quick.txt" if it is an object of 
		// SelectionSort, InsertionSort, MergeSort, or QuickSort, respectively. 
		
	}
	
	
	/**
	 * This method generates a given number of random points to initialize randomPoints[].
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] × [-50,50]. Please refer to Section 3 of assignment description document on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		Point[] randomPoints = new Point[numPts];
		for(int i = 0; i < randomPoints.length; i++)
		{
			int tempx = rand.nextInt(101)-50;
			int tempy = rand.nextInt(101)-50;
			
			randomPoints[i] = new Point(tempx, tempy);
		}
		return randomPoints;  
	}
}
