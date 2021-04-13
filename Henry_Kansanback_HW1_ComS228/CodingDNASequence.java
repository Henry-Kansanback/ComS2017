package edu.iastate.cs228.hw1;
/**
 * 
 * @author Henry Kansanback
 *This class is in charge of codons and amino acids
 */
public class CodingDNASequence extends DNASequence{
/**
 * Constructor for CodingDNASequence class
 * @param cdnaar
 * input char array, passed to the superclass
 */
	public CodingDNASequence(char[] cdnaar) {
		super(cdnaar);

	}
	/**
	 * Method determines if starting codon is valid
	 * @return boolean
	 * returns boolean to indicate if starting codon is valid
	 */
	public boolean checkStartCodon()
	{
		if(seqarr.length < 3)
		{
			return false;
		}
		if((seqarr[0] == 'A' || seqarr[0] == 'a') && (seqarr[1] == 'T' || seqarr[1] == 't') && (seqarr[2] == 'G' || seqarr[2] == 'g'))
		{
			return true;
		}
		else return false;
	}
	/**
	 * This method is used to output amino acids based on codons inputed.
	 * 
	 * @param codon
	 * codon is a short 3 unit string that (if valid) corresponds to an amino acid.
	 * @return Amino Acid
	 * returns the amino acid that corresponds to the inputed codon.
	 */
	private char getAminoAcid(String codon)
	{
		if ( codon == null ) return '$';
	    char aa = '$';
	    switch ( codon.toUpperCase() )
	    {
	      case "AAA": aa = 'K'; break;
	      case "AAC": aa = 'N'; break;
	      case "AAG": aa = 'K'; break;
	      case "AAT": aa = 'N'; break;

	      case "ACA": aa = 'T'; break;
	      case "ACC": aa = 'T'; break;
	      case "ACG": aa = 'T'; break;
	      case "ACT": aa = 'T'; break;

	      case "AGA": aa = 'R'; break;
	      case "AGC": aa = 'S'; break;
	      case "AGG": aa = 'R'; break;
	      case "AGT": aa = 'S'; break;

	      case "ATA": aa = 'I'; break;
	      case "ATC": aa = 'I'; break;
	      case "ATG": aa = 'M'; break;
	      case "ATT": aa = 'I'; break;

	      case "CAA": aa = 'Q'; break;
	      case "CAC": aa = 'H'; break;
	      case "CAG": aa = 'Q'; break;
	      case "CAT": aa = 'H'; break;

	      case "CCA": aa = 'P'; break;
	      case "CCC": aa = 'P'; break;
	      case "CCG": aa = 'P'; break;
	      case "CCT": aa = 'P'; break;

	      case "CGA": aa = 'R'; break;
	      case "CGC": aa = 'R'; break;
	      case "CGG": aa = 'R'; break;
	      case "CGT": aa = 'R'; break;

	      case "CTA": aa = 'L'; break;
	      case "CTC": aa = 'L'; break;
	      case "CTG": aa = 'L'; break;
	      case "CTT": aa = 'L'; break;

	      case "GAA": aa = 'E'; break;
	      case "GAC": aa = 'D'; break;
	      case "GAG": aa = 'E'; break;
	      case "GAT": aa = 'D'; break;

	      case "GCA": aa = 'A'; break;
	      case "GCC": aa = 'A'; break;
	      case "GCG": aa = 'A'; break;
	      case "GCT": aa = 'A'; break;

	      case "GGA": aa = 'G'; break;
	      case "GGC": aa = 'G'; break;
	      case "GGG": aa = 'G'; break;
	      case "GGT": aa = 'G'; break;

	      case "GTA": aa = 'V'; break;
	      case "GTC": aa = 'V'; break;
	      case "GTG": aa = 'V'; break;
	      case "GTT": aa = 'V'; break;

	      case "TAA": aa = '$'; break;
	      case "TAC": aa = 'Y'; break;
	      case "TAG": aa = '$'; break;
	      case "TAT": aa = 'Y'; break;

	      case "TCA": aa = 'S'; break;
	      case "TCC": aa = 'S'; break;
	      case "TCG": aa = 'S'; break;
	      case "TCT": aa = 'S'; break;

	      case "TGA": aa = '$'; break;
	      case "TGC": aa = 'C'; break;
	      case "TGG": aa = 'W'; break;
	      case "TGT": aa = 'C'; break;

	      case "TTA": aa = 'L'; break;
	      case "TTC": aa = 'F'; break;
	      case "TTG": aa = 'L'; break;
	      case "TTT": aa = 'F'; break;
	      default:    aa = '$'; break;
	    }
	    return aa;
	}
	/**
	 * Translates seqarr into units of codons and then passes those codons to the getAminoAcid method in order to be turned
	 * into amino acids
	 * @return char[]
	 * returns char[] that represents the amino acid chain created by the method
	 */
	public char[] translate()
	{
		if(checkStartCodon() == false)
		{
			throw new RuntimeException("No start codon");
		}
		String out = "";
		for(int i = 0; i <= seqarr.length-1;)
		{
			String in = "";
			in = in + seqarr[i] + seqarr[i+1] + seqarr[i+2];
			if(getAminoAcid(in) == '$')
			{
				break;
			}
			else
			{
				out = out + getAminoAcid(in);
			}
			if(seqarr.length >= (i+3) && seqarr.length > (i+4) && seqarr.length > (i+5))
			{
				i = i+3;
			}
			else if(seqarr.length <= (i+3))
			{
				i = i +3;
			}
		}
		char[] full = new char[out.length()];
		for(int i = 0; i <= out.length()-1; i++)
		{
			full[i] = out.charAt(i);
		}
		return full;
	}
}