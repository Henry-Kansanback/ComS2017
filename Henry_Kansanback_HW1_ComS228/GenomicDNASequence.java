package edu.iastate.cs228.hw1;
/**
 * 
 * @author Henry Kansanback
 *This class deals in exons
 */
public class GenomicDNASequence extends DNASequence {
	public boolean[] iscoding;
	/**
	 * 
	 * @param gdnaarr
	 * char[] passed into constructor
	 * 
	 * Constructor for GenomicDNASequence
	 * acts as a normal constructor except by creating a boolean array of the same length
	 * as gdnaarr character array and saves its reference in iscoding.
	 * it then changes its boolean values to false at each index.
	 */
	public GenomicDNASequence(char[] gdnaarr)
	{
	
		super(gdnaarr);
	
	boolean[] it = new boolean[gdnaarr.length];
	// it = (boolean[]) iscoding;
	iscoding = it;
	for(int o = 0; o <= it.length-1;o++)
	{
		it[o] = false;
	}
	}
	/**
	 * this method deals with marking the beginning and the end of the exon coding sequence
	 * @param first
	 * first is the int that represents the starting point for the exon coding
	 * @param last
	 * last is the int that represents the end point for the exon coding
	 */
	public void markCoding(int first, int last)
	  {
		  int slen = seqLength();
	    if(first < 0 || last < 0 || first >= slen || last >= slen)
	    {
	    	throw new IllegalArgumentException("Coding border is out of bound");
	    }
	    if(first > last)
	    {
	    	reverseCompliment();
	    	first = slen - 1 - first;
	    	last = slen - 1 - last;
	    }
	    for(int i = first; i <= last; i++)
	    {
	    	iscoding[i] = true;
	    }
	  }
	
	/**
	 * This method extracts exons from the seqarr array by using the inputed start and end points.
	 * @param exonpos
	 * exonpos represents the int array that marks beginnings and ends of the seqarr char[]
	 * @return
	 * returns a char[] that is in between each of the int[] start and end marks
	 */
	public char[] extractExons(int[] exonpos){
		  if(exonpos.length == 0 || exonpos.length%2 != 0)
		  {
			  throw new IllegalArgumentException("Empty array or odd number of array elements");
		  }
		  for(int i = 0; i <= exonpos.length-1; i++)
		  {
			  if(exonpos[i] < 0 || exonpos[i] >= seqLength())
			  {
				  throw new IllegalArgumentException("Exon position is out of bound");
			  }
			  if(i != exonpos.length - 1)
			  {
				  if(exonpos[i] > exonpos[i+1])
				  {
					  throw new IllegalArgumentException("Exon positions are not in order");
				  }
			  }
			  if(iscoding[exonpos[i]] == false)
			  {
				  throw new IllegalStateException("Noncoding position is found");
			  }
		  }
		  
		  
		  int total = 0;

		  for(int i = 0; i < exonpos.length-1; i = i + 2)
		  {
			  if(i >= exonpos.length)
			  {
				  break;
			  }
			  total = total + exonpos[i+1] - exonpos[i] + 1;
		  }
		  char[] exonarr = new char[total];
		  // p is the position in the exonpos
		  // d is the index of all integers between the start and end
		  // positions of the two exonpos int's provided
		  
		  int c = 0;
		  int p = 0;
		  for(;p <= exonpos.length-1;p = p+2)
		  {
			  for(int d = exonpos[p]; d <= exonpos[p+1]; d++)
			  {
				  exonarr[c] = seqarr[d];
				  c++;
			  }
		  }
		return exonarr;
	  }
}