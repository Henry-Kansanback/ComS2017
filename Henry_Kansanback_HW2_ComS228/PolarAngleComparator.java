package edu.iastate.cs228.hw2;

/**
 *  
 * @author Henry Kansanback
 *
 */

import java.util.Comparator;

/**
 * 
 * This class compares two points p1 and p2 by polar angle with respect to a reference point.  
 * It is known that the reference point is not above either p1 or p2, and in the case that
 * either or both of p1 and p2 have the same y-coordinate, not to their right. 
 *
 */
public class PolarAngleComparator implements Comparator<Point>
{
	private Point referencePoint; 
	
	/**
	 * 
	 * @param p reference point
	 */
	public PolarAngleComparator(Point p)
	{
		referencePoint = p; 
	}
	
	/**
	 * Use cross product and dot product to implement this method.  Do not take square roots 
	 * or use trigonometric functions. See the PowerPoint notes on how to carry out cross and 
	 * dot products. Calls private methods crossProduct() and dotProduct(). 
	 * 
	 * Call comparePolarAngle() and compareDistance(). 
	 * 
	 * @param p1
	 * @param p2
	 * @return  0 if p1 and p2 are the same point
	 *         -1 otherwise, if one of the following three conditions holds: 
	 *                a) p1 and referencePoint are the same point (hence p2 is a different point); 
	 *                b) neither p1 nor p2 equals referencePoint, and the polar angle of 
	 *                   p1 with respect to referencePoint is less than that of p2; 
	 *                c) neither p1 nor p2 equals referencePoint, p1 and p2 have the same polar 
	 *                   angle w.r.t. referencePoint, and p1 is closer to referencePoint than p2. 
	 *   
	 *          1  otherwise. 
	 *                   
	 */
	@Override
	public int compare(Point p1, Point p2)
	{
		if(p1.getX() == p2.getX() && p2.getY() == p1.getY())
		{
			return 0;
		}
		if(p1.getX() == referencePoint.getX() && p1.getY() == referencePoint.getY())
		{
			return -1;
		}
		if(p1.getX() != referencePoint.getX() && p2.getX() != referencePoint.getX() && comparePolarAngle(p1,p2) == -1)
		{
			return -1;
		}
		if(p1.getX() != referencePoint.getX() && p2.getX() != referencePoint.getX() && comparePolarAngle(p1,p2) == 0 && compareDistance(p1,p2) == -1)
		{
			return -1;
		}
		return 1; 
	}
	
	
	/**
	 * Compare the polar angles of two points p1 and p2 with respect to referencePoint.  Use 
	 * cross products.  Do not use trigonometric functions. 
	 * 
	 * Ought to be private but made public for testing purpose. 
	 * 
	 * @param p1
	 * @param p2
	 * @return    0  if p1 and p2 have the same polar angle.
	 * 			 -1  if p1 equals referencePoint or its polar angle with respect to referencePoint
	 *               is less than that of p2. 
	 *            1  otherwise. 
	 */
    public int comparePolarAngle(Point p1, Point p2)
    {
    	if(Math.abs(crossProduct(p1,p2)) == 0)
    	{
    		return 0;
    	}
    	if(0 < crossProduct(p1,p2) || (p1.getX() == referencePoint.getX() && p1.getY() == referencePoint.getY())) // counterclockwise if >0
    	{ // if If p1 and p2 are the points, and the polar angle of p1 is less than the polar angle of p2 relative to the reference point,
    		//the cross product of p1 and p2 should be a positive number. If p1 comes after p2, then the cross product is a negative number
    		return -1; // clockwise if this is the case
    	}
    	return 1; //counterclockwise if this is the case
    }
    
    
    /**
     * Compare the distances of two points p1 and p2 to referencePoint.  Use dot products. 
     * Do not take square roots. 
     * 
     * Ought to be private but made public for testing purpose.
     * 
     * @param p1
     * @param p2
     * @return    0   if p1 and p2 are equidistant to referencePoint
     * 			 -1   if p1 is closer to referencePoint 
     *            1   otherwise (i.e., if p2 is closer to referencePoint)
     */
    public int compareDistance(Point p1, Point p2)
    {
    	if(Math.abs(dotProduct(p1,p1)) == Math.abs(dotProduct(p2,p2)))
    	{
    		return 0;
    	}
    	if(Math.abs(dotProduct(p1,p1)) < Math.abs(dotProduct(p2,p2)))
    	{
    		return -1;
    	}
    	if(Math.abs(dotProduct(p1,p1)) > Math.abs(dotProduct(p2,p2)))
    	{
    		return 1;
    	}

    	return 0; 
    }
    

    /**
     * 
     * @param p1
     * @param p2
     * @return cross product of two vectors p1 - referencePoint and p2 - referencePoint
     */
    private int crossProduct(Point p1, Point p2)
    {
    	int dotx1 = (referencePoint.getX() - p1.getX());
    	int doty1 = (referencePoint.getY() - p1.getY());
    	int dotx2 = (referencePoint.getX() - p2.getX());
    	int doty2 = (referencePoint.getY() - p2.getY());
    	return (dotx1*doty2 - dotx2*doty1); 
    }

    /**
     * 
     * @param p1
     * @param p2
     * @return dot product of two vectors p1 - referencePoint and p2 - referencePoint
     */
    private int dotProduct(Point p1, Point p2)
    {
    	int dotx1 = (referencePoint.getX() - p1.getX());
    	int doty1 = (referencePoint.getY() - p1.getY());
    	int dotx2 = (referencePoint.getX() - p2.getX());
    	int doty2 = (referencePoint.getY() - p2.getY());
    	return (dotx1*dotx2 + doty1*doty2); 
    }
}
