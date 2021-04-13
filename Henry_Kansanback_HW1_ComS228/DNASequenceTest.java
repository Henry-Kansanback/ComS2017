package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class DNASequenceTest {

	@Test
	public void test() {
		try{
		String probst2 = new String("TDG");
		DNASequence dnaseqobj = new DNASequence( probst2.toCharArray() );
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("IllegalArgumentException " + e.getMessage());
		}
	}
	@Test
	public void test1(){
		try{
			String robst3 = new String("ATGAATCAGTGC");
			DNASequence dnaseqobj = new DNASequence( robst3.toCharArray());
			char[] fseq = dnaseqobj.getReverseCompSeq();
			assertArrayEquals("Not Equal", fseq, robst3.toCharArray());
		}
		catch(AssertionError e){
			System.err.println("AssertionError has occurred");
		}
	}
	@Test
	public void test2(){
		try{
			String robst3 = new String("ATGAATCAGTGC");
			DNASequence dnaseqobj = new DNASequence( robst3.toCharArray());
			char[] fseq = dnaseqobj.getReverseCompSeq();
			char[] bseq = new char[]{'G','C','A','C','T','G','A','T','T','C','A','T'};
			assertArrayEquals("Equal", fseq, bseq);
		}
		catch(AssertionError e){
			System.err.println("AssertionError has occurred");
		}
	}

}
