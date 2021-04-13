package edu.iastate.cs228.hw5;
/*
 *  @author Henry Kansanback
 *
 */
/**
 * @author Henry Kansanback
 */
import java.util.HashMap;
import java.util.Iterator;

public class DFS
{
    // This method creates a color map and a pred map (see lecture code file DiGraph.java)
    // and an empty stack of type LinkedStack<V>. It colors each vertex green
    // and sets the value of each vertex to null in the pred map (see lecture code).
    // Then as long as there is a green vertex w left, the method calls visitDFS()
    // on the vertex w along with other parameters.
    // If visitDFS() returns false, then this method returns null.
    // Otherwise, it returns the stack containing the list of all vertices in
    // a topological order, which is produced by the visitDFS() method.
    //
    // If aGraph is null, then it throws IllegalArgumentException.

	/**
	 * This method sets up the DFS method by setting all values in color to green and all values in pred to null. The method returns null if visitDFS returns
	 * false and the method returns em if visitDFS return true on all vertices.
	 * @param aGraph
	 * The directed graph inputed
	 * @return em
	 * LinkedStack that is used in the class MaxPath
	 */
    public static <V> LinkedStack<V> depthFirstSearch(DiGraph<V> aGraph)
    {
    	if(aGraph == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	HashMap<V,String> color = new HashMap<V,String>();
    	HashMap<V,V> pred = new HashMap<V,V>();
    	LinkedStack<V> em = new LinkedStack<V>();
    	for(V w : aGraph.vertices())
    	{
    		color.put(w, "green");
    		pred.put(w, null);
    	}
    	
    	for(V w : aGraph.vertices())
    	{
    		if(color.get(w).equals("green"))
    		{
    			if(visitDFS(aGraph, w, color, pred, em) == false)
    			{
    				return null;
    			}
    		}
    	}
		return em;
    }

    // This method implements an iterative depth-first search algorithm for
    // checking if the given graph is acyclic (has no cycles) and if so,
    // generates a stack (named topoOrder) of all vertices in a topological order
    // and returns true. Otherwise, it returns false. An iterative depth-first 
    // search algorithm is given in lecture for an undirected graph. Here, you need
    // to modify the algorithm to make it work for a directed graph. Note that
    // the edge iterator citer should be changed from type Iterator<V> to
    // type Iterator<Edge<V, Integer>>, and that citer.next().getVertex(),
    // instead of citer.next(), gives the vertex w. If the vertex w is red,
    // then the graph has a cycle. So the method returns false.
    // Whenever a vertex is colored black, the vertex is pushed onto the stack
    // topoOrder. If the graph has no cycles (the execution reaches the end of the method),
    // then the method returns true.

    /**
     * This is a depth search first method that begins at s marking each value in aGraph as red as they are reached via the vertexes edges. The predecessors of
     * the Vertices are also marked in order to "Back out" of the loop to color the items black and to push the items into topoOrder which is used elsewhere.
     * 
     * On a side note, I've been rather paranoid about this method and am unsure if this fits the criteria for a directed graph. I used the undirected graph as
     * a starting point, but there didn't appear to be much that needed changing and the class functions as it should. The program as a whole produces the output
     * it should from the given main method which is comforting, but I feel as if I haven't done enough still.
     * 
     * @param aGraph
     * The directed graph that is searched through
     * @param s
     * The starting point
     * @param color
     * a HashMap of colors which are used to mark unvisited nodes, visited nodes, and completed nodes.
     * @param pred
     * a HashMap of predecessor nodes.
     * @param topoOrder
     * a LinkedStack to place the finished nodes as the method begins to complete
     * @return boolean
     * returns a boolean value indicating whether or not there was a cycle.
     */
    protected static <V> boolean visitDFS(DiGraph<V> aGraph, V s,
            HashMap<V, String> color, HashMap<V, V> pred, LinkedStack<V> topoOrder)
    {
    	color.put(s, "red");
    	LinkedStack<V> nt = new LinkedStack<V>();
    	LinkedStack<Iterator<Edge<V,Integer>>> edges = new LinkedStack<Iterator<Edge<V,Integer>>>();
    	Iterator<Edge<V, Integer>> siter = aGraph.adjacentTo(s).iterator();
    	nt.push(s);
    	edges.push(siter);
    	while(!nt.isEmpty())
    	{
    		V d = nt.peek();
    		Iterator<Edge<V, Integer>> citer = edges.peek();
    		if(citer.hasNext())
    		{
    			V w = citer.next().getVertex();
    			if(color.get(w).equals("green"))
    			{
    				color.put(w, "red");
    				pred.put(w, d);
    				Iterator<Edge<V, Integer>> witer = aGraph.adjacentTo(w).iterator();
    				
    				nt.push(w);
    				edges.push(witer);
    			}
    			else if(color.get(w).equals("red"))
    			{
    				return false;
    			}
    		}
    		else
    		{
    			color.put(d,"black");
    			topoOrder.push(d);
    			nt.pop();
    			edges.pop();
    		}
    	}
		return true;
    } 
}
