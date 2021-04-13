package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class CodingDNASequenceTest {

	@Test(expected = IllegalArgumentException.class)
	public void test() {
		try{
			String probst = new String("T$G");
			CodingDNASequence seqobj = new CodingDNASequence( probst.toCharArray());
			}
			catch(IllegalArgumentException e)
			{
			System.err.println("IllegalArgumentException " + e.getMessage());
			}
	}
	@Test
	public void test1()
	{
		String probst = new String("ATG");
		CodingDNASequence seqobj = new CodingDNASequence( probst.toCharArray());
		assertTrue("Should be True",seqobj.checkStartCodon());
	}
	@Test
	public void test2()
	{
		String probst = new String("GtA");
		CodingDNASequence seqobj = new CodingDNASequence( probst.toCharArray());
		assertFalse("Should be False", seqobj.checkStartCodon());
	}
	@Test
	public void test3()
	{
		String probst = new String("aT");
		CodingDNASequence seqobj = new CodingDNASequence( probst.toCharArray());
		assertFalse("Should be False", seqobj.checkStartCodon());
	}
	@Test
	public void test4()
	{
		String probst = new String("ATGAATCAGTGC");
		CodingDNASequence seqobj = new CodingDNASequence( probst.toCharArray());
		assertTrue("Should be True", seqobj.checkStartCodon());
	}
	@Test
	public void test5()
	{
		String probst = new String("ATGAATCAGTGC");
		CodingDNASequence seqobj = new CodingDNASequence(probst.toCharArray());
		char[] two = seqobj.translate();
		char[] one = new char[]{'M','N','Q','C'};
		assertEquals("sequences should be equal", two, one);
	}
	
	@Test(expected = AssertionError.class)
	public void test6()
	{
		String probst = new String("ATGAATCAGTAG");
		CodingDNASequence seqobj = new CodingDNASequence( probst.toCharArray());
		char[] two = seqobj.translate();
		char[] one = new char[]{'M','N','Q'};
		assertEquals("Invalid sequence character", two, one);
	}

}
