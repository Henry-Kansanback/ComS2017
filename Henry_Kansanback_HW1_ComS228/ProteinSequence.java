package edu.iastate.cs228.hw1;
/**
 * 
 * @author Henry Kansanback
 * This class codes protein sequences
 *
 */
public class ProteinSequence extends Sequence{
	/**
	 * Constructor
	 * @param psarr
	 * char[] passed to the superclass
	 */
	  public ProteinSequence(char[] psarr)
	  {
		  super(psarr);
	  }
	  
	  /**
	   * @param aa
	   * input to be checked
	   * @return
	   * boolean value, representing if letter input was valid
	   */
	  @Override
	  public boolean isValidLetter(char aa)
	  {
		  if(aa == 'B' || aa == 'b' || aa == 'J' || aa == 'j' || aa == 'O' || aa == 'o' || aa == 'U' || aa == 'u' || aa == 'X' || aa == 'x' || aa == 'Z' || aa == 'z')
		  {
			  return false;
		  }
		return true;
	  }
}
