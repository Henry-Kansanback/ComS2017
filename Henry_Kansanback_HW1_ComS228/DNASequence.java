package edu.iastate.cs228.hw1;
/**
 * 
 * @author Henry Kansanback
 * This class codes the complimentary sequence to the seqarr sequence
 */
public class DNASequence extends Sequence{
	/**
	 * Constructor
	 * @param dnaar
	 * Constructor parameter that is passed to superclass
	 */
	public DNASequence(char[] dnaar){
		super(dnaar);
	}
	/**
	 * overrides isValidLetter in superclass Sequence
	 */
	@Override
	public boolean isValidLetter(char let)
	{
		if(let == 'a' || let =='A' || let == 'c' || let == 'C' || let == 'g' || let =='G' || let == 't' || let == 'T')
		{
			return true;
		}
		else return false;
	}
	/**
	 * This method saves the reverse complimentary sequence of seqarr into a temporary char[] and then returns that array
	 * @return
	 * returns the save variable, which is the reverse complimentary sequence of seqarr
	 */
	public char[] getReverseCompSeq()
	{
		char[] save = new char[seqarr.length];
		if(seqarr.length%2 == 0)
		{
		for(int o = 0; o < seqarr.length/2; o++)
		{
			char temp = seqarr[o];
			save[o] = seqarr[seqarr.length-1-o];
			save[seqarr.length-1-o] = temp;
		}
		}
		if(seqarr.length%2 != 0)
		{
			for(int o = 0; o < seqarr.length/2+1; o++)
			{
				char temp = seqarr[o];
				save[o] = seqarr[seqarr.length-1-o];
				save[seqarr.length-1-o] = temp;
			}
		}
		for(int i = 0; i <= seqarr.length-1; i++)
		{
			if(save[i] == 'A' || save[i] == 'a')
			{
				if(save[i] =='A')
				{
					save[i] = 'T';
				}
				if(save[i] == 'a')
				{
					save[i] = 't';
				}
			}
			if(save[i] == 'C' || save[i] == 'c')
			{
				if(save[i] == 'C')
				{
					save[i] = 'G';
				}
				if(save[i] == 'c')
				{
					save[i] = 'g';
				}
			}
			if(save[i] == 'G' || save[i] == 'g')
			{
				if(save[i] == 'G')
				{
					save[i] = 'C';
				}
				if(save[i] == 'g')
				{
					save[i] = 'c';
				}
			}
			if(save[i] == 'T' || save[i] == 't')
			{
				if(save[i] == 'T')
				{
					save[i] = 'A';
				}
				if(save[i] == 't')
				{
					save[i] = 'a';
				}
			}
		}
		return save;
	}
	/**
	 * This method calls on the getReverseCompSeq() in order to save the reverse compliment of seqarr to the seqarr array
	 */
	public void reverseCompliment()
	{
		char[] temp = getReverseCompSeq();
		for(int i = 0; i <= seqarr.length-1; i++)
		{
			seqarr[i] = temp[i];
		}
	}
}
