package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Henry Kansanback
 * 
 *         An application class
 */
public class Dictionary {
	public static void main(String[] args) throws FileNotFoundException {
		File in = new File(args[0]);
		Scanner sc = new Scanner(in);
		String ino;
		EntryTree<Character, String> tree = new EntryTree<Character,String>();
		while(sc.hasNextLine())
		{
			ino = sc.next();
			if(ino.equals("showtree"))
			{
				tree.showTree();
				sc.nextLine();
				continue;
			}
			String op = sc.next();
			Character[] p = new Character[op.length()];
			for(int i = 0; i < op.length(); i++)
			{
				p[i] = op.charAt(i);
			}
			switch(ino)
			{
			case "add" : tree.add(p, sc.next()); sc.nextLine(); continue;
			case "search" : System.out.println(tree.search(p));  sc.nextLine(); continue;
			case "remove" : System.out.println(tree.remove(p)); sc.nextLine(); continue;
			case "prefix" : System.out.println(Arrays.toString(tree.prefix(p))); tree.prefix(p); sc.nextLine(); continue;
			}
		}
		sc.close();

	}
}
