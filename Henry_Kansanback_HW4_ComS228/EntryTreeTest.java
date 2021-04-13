package edu.iastate.cs228.hw4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntryTreeTest {

	public EntryTree<Integer, String> tree;
	public Integer[] t;
	public Integer[] b;
	
	@Before
	public void testStart(){
		tree = new EntryTree<Integer, String>();
		t = new Integer[]{13, 12, 41, 21, 30, 40};
		b = new Integer[]{13, 12, 45, 56};
		tree.add(t, "the");
		tree.add(b, "Because");
	}
	@Test
	public void test1() {
		boolean v = tree.add(t, "of");
		assertEquals(true, v);
	}
	
	@Test
	public void testlen(){
		Integer[] i = tree.prefix(new Integer[]{13,12,45});
		assertEquals(3, i.length);
	}
	
	@Test
	public void testlen2(){
		Integer[] i = tree.prefix(new Integer[]{13,12,45,56,73});
		assertEquals(4, i.length);
	}
	
	@Test
	public void test2() {
		tree.add(t, "of");
		String y = tree.search(new Integer[]{13,12,45,56});
		assertEquals("Because", y);
	}
	
	@Test
	public void test3() {
		tree.add(t, "of");
		String y = tree.search(new Integer[]{13,12,41,21,30,40});
		assertEquals("of", y);
	}
	
	@Test
	public void test7() {
		tree.add(t, "of");
		String y = tree.search(new Integer[]{13,12,45,56});
		assertNotEquals("because", y);
	}
	
	@Test
	public void test4() {
		String y = tree.search(new Integer[]{13,12,41,21,30,40});
		assertEquals("the", y);
	}

	@Test
	public void test5(){
		tree.add(new Integer[]{13,12,41,21,30,55}, "ook");
		String y = tree.search(new Integer[]{13,12,41,21,30,55});
		assertEquals("ook", y);
	}
	@Test
	public void test6(){
		tree.add(new Integer[]{13,12,45,56,73,44}, "ook");
		String y = tree.search(new Integer[]{13,12,45,56,73,44});
		assertEquals("ook", y);
	}
	@Test
	public void test1pre(){
		tree.add(new Integer[]{13,12,45,56,73,44}, "ook");
		Integer[] y = tree.prefix(new Integer[]{13,12,45,56});
		assertArrayEquals(b, y);
	}
	
	@Test
	public void test2pre(){
		tree.add(new Integer[]{13,12,45,56,73,44}, "ook");
		Integer[] y = tree.prefix(new Integer[]{13, 12, 41, 21, 30, 40});
		assertArrayEquals(t, y);
	}
	
	@Test
	public void test1rem(){
		tree.add(new Integer[]{13,12,45,56,73,44}, "ook");
		String y = tree.search(new Integer[]{13,12,45,56,73,44});
		String i = tree.remove(b);
		assertEquals("ook", y);
	}
	
	
	@Test
	public void test2rem(){
		tree.add(new Integer[]{13,12,45,56,73,44}, "ook");
		tree.search(new Integer[]{13,12,45,56,73,44});
		String i = tree.remove(b);
		assertEquals("Because", i);
	}
	
	@Test
	public void test3rem(){
		tree.add(new Integer[]{13,12,45,56,73,44}, "ook");
		tree.add(new Integer[]{13,12,45,56,73,44}, null);
		String y = tree.search(new Integer[]{13,12,45,56,73,44});
		tree.remove(b);
		assertNotEquals("ook", y);
	}
	
	@Test
	public void test4rem(){
		tree.add(new Integer[]{13,12,45,56,73,44}, "ook");
		tree.search(new Integer[]{13,12,45,56,73,44});
		tree.remove(b);
		String o = tree.search(new Integer[]{13,12,45,56,73,44});
		assertEquals("ook", o);
	}
	@Test
	public void test5rem(){
		tree.add(new Integer[]{13,12,45,56,73,44}, "ook");
		tree.search(new Integer[]{13,12,45,56,73,44});
		tree.remove(b);
		String o = tree.search(new Integer[]{13,12,45,56});
		assertNull(o);
	}
	@Test
	public void test6rem(){
		tree.add(new Integer[]{13,12,45,56,73,44}, "ook");
		tree.search(new Integer[]{13,12,45,56,73,44});
		String i = tree.remove(new Integer[]{11});
		String o = tree.search(new Integer[]{13,12,45,56});
		assertNull(i);
	}
	@Test (expected = NullPointerException.class)
	public void test1ser(){
		tree.add(new Integer[]{13,12,45,56,73,44}, "ook");
		tree.search(new Integer[]{13,12,45,56,73,44,99});
		String i = tree.remove(b);
		//assertEquals("Because", i);
	}
	@Test (expected = NullPointerException.class)
	public void test2ser(){
		tree.add(new Integer[]{13,12,45,56,73,44}, "ook");
		tree.search(new Integer[]{13,12,45,null,73,44});
		String i = tree.remove(b);
		//assertEquals("Because", i);
	}
	@Test (expected = NullPointerException.class)
	public void test3ser(){
		tree.add(new Integer[]{13,12,null,56,73,44}, "ook");
		tree.search(new Integer[]{13,12,45,56,73,44});
		String i = tree.remove(b);
		//assertEquals("Because", i);
	}
	
	@Test (expected = NullPointerException.class)
	public void test3pre(){
		tree.add(new Integer[]{13,12,null,56,73,44}, "ook");
		tree.prefix(new Integer[]{13,12,45,56,73,44});
		String i = tree.remove(b);
		//assertEquals("Because", i);
	}
	@Test (expected = NullPointerException.class)
	public void test4pre(){
		tree.add(new Integer[]{13,12,45,56,73,44}, "ook");
		tree.prefix(new Integer[]{13,12,null,56,73,44});
		String i = tree.remove(b);
		//assertEquals("Because", i);
	}
}
