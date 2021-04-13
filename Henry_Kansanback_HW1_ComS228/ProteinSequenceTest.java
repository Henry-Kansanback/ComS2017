package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProteinSequenceTest {

	@Test
	public void test() {
		try{
		String probst4 = new String("BJU");
		ProteinSequence probj = new ProteinSequence( probst4.toCharArray() );
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("IllegalArgumentException " + e.getMessage());
		}
	}
	@Test
	public void test1(){
		String probst5 = new String("AGT");
		ProteinSequence probj = new ProteinSequence(probst5.toCharArray());
		ProteinSequence probj1 = new ProteinSequence(probst5.toCharArray());
		assertNotSame("These objects refer to the same object", probj, probj1);
	}

}
