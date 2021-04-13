package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class SequenceTest {

	@Test(expected = IllegalArgumentException.class)
	public void test1() {
		try{
		String probst = new String("T$G");
		Sequence seqobj = new Sequence( probst.toCharArray());
		}
		catch(IllegalArgumentException e)
		{
		System.err.println("Should throw IllegalArgumentException " + e.getMessage());
		}
	}
	
	@Test
	public void test2() {
		String probst = new String("ATG");
		
		Sequence seqobj = new Sequence(probst.toCharArray());
		char[] sea = seqobj.getSeq();
		assertTrue(sea.equals(seqobj));
	}
	@Test
	public void test3() {
		String probst = new String("ATG");
		
		Sequence seqobj = new Sequence(probst.toCharArray());
		Sequence sea = new Sequence(seqobj.getSeq());
		assertEquals("sea should be equal to seqobj", sea, seqobj);
	}

}
