package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.io.File;

/**
 * @author Henry Kansanback
 *
 * An entry tree class
 * Add Javadoc comments to each method
 */
public class EntryTree<K, V> {
	/**
	 * dummy root node made public for grading
	 */
	protected Node root;
	
	/**
	 * prefixlen is the largest index such that the keys in the subarray keyarr
	 * from index 0 to index prefixlen - 1 are, respectively, with the nodes on
	 * the path from the root to a node. prefixlen is computed by a private
	 * method shared by both search() and prefix() to avoid duplication of code.
	 */
	protected int prefixlen;

	protected class Node implements EntryNode<K, V> {
		protected Node child; // link to the first child node
		protected Node parent; // link to the parent node
		protected Node prev; // link to the previous sibling
		protected Node next; // link to the next sibling
		protected K key; // the key for this node
		protected V value; // the value at this node

		public Node(K aKey, V aValue) {
			key = aKey;
			value = aValue;
			child = null;
			parent = null;
			prev = null;
			next = null;
		}

		@Override
		public EntryNode<K, V> parent() {
			if(parent == null || parent.equals(null))
			{
				return null;
			}
			return parent;
		}

		@Override
		public EntryNode<K, V> child() {
			if(child == null)
			{
				return null;
			}
			return child;
		}

		@Override
		public EntryNode<K, V> next() {
			if(next == null)
			{
				return null;
			}
			return next;
		}

		@Override
		public EntryNode<K, V> prev() {
			if(prev == null)
			{
				return null;
			}
			return prev;
		}

		@Override
		public K key() {
			if(key == null)
			{
				return key;
			}
			return key;
		}

		@Override
		public V value() {
			return value;
		}
	}

	public EntryTree() {
		root = new Node(null, null);
	}

	/**
	 * Returns the value of the entry with a specified key sequence, or null if
	 * this tree contains no entry with the key sequence.
	 * 
	 * @param keyarr
	 * @return
	 */
	public V search(K[] keyarr) {//make sure to modify code to distinguish prefix functionality and search, so as not to return the value partially
		//through the array due to the shorter length of the tree
		if(keyarr == null || keyarr.length == 0)
		{
			return null;
		}
		if(nulCheck(keyarr))
		{
			throw new NullPointerException();
		}
		
		Node i = findNode(keyarr);
		
		return i.value;
	}

	/**
	 * The method returns an array of type K[] with the longest prefix of the
	 * key sequence specified in keyarr such that the keys in the prefix label
	 * the nodes on the path from the root to a node. The length of the returned
	 * array is the length of the longest prefix.
	 * 
	 * @param keyarr
	 * @return
	 */
	public K[] prefix(K[] keyarr) {
		if(keyarr == null || keyarr.length == 0)
		{
			return null;
		}
		if(nulCheck(keyarr))
		{
			throw new NullPointerException();
		}
		findNode(keyarr);
		
		K[] y = Arrays.copyOf(keyarr, prefixlen);
		if(prefixlen == 0 || y.length == 0)
		{
			return null;
		}
		return y;
		// Hint: An array of the same type as keyarr can be created with
		// Arrays.copyOf().

	}

	/**
	 * The method locates the node P corresponding to the longest prefix of the
	 * key sequence specified in keyarr such that the keys in the prefix label
	 * the nodes on the path from the root to the node. If the length of the
	 * prefix is equal to the length of keyarr, then the method places aValue at
	 * the node P and returns true. Otherwise, the method creates a new path of
	 * nodes (starting at a node S) labelled by the corresponding suffix for the
	 * prefix, connects the prefix path and suffix path together by making the
	 * node S a child of the node P, and returns true.
	 * 
	 * @param keyarr
	 * @param aValue
	 * @return
	 */
	public boolean add(K[] keyarr, V aValue) { //create a link and an unlink method to perform this and the remove method
		if(keyarr.equals(null) || keyarr.length == 0)
		{
			return false;
		}
		if(nulCheck(keyarr))
		{
			throw new NullPointerException();
		}
		K[] ket = prefix(keyarr);
		Node p = findNode(ket);
		
		if(ket != null && ket.length == keyarr.length)
		{
			p.value = aValue;
			return true;
		}
		else
		{
			EntryNode[] arr = nMaker(keyarr, ket);

			if(ket == null)
			{
				link((Node) arr[0], root);
				for(int i = 1; i< keyarr.length; i++)
				{
					link((Node) arr[i],(Node) arr[i-1]);
				}
				p = findNode(keyarr);
				p.value = aValue;
				return true;
			}
			if(ket.length < keyarr.length)
			{
				int t = arr.length;
				EntryNode y = findNode(ket);
				for(int i = 0; i < t; i++)
				{
					link((Node) arr[i], (Node) y);
					y =  arr[i];
					if(i >= keyarr.length)
					{
						p = findNode(keyarr);
						p.value = aValue;
					}
				}
				p = findNode(keyarr);
				p.value = aValue;
				return true;
			}
			if(ket.length > keyarr.length)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the entry for a key sequence from this tree and returns its value
	 * if it is present. Otherwise, it makes no change to the tree and returns
	 * null.
	 * 
	 * @param keyarr
	 * @return
	 */
	public V remove(K[] keyarr) {
		if(keyarr.equals(null) || keyarr.length == 0)
		{
			return null;
		}
		if(nulCheck(keyarr))
		{
			throw new NullPointerException();
		}
		Node v = findNode(keyarr);
		if(v == null)
		{
			return null;
		}
		V i = search(keyarr);
		v.value = null;
		if(!v.equals(root) && i == null)
		{
			if(v.prev == null && v.next == null)
			{
				if(v.parent != root)
				{
					unlink(v.parent);
				}
				unlink(v);
			}
			else if(v.prev != null || v.next != null)
			{
				unlink(v);
			}
			
		}
		return i;
	}
	
	
	/**
	 * Creates an array of nodes from items in the array keyarr that are not present in the array ket. This method is used by the add method to simplify
	 * the adding process
	 * @param keyarr
	 * @param ket
	 * @return
	 */
	private EntryNode<K,V>[] nMaker(K[] keyarr, K[] ket)
	{

		EntryNode<K,V>[] v = new EntryNode[keyarr.length];
		if(ket != null && keyarr != null)
		{
			int o = ((keyarr.length) - ket.length);
			//v = Arrays.copyOf(v, o);
			v = new EntryNode[o];
			int t = 0;
			for(int i = ket.length; o > t;i++, t++)//TODO, change the iterator values
			{
				v[t] = new Node(keyarr[i], null);
			}
		}
		if(ket == null)
		{
			int o = keyarr.length;
			//v = Arrays.copyOf(v, o);
			v = new EntryNode[keyarr.length];
			for(int i = 0; i<o; i++)
			{
				v[i] = new Node(keyarr[i],null);
			}
		}
		return v;
	}

	
	
	/**
	 * To be used in conjunction with the add method. Links the toAdd method as a child of current and as a sibling of currents children if present
	 * @param toAdd
	 * @param current
	 * @return
	 */
	private boolean link(Node toAdd, Node current) //TODO, change the first if statement
	{
		
		if(current.child == null)
		{
			toAdd.parent = current;
			current.child = toAdd;
		}
		else if(current.child != null)
		{
			Node z = current.child;
			while(z.next != null)
			{
				z = z.next;
			}
			toAdd.parent = current;
			z.next = toAdd;
			toAdd.prev = z;
		}
		return true;
	}

	
	/**
	 * Unlinks a node from all Nodes that are related to it.
	 * @param toRemove
	 * @return
	 */
	private boolean unlink(Node toRemove)
	{
		if(toRemove != null && toRemove != root)
		{
			if(toRemove.child == null)
			{
				Node i = toRemove;
				while(i.parent != null && i != root && i.value == null)
				{
					if(i.next == null && i.prev == null)
					{
						i.child = null;
						
					}
					else if(i.next != null || i.prev != null)
					{
						i.prev.next = i.next;
						i.next.prev = i.prev;
						return true;
					}
					i = i.parent;
					i.key = null;
				}
				return true;
			}
			else if(toRemove.child != null)
			{
				toRemove.value = null;
			}
			return true;
		}
		return false;
	}
	/**
	 * The method prints the tree on the console in the output format shown in
	 * an example output file.
	 */
	public void showTree() {
		showTo(root, 0);
	}



	/**
	 * Recursive showTree method that scans through and prints the node v with tabbing and siblings based on depth n 
	 * @param v Node to be printed
	 * @param n Depth of v and its siblings
	 * @return Technically the method isn't used to return a real value.
	 */
	private Node showTo(Node v, int n)
	{
		if(root.child == null)
		{
			return null;
		}
		if(v == root)
		{
			System.out.println(root.key().toString() + " -> " + root.value().toString());
			showTo(root.child, n + 1);
		}
		else if(v.child != null)
		{
			System.out.println("");
			for(int o = 0; o < n; o++)
			{
				System.out.print("\t");
			}
			System.out.print(v.key.toString() + " -> " + v.value.toString());
			showTo(v.child, n+1);
		}
		if(v.next != null)
		{
			v = v.next;
			System.out.println("");
			for(int o = 0; o < n; o++)
			{
				System.out.print("\t");
			}
			System.out.print(v.key.toString() + " -> " + v.value.toString());
			if(v.child != null)
			{
				showTo(v.child, n+1);
			}
		}
		if(v.parent != null && v.parent != root)
		{
			return v.parent;
		}
		return v;
	}
	
	
	/**
	 * This method is mildly redundant, but it serves as an extra barrier to any issues in the program
	 * @param ket
	 * @return
	 */
	private Node findNode(K[] ket)
	{
		if(ket == null || ket.length == 0)
		{
			return null;
		}
		
		Node v = cont(ket);
		if(v == null)
		{
			return null;
		}
		return v;
	}

	
	/**
	 * This method scans through the tree looking for the array provided.
	 * @param ket
	 * @return
	 */
	private Node cont(K[] ket)
	{
		EntryNode<K,V> v = root;
		int i;
		for(i = 0; i < ket.length; i++)
		{
			v = v.child();
			if(v == null)
			{
				return null;
			}
			
			while((v.next() != null && v.key() != ket[i]))
			{
				v = v.next();
			}
			if(v.key() != ket[i] && v.next() == null && v.parent() != root)
			{
				v = v.parent();
				break;
			}
			else if(v.parent() == root && v.key() != ket[i])
			{
				prefixlen = i;
				return null;
			}
			if(i == ket.length && v.key() == ket[i])
			{
				prefixlen = i;
				return (Node) v;
			}
		}
		prefixlen = i;
		return (Node) v;
	}
	/**
	 * Helper method that checks for a null point in the array provided
	 * @param ket
	 * @return
	 */
	private boolean nulCheck(K[] ket)
	{
		for(int i = 0; i < ket.length; i++)
		{
			if(ket[i].equals(null))
			{
				return true;
			}
		}
		return false;
	}
	
	
}
