package edu.iastate.cs228.hw3;
/*
 *  @author Henry Kansanback
 *
 *  An implementation of List<E> based on a doubly-linked list with an array for indexed reads/writes
 *
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * 
 * @author Henry Kansanback
 *
 * Definition Provided:
 * An implementation of List<E> based on a doubly-linked list with an array for indexed reads/writes
 * 
 * A note on the JavaDoc: I am following my lecturer's, Xiaoqiu Huang's, instruction in that I will only be writing JavaDoc for methods that are private
 * and are not overridden (i.e. they do not already have JavaDoc pre-made).
 * Reference Assingment 3 discussion board thread entitled "Javadoc Comments", posted on October 19th.
 * 
 * @param <E> This generic represent the elements to be stored in the list
 */
public class AdaptiveList<E> implements List<E>
{
  public class ListNode // private member of outer class
  {                     
    public E data;        // public members:
    public ListNode link; // used outside the inner class
    public ListNode prev; // used outside the inner class
    
    /**
     * The constructor for ListNode Objects
     * @param item a generic element that makes up the data of the ListNode
     */
    public ListNode(E item)
    {
      data = item;
      link = prev = null;
    }
  }
  
  public ListNode head;  // dummy node made public for testing.
  public ListNode tail;  // dummy node made public for testing.
  private int numItems;  // number of data items
  private boolean linkedUTD; // true if the linked list is up-to-date.

  public E[] theArray;  // the array for storing elements
  private boolean arrayUTD; // true if the array is up-to-date.

  /**
   * The primary/default constructor that is used to create a default AdaptiveList.
   * 
   * Calls clear() in order to create a default method.
   */
  public AdaptiveList()
  {
    clear();
  }

  @Override
  public void clear()
  {
    head = new ListNode(null);
    tail = new ListNode(null);
    head.link = tail;
    tail.prev = head;
    numItems = 0;
    linkedUTD = true;
    arrayUTD = false;
    theArray = null;
  }

  /**
   * This method provides the boolean value of linkedUTD
   * @return linkedUTD
   */
  public boolean getlinkedUTD()
  {
    return linkedUTD;
  }

  /**
   * This method provides the boolean value of arrayUTD
   * @return arrayUTD 
   */
  public boolean getarrayUTD()
  {
    return arrayUTD;
  }
  /**
   * This is our secondary constructor that takes in an element or a sequence of elements to create an AdaptiveList
   * @param c This is the Collection that possesses the elements to be stored in the AdaptiveList
   */
  public AdaptiveList(Collection<? extends E> c)
  {
    
	  this();
	  for(E e : c) //temporary
	  {
		  this.add(e);
		  //add(e);
		  
	  }
  }

  /** 
   * Definition provided:
   * Removes the node from the linked list.
   * This method should be used to remove a node from the linked list.
   * 
   * @param toRemove This is the node to be removed.
   */
  private void unlink(ListNode toRemove)
  {
    if ( toRemove == head || toRemove == tail )
      throw new RuntimeException("An attempt to remove head or tail");
    toRemove.prev.link = toRemove.link;
    toRemove.link.prev = toRemove.prev;
    //updateLinked();
    arrayUTD = false;
  }

  /** 
   * Definition provided:
   * Inserts new node toAdd right after old node current.
   * This method should be used to add a node to the linked list.
   * 
   * @param current This is the node directly behind the location that toAdd will go. It provides all of the links that set toAdd's new position
   * @param toAdd This is the node to be Added after current
   */
  private void link(ListNode current, ListNode toAdd)
  {
	  
    if ( current == tail )
      throw new RuntimeException("An attempt to link after tail");
    if ( toAdd == head || toAdd == tail )
      throw new RuntimeException("An attempt to add head/tail as a new node");
    toAdd.link = current.link;
    toAdd.link.prev = toAdd;
    toAdd.prev = current;
    current.link = toAdd;
    //updateLinked();
    arrayUTD = false;
  }

  /**
   * This method updates theArray using data from the currently up to date linked list. This method first checks if the size of the list is impossible and
   * then it checks if the linked list is currently up to date. The method then inserts the elements of the list into a new theArray.
   * 
   * Personal note - I found it to be a necessary evil to perform an unchecked cast in order to change the size of the current array. This was because
   * I couldn't figure out how to create a new array of type E or change theArray more directly
   */
  private void updateArray() // makes theArray up-to-date.
  {
    if ( numItems < 0 ){
      throw new RuntimeException("numItems is negative: " + numItems);
    }
    if ( ! linkedUTD ){
      throw new RuntimeException("linkedUTD is false");
    }
    //if(!arrayUTD)
    {
    	theArray = (E[]) new Object[numItems];
    	//Object[] ari = this.toArray(theArray);
    	//theArray = this.toArray(theArray);
    	ListIterator<E> iterator = listIterator();
    	for(int o = 0; o < numItems; o++)
    	{
    		theArray[o] = iterator.next();
    	}
	  }

    arrayUTD = true;
    }
    
  /**
   * This method "switches" the primary list (not list in the java sense). It does so first by checking whether or not the number of items
   * saved in an integer value is within the array. It then checks whether or not the array is up to date, and it then checks if theArray exists and
   * has elements in it.
   * The method then begins its primary function by checking if the linked list is up to date (there is no need to run the rest of the method if the
   * linked list is already up to date). The rest of the function is devoted to saving the currently up to date array into the linked list
   */
  private void updateLinked() // makes the linked list up-to-date.
  {
	  if ( numItems < 0 ){
		  throw new RuntimeException("numItems is negative: " + numItems);
	  }
	  if ( ! arrayUTD ){
		  throw new RuntimeException("arrayUTD is false");
	  }
	  if ( theArray == null || theArray.length < numItems ){
		  throw new RuntimeException("theArray is null or shorter");
	  }
	  if(!linkedUTD)
	  {
		  AdaptiveList<E> temp = new AdaptiveList<E>();
		  head = temp.head;
		  tail = temp.tail;

		  for(E e : theArray)
		  {
			  temp.add(e);
			  
		  }
		  numItems = temp.numItems;
	  }
	  linkedUTD = true;
	  
  }

  @Override
  public int size()
  {
    return numItems; // may need to be revised.
  }

  @Override
  public boolean isEmpty()
  {
	  boolean has = false;
	  if((head.link == tail && tail.prev == head) || numItems == 0)
	  {
		  has = true;
	  }
    return has; // may need to be revised.
  }

  @Override
  public boolean add(E obj)
  {
	  if(!linkedUTD && arrayUTD)
	  {
		  updateLinked();
	  }
	  else if(!linkedUTD && !arrayUTD)
	  {
		  updateArray();
		  updateLinked();
	  }
    if(isEmpty()){
    	ListNode Add = new ListNode(obj);
    	link(head, Add);
    	numItems++;
    	arrayUTD = false;
    	return true;
    }
    ListNode Add = new ListNode(obj);
    link(tail.prev, Add);
    numItems++;
	  arrayUTD = false;
    return true;
  }

  @Override
  public boolean addAll(Collection< ? extends E> c)
  {
	  if(!linkedUTD && arrayUTD)
	  {
		  updateLinked();
	  }
    if(c.isEmpty())
    {
    	return false;
    }
    int p = c.size();
    int counter = 0;
    for(E e : c)
    {
    	add(e);
    	counter++;
    	if(counter == p)
    	{
    		break;
    	}
    }
    return true; // may need to be revised.
  } // addAll 1

  @Override
  public boolean remove(Object obj) // rewrite for simplicity
  {
	  if(!linkedUTD)
	  {
		  updateLinked();
	  }
	  if(numItems <= 0)
	  {
		  return false;
	  }
    ListNode previous = null;
    ListNode current = head;
    while(current != null || current != tail)
    {
    	previous = current;
    	current = current.link;    	
    	if(obj == current.data || obj!=null && obj.equals(current.data))
    	{
    		break;// found obj
    	}

    }
    if(current == null) //didn't find obj
    {
    	return false;
    }
    if(previous != null) //found obj
    {
    	unlink(current);
    }
    numItems--;
    arrayUTD = false;
    return true; // may need to be revised.
  }
  
  /**
   * This method checks whether or not a given integer value is outside the list or is equal to the lists length minus the dummy nodes
   * @param pos The integer value to be checked
   */
  private void checkIndex(int pos) // a helper method
  {
    if ( pos >= numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }
  
  /**
   * This method checks whether or not a given integer value (presumably the position) is outside of the list
   * @param pos integer value to be checked
   */
  private void checkIndex2(int pos) // a helper method
  {
    if ( pos > numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  /**
   * This method checks whether or not a given node is nonexistent or is the tail
   * @param cur The node to be tested
   */
  private void checkNode(ListNode cur) // a helper method
  {
    if ( cur == null || cur == tail )
     throw new RuntimeException(
      "numItems: " + numItems + " is too large");
  }

  /**
   * This method finds the node at the given position integer
   * @param pos this is the location of the Node we are looking for
   * @return This method returns a ListNode object at the position integer given
   */
  private ListNode findNode(int pos)   // a helper method
  {
	  if(!linkedUTD)
	  {
		  updateLinked();
	  }
    ListNode cur = head;
    for ( int i = 0; i < pos; i++ )
    {
      checkNode(cur);
      cur = cur.link;
    }
    checkNode(cur);
    arrayUTD = false;
    return cur;
  }

  @Override
  public void add(int pos, E obj) 
  {
	  if(!linkedUTD)
	  {
		  updateLinked();
	  }
    checkIndex2(pos);
    if(pos == numItems)
    {
    	add(obj);
    	return;
    }
    
    ListNode Add = new ListNode(obj);
    if(pos == 0)
    {
    	ListNode current = head;
    	link(current, Add);
    	numItems++;
    	arrayUTD = false;
    	return;
    }
    ListNode current = findNode(pos);
    link(current, Add);
    numItems++;
    arrayUTD = false;
  }

  @Override
  public boolean addAll(int pos, Collection< ? extends E> c) // to solve the issue I need to copy c so that I can add consistently
  {
	  if(!linkedUTD)
	  {
		  updateLinked();
	  }
	  if(c.isEmpty())
	    {
		  arrayUTD = false;
	    	return false;
	    }
	  int i = 0;
	  int p = c.size();
	
	 AdaptiveList<? extends E> v = new AdaptiveList<>(c);
	 Iterator<? extends E> iterator = v.iterator();
	  for(E o = iterator.next(); p > i; o = iterator.next())
	  {
		  this.add(pos+i, o);
		  i++;
		  if(p == i)
		  {
			  break;
		  }
	  }
	    arrayUTD = false;
	    return true; // may need to be revised.
  } // addAll 2

  @Override
  public E remove(int pos) // find a way to correct findNode without editing it
  {
	  if(!linkedUTD)
	  {
		  updateLinked();
	  }
    checkIndex(pos);
    /*if(pos == 0)
    {
    	pos++;
    }*/
    ListNode current = findNode(pos).link;
    ListNode temp = current;
    unlink(current);
    numItems--;
    arrayUTD = false;
    return temp.data; // may need to be revised.
  }

  @Override
  public E get(int pos) //REVISE
  {
	  if(!arrayUTD)
	  {
		  updateArray();
	  }
	  checkIndex(pos);
	  E en = theArray[pos];
	  
	  if(!arrayUTD)
	    {
	    	updateArray();
	    }
	  linkedUTD = false;
    return en; // may need to be revised.
  }

  @Override
  public E set(int pos, E obj)
  {
	  if(!arrayUTD)
	  {
		  updateArray();
	  }
    checkIndex(pos);
    E temp = theArray[pos]; 
    theArray[pos] = obj;
    if(!arrayUTD)
    {
    	updateArray();
    }
    linkedUTD = false;
    return temp; // may need to be revised.
  } 

   
   
   
   
  /**
   * Definition Provided:
   * If the number of elements is at most 1, the method returns false.
   * Otherwise, it reverses the order of the elements in the array
   * without using any additional array, and returns true.
   * Note that if the array is modified, then linkedUTD needs to be set to false.
   * 
   * 
   * @return boolean value depending on whether or not the Array was changed
   */
  public boolean reverse()
  {
	  if(!arrayUTD)
	  {
		  updateArray();
	  }
    if(theArray.length <= 1)
    {
    	return false;
    }
    int n = theArray.length;
    for(int i = 0; i < n/2; i++)
    {
    	E o = theArray[n - (i + 1)];
    	E temp = theArray[i];
    	theArray[i] = o;
    	theArray[n - (i+1)] = temp;
    }
    if(!arrayUTD)
    {
    	updateArray();
    }
    linkedUTD = false;
    return true; // may need to be revised.
  }

  @Override
  public boolean contains(Object obj)
  {

	  int size = this.size();
	  int p = 0;
    for(Object e : this)
    {
    	//if(e != null)
    	{
    		if(obj == e || obj!= null && obj.equals(e))
    		{
    			return true;
    		}
    	}
    	p++;
    	if(p == size)
    	{
    		break;
    	}
    }

   return false; // may need to be revised.
  }

  @Override
  public boolean containsAll(Collection< ? > c)
  {
	  int size = this.size();
	  int i = 0;
    for(Object ob : c)
    {
    	if(!contains(ob))
    	{
    		return false;
    	}
    	i++;
    	if(i == size)
    	{
    		break;
    	}
    }
   return true; // may need to be revised.
  } // containsAll


  @Override
  public int indexOf(Object obj)
  {
	  if(!linkedUTD)
	  {
		  updateLinked();
	  }
    ListNode current = head.link;
    int pos = 0;
	for( pos = 0; current != null; current = current.link, pos++)
	{
		if(obj == current.data || (obj != null && obj.equals(current.data)))
		{
			arrayUTD = false;
			return pos;
		}
	}
	arrayUTD = false;
    return -1; // may need to be revised.
  }

  @Override
  public int lastIndexOf(Object obj)
  {
	  if(!linkedUTD)
	  {
		  updateLinked();
	  }
    ListIterator<E> iterator = listIterator(numItems);
    while(iterator.hasPrevious())
    {
    	Object data = iterator.previous();
    	if(obj == data || (obj != null && obj.equals(data)))
    	{
    		arrayUTD = false;
    		return iterator.nextIndex();
    	}
    }
    arrayUTD = false;
    return -1; // may need to be revised.
  }

  @Override
  public boolean removeAll(Collection<?> c)
  {
	  if(!linkedUTD)
	  {
		  updateLinked();
	  }
	  boolean hasChanged = false;
	  if(c == null)
	  {
		  throw new NullPointerException();
	  }
	  if(c.isEmpty() || isEmpty())
	  {
		  return false;
	  }
	  int i = 0;
	  int siz = c.size();
	  AdaptiveList<?> v = new AdaptiveList<>(c);
	  Iterator<?> iterator = v.iterator();
	  for(Object o = iterator.next(); siz > i; o = iterator.next())
	  {
		  if(this.contains(o))
		  {
			  this.remove(o);
			  hasChanged = true;
		  }


		  i++;
		  if(siz == i)
		  {
			  break;
		  }
	  }
	  
	  arrayUTD = false;
	  return hasChanged; // may need to be revised.
  
  }

  
  @Override
  public boolean retainAll(Collection<?> c)
  {
	  if(!linkedUTD)
	  {
		  updateLinked();
	  }
	  boolean hasChanged = false;
    if(c == null)
    {
    	throw new NullPointerException("Input was head, tail, or incorrect value");
    }
    int i = 0;
    int siz = c.size();
    for(Object e : this)
    {
    	if(!c.contains(e))
    	{
    		remove(e);
    		hasChanged = true;
    	}
    	i++;
    	if(i >= siz)
    	{
    		break;
    	}
    }
    
    arrayUTD = false;
    return hasChanged; // may need to be revised.
  }

  @Override
  public Object[] toArray() // rewrite and try to use this method
  {
	  if(!linkedUTD)
	  {
		  updateLinked();
	  }
	  Object[] array = new Object[numItems]; // + 2 for head and tail. remove if head and tail do not count
	  ListIterator<?> iterator = this.listIterator();
	  for(int i = 0; i < numItems; i++)
	  {
		  array[i] = iterator.next();
	  }
	  arrayUTD = false;
    return array; // may need to be revised.
  }
  
  @Override
  public <T> T[] toArray(T[] arr) // rewrite and try to use this method
  {
	  if(!linkedUTD)
	  {
		  updateLinked();
	  }
	  
    if(arr.length < numItems)
    {
    	arr = Arrays.copyOf(arr, numItems);
    	
    }
    System.arraycopy(toArray(), 0, arr, 0, size());
    
    // copy array differently
    if(arr.length > size())
    {
    	arr[numItems] = null;
    }
    arrayUTD = false;
    return arr; // may need to be revised.
  }

  @Override
  public List<E> subList(int fromPos, int toPos)
  {
    throw new UnsupportedOperationException();
  }

  /**
   * 
   * @author Henry Kansanback
   *
   * This class creates an iterator given an AdaptiveList.
   */
  private class AdaptiveListIterator implements ListIterator<E>
  {
    private int    index;  // index of next node;
    private ListNode cur;  // node at index - 1
    private ListNode last; // node last visited by next() or previous()

    /**
     * The primary/default constructor for this class. It sets the current node and last node to null and index to 0
     */
    public AdaptiveListIterator()
    {
      if ( ! linkedUTD ) updateLinked();
      cur = null;
      last = null;
      index = 0;
    }
    /**
     * the secondary constructor for this class. It checks to position cur will be set to initially
     * @param pos The integer value representing the initial value cur may be set to
     */
    public AdaptiveListIterator(int pos)
    {
      if ( ! linkedUTD ) updateLinked();
      checkIndex2(pos);
      index = pos;
      if(pos == 0)
      {
    	  cur = null;
      }
      if(!(pos == 0))
      {
    	  cur = findNode(pos);
      }
      last = null;
    }

    @Override
    public boolean hasNext()
    {
      if(cur == null)
      {
    	  if(head.link == tail)
    	  {
    		  return false;
    	  }
    	  else
    	  {
    		  return head.link != null;
    	  }
      }
      else if(cur != null && cur.link != tail)
      {
    	  return cur.link != null;
      } // may need to be revised.
      return false;
    }

    @Override
    public E next()
    {
      if(!hasNext())
      {
    	  throw new NoSuchElementException("There are no elements after current");
      }
      if(index >= numItems)
      {
    	  throw new RuntimeException("index or numItems is incorrect value");
      }
      index++;
      if(cur == null)
      {
    	  if(numItems == 0)
    	  {
    		  //last = cur;
    		  last = head.link;
    		  cur = head.link;
    		  return cur.data;
    	  }
    	  //last = cur;
    	  last = head.link;
    	  cur = head.link;
    	  //last = cur;
    	  return cur.data;
      }
      else if(cur != null && cur.link != null)
      {
    	  //last = cur;
    	  last = cur.link;
    	  cur = cur.link;
    	  //last = cur;
      }
      return cur.data; // may need to be revised.
    } 

    @Override
    public boolean hasPrevious()
    {
    	boolean has = true;
    	if(cur == null || cur.prev == null)
    	{
    		has = false;
    	}
    	return has; // may need to be revised.
    }

    @Override
    public E previous()
    {
      if(!hasPrevious())
      {
    	  throw new NoSuchElementException("hasPrevious was not been checked before running previous()");
      }
      if(index <= 0)
      {
    	  throw new RuntimeException("index is at head or is not real");
      }
      
      last = cur;
      cur = cur.prev;
      index--;
      return last.data; // may need to be revised.
    }
    
    @Override
    public int nextIndex()
    {
      
      return index; // may need to be revised.
    }

    @Override
    public int previousIndex()
    {
      
      return index - 1; // may need to be revised.
    }


    public void remove()
    {
      if(last == null)
      {
    	  throw new IllegalStateException("No call to previous or next");
      }
      if(cur == last)
      {
    	  if(index <= 0)
    	  {
    		  throw new RuntimeException("index should not be less than or equal to 0");
    	  }
    	  index--;
      }
      
      if(last == head)
      {
    	    unlink(last.link);
    	  last = null;
      }
      else
      {
    	  numItems--;
    	  if(cur == last) //unlink
    	  {
    		  cur = cur.prev;
    	  }
    	  cur.link = last.link;
      }
      last = null;
    }


    public void add(E obj)
    { 
      if(cur == null)
      {
    	  ListNode Add = new ListNode(obj);
      	link(head, Add);
      	cur = Add;
    	  last = null;
    	  index = 1;
    	  return;
      }
      ListNode Add = new ListNode(obj);
      //boolean t = false;
      //if(hasNext())
      link(cur, Add);
      cur = Add;
      last = null;
      numItems++;
      index++;
    		  
    } // add

    @Override
    public void set(E obj)
    {
      if(last == null)
      {
    	  throw new IllegalStateException();
      }
      last.data = obj;
    } // set
  } // AdaptiveListIterator
  
  @Override
  public boolean equals(Object obj)
  {
    if ( ! linkedUTD ) updateLinked();
    if ( (obj == null) || ! ( obj instanceof List<?> ) )
      return false;
    List<?> list = (List<?>) obj;
    if ( list.size() != numItems ) return false;
    Iterator<?> iter = list.iterator();
    for ( ListNode tmp = head.link; tmp != tail; tmp = tmp.link )
    {
      if ( ! iter.hasNext() ) return false;
      Object t = iter.next();
      if ( ! (t == tmp.data || t != null && t.equals(tmp.data) ) )
         return false;
    }
    if ( iter.hasNext() ) return false;
    return true;
  } // equals

  @Override
  public Iterator<E> iterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int pos)
  {
    checkIndex2(pos);
    return new AdaptiveListIterator(pos);
  }

  // Adopted from the List<E> interface.
  @Override
  public int hashCode()
  {
    if ( ! linkedUTD ) updateLinked();
    int hashCode = 1;
    for ( E e : this )
       hashCode = 31 * hashCode + ( e == null ? 0 : e.hashCode() );
    return hashCode;
  }

  // You should use the toString*() methods to see if your code works as expected.
  @Override
  public String toString()
  {
   String eol = System.getProperty("line.separator");
   return toStringArray() + eol + toStringLinked();
  }

  public String toStringArray()
  {
    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent array:" + eol );
    strb.append('[');
    if ( theArray != null )
      for ( int j = 0; j < theArray.length; )
      {
        if ( theArray[j] != null )
           strb.append( theArray[j].toString() );
        else
           strb.append("-");
        j++;
        if ( j < theArray.length )
           strb.append(", ");
      }
    strb.append(']');
    return strb.toString();
  }

  public String toStringLinked()
  {
    return toStringLinked(null);
  }

  // iter can be null.
  public String toStringLinked(ListIterator<E> iter)
  {
    int cnt = 0;
    int loc = iter == null? -1 : iter.nextIndex();

    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent linked list:" + eol );
    strb.append('(');
    for ( ListNode cur = head.link; cur != tail; )
    {
      if ( cur.data != null )
      {
        if ( loc == cnt )
        {
          strb.append("| ");
          loc = -1;
        }
        strb.append(cur.data.toString());
        cnt++;

        if ( loc == numItems && cnt == numItems )
        {
          strb.append(" |");
          loc = -1;
        }
      }
      else
         strb.append("-");
      
      cur = cur.link;
      if ( cur != tail )
         strb.append(", ");
    }
    strb.append(')');
    return strb.toString();
  }
}
