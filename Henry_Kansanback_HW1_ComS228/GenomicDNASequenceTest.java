package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenomicDNASequenceTest {

	@Test
	public void test() {
		try{
		String probst3 = new String("TGCH");
		GenomicDNASequence gdnaobj = new GenomicDNASequence( probst3.toCharArray());
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("IllegalArgumentException " + e.getMessage());
		}
	}
	@Test
	public void test1()
	{
		try{
			String robst3 = new String("TGC");
			GenomicDNASequence gdnaobj = new GenomicDNASequence( robst3.toCharArray());
			gdnaobj.markCoding(-1, -5);
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("IllegalArgumentException " + e.getMessage());
		}
	}
	
	@Test
	public void test2()
	{
		try{
			String robst3 = new String("TGC");
			GenomicDNASequence gdnaobj = new GenomicDNASequence( robst3.toCharArray());
			gdnaobj.markCoding(gdnaobj.seqLength(), 0);
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("IllegalArgumentException " + e.getMessage());
		}
	}
	
	@Test
	public void test3()
	{
		try{
			String robst3 = new String("ATGAATCAGTGC");
			GenomicDNASequence gdnaobj = new GenomicDNASequence( robst3.toCharArray());
			int[] inte = new int[]{1,5,6,8};
			char[] in = gdnaobj.extractExons(inte);
		}
		catch(IllegalStateException e)
		{
			System.err.println("IllegalStateException " + e.getMessage());
		}
	}
	
	@Test
	public void test4()
	{
		try{
			String robst3 = new String("ATGAATCAGTGC");
			GenomicDNASequence gdnaobj = new GenomicDNASequence( robst3.toCharArray());
			int[] inte = new int[]{0,5,6,8};
			gdnaobj.markCoding(0, 6);
			char[] in = gdnaobj.extractExons(inte);
		}
		catch(IllegalStateException e)
		{
			System.err.println("IllegalStateException " + e.getMessage());
		}
	}
	@Test
	public void test5()
	{
		try{
			String robst3 = new String("ATGAATCAGTGC");
			GenomicDNASequence gdnaobj = new GenomicDNASequence( robst3.toCharArray());
			int[] inte = new int[]{0,5,6,8};
			gdnaobj.markCoding(0, 8);
			char[] in = gdnaobj.extractExons(inte);
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("IllegalArgumentException " + e.getMessage());
		}
	}
	@Test
	public void test6()
	{
		try{
			String robst3 = new String("ATGAATCAGTGC");
			GenomicDNASequence gdnaobj = new GenomicDNASequence( robst3.toCharArray());
			int[] inte = new int[]{0,5,6,13};
			gdnaobj.markCoding(0, 11);
			char[] in = gdnaobj.extractExons(inte);
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("IllegalArgumentException " + e.getMessage());
		}
	}
	@Test
	public void test7()
	{
		try{
			String robst3 = new String("ATGAATCAGTGC");
			GenomicDNASequence gdnaobj = new GenomicDNASequence( robst3.toCharArray());
			int[] inte = new int[]{-3,5,6,10};
			gdnaobj.markCoding(0, 10);
			char[] in = gdnaobj.extractExons(inte);
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("IllegalArgumentException " + e.getMessage());
		}
	}
	@Test
	public void test8()
	{
		try{
			String robst3 = new String("ATGAATCAGTGC");
			GenomicDNASequence gdnaobj = new GenomicDNASequence( robst3.toCharArray());
			int[] inte = new int[0];
			gdnaobj.markCoding(0, 11);
			char[] in = gdnaobj.extractExons(inte);
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("IllegalArgumentException " + e.getMessage());
		}
	}
	@Test
	public void test9()
	{
		try{
			String robst3 = new String("ATGAATCAGTGCT");
			GenomicDNASequence gdnaobj = new GenomicDNASequence( robst3.toCharArray());
			int[] inte = new int[]{0,5,6,12, 7};
			gdnaobj.markCoding(0, 12);
			char[] in = gdnaobj.extractExons(inte);
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("IllegalArgumentException " + e.getMessage());
		}
	}
}
