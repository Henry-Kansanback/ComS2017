package edu.iastate.cs228.hw1;

public class Sequence {
	/**
	 * @author Henry Kansanback
	 * This is the superclass to all other classes in the chain.
	 */
protected char[] seqarr;
/**
 * This Method determines if inputed char is valid
 * @param let
 * input to be determined if valid
 * @return boolean
 * returns true or false depending on the result of if statements
 */
public boolean isValidLetter(char let)
{
	if(Character.isUpperCase(let))
	{
		return true;
	}
	if(Character.isLowerCase(let))
	{
		return true;	
	}
	return false;
}
/**
 * This is the constructor for Sequence. It determines if a character is valid.
 * @param sarr
 * input of same length as seqarr. Will be run through by for loop to determine if contents are valid.
 */
public Sequence(char[] sarr)
{
	seqarr = new char[sarr.length];
	for(int i = 0; i <= sarr.length-1; i++)
	{
		if(isValidLetter(sarr[i]))
		{
			seqarr[i] = sarr[i];
		}
		else throw new IllegalArgumentException("Invalid sequence letter for class " + this.getClass());
	}
}
/**
 * returns seqarr length
 * @return seqarr length
 */
public int seqLength()
{
	return seqarr.length;
}
/**
 * 
 * @return sec
 * returns the char array for seqarr
 */
public char[] getSeq()
{
	char[] sec = (char[]) seqarr.clone();
	return sec;
}
/**
 * Overrides the toString method. Only turns seqarr to string.
 */
@Override
public String toString()
{
	String temp = new String(seqarr);
	return temp;
}
/**
 * Overrides equals method. Determines if inputed object is equal to referenced object.
 * @return boolean
 * Returns boolean value of equal
 */
@Override
public boolean equals(Object obj)
{
	if((obj != null) && (obj.getClass() == this.getClass()))
	{
		if(obj.getClass().isPrimitive()){
			if(obj == this)
			{
				return true;
			}
		}
		Sequence ty = (this.getClass().cast(obj));
		for(int i = 0; i <= this.seqLength(); i++)
		{
			if(!this.getClass().isArray())
			{
				return false;
			}
			else if(!(this.seqarr[i] == ty.getSeq()[i]))
			{
				return false;
			}
		}
		return true;
	}
	return false;
	
}
}
