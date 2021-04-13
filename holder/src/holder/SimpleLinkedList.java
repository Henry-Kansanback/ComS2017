package holder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements List<E>
{
  private class ListNode // private member of outer class
  {                     
    public E data;        // public members:
    public ListNode link; // used outside the inner class
  }
  
  private ListNode head;
  private int numItems;

  public SimpleLinkedList()
  {
    head = null;
    numItems = 0;
  }

  public SimpleLinkedList(Collection<? extends E> c)
  {
    this();
    for ( E e : c )
       add(e);
  }

  public void addFirst(E obj)
  {
    ListNode toAdd = new ListNode();
    toAdd.data = obj;
    toAdd.link = head;
    head = toAdd;
    numItems++;
  } // constant time vs. O(n) time for an array


  // for demo only
  public void addFirst_Demo(E obj)
  {
    System.out.println("--------------------------------------------------------------------------------");
    String st = obj == null? "NULL_Value" : obj.toString();
    System.out.println( "Method to be demonstrated: addFirst(" + st + ")" );
    showOneStep("Before the addition", head, "head", -1, "");
    ListNode toAdd = new ListNode();
    toAdd.data = obj;
    toAdd.link = head;
    head = toAdd;
    showOneStep("toAdd.link = head; head = toAdd;", head, "head", -1, "");
    numItems++;
  } // constant time vs. O(n) time for an array

  @Override
  public int size()
  {
    return numItems;
  }

  @Override
  public boolean isEmpty()
  {
    return head == null;
  }

  @Override
  public void clear()
  {
    head = null;
    numItems = 0;
  }

  public void addLast(E obj)
  {
    if ( head == null )
     { addFirst(obj); return; }
     
    ListNode toAdd = new ListNode();
    toAdd.data = obj;
    toAdd.link = null;
    ListNode cur = head;
    while ( cur.link != null )
      cur = cur.link;
    cur.link = toAdd;
    numItems++;
  } // O(n) time

  // for demo only
  public void addLast_Demo(E obj)
  {
    System.out.println("--------------------------------------------------------------------------------");
    String st = obj == null? "NULL_Value" : obj.toString();
    System.out.println( "Method to be demonstrated: addLast(" + st + ")" );
    if ( head == null )
     { addFirst(obj); return; }
     
    ListNode toAdd = new ListNode();
    toAdd.data = obj;
    toAdd.link = null;
    ListNode cur = head;
    showOneStep("Before the while loop", cur, "cur", -1, "");
    while ( cur.link != null )
    { cur = cur.link;
      showOneStep("cur = cur.link", cur, "cur", -1, "");
    }
    cur.link = toAdd;
    showOneStep("cur.link = toAdd;", toAdd, "toAdd", -1, "");
    numItems++;

  } // constant time vs. O(n) time for an array

  public void removeFirst()
  {
    if ( head == null )
      throw new NoSuchElementException("empty list");
    head = head.link;
    numItems--;
  } // constant time vs. O(n) time for an array

  @Override
  public boolean add(E obj)
  {
     addLast(obj);
     return true;
  }

  @Override
  public boolean addAll(Collection< ? extends E> c)
  {
    if ( c.isEmpty() ) return false;
    for ( E e : c )
       add(e);
    return true;
  } // addAll 1


  public void removeLast()
  {
    if ( head == null )
      throw new NoSuchElementException("empty list");
    if ( head.link == null )
     { removeFirst(); return; }
    
    ListNode cur = head;
    ListNode prev = null;
    while ( cur.link != null )
    { prev = cur;
      cur = cur.link;
    }
    prev.link = null; // prev is not null
    numItems--;
  }

  // for demo only
  public void removeLast_Demo()
  {
    System.out.println("--------------------------------------------------------------------------------");
    System.out.println("Method to be demonstrated: removeLast()");
    if ( head == null )
      throw new NoSuchElementException("empty list");
    if ( head.link == null )
     { removeFirst(); return; }
    
    ListNode cur = head;
    ListNode prev = null;
    showOneStep("Before the while loop", cur, "cur", -1, "");
    while ( cur.link != null )
    { prev = cur;
      cur = cur.link;
      showOneStep("prev = cur; cur = cur.link;", cur, "cur", -1, "");
    }
    showOneStep("After the loop", prev, "prev", -1, "");
    prev.link = null; // prev is not null
    showOneStep("prev.link = null;", prev, "prev", -1, "");
    numItems--;
  }

  @Override
  public boolean remove(Object obj)
  {
    ListNode prev, cur;
    for ( prev = null, cur = head; cur != null; prev = cur, cur = cur.link )
      if ( obj == cur.data || obj != null && obj.equals(cur.data) )
          break;

    if ( cur == null ) return false; 
    if ( prev != null )
      prev.link = cur.link;
    else
      head = head.link; // cur == head
    numItems--;
    return true;
  }

  // The code of remove() is restructured for demo.
  public boolean remove_Demo(Object obj)
  {
    System.out.println("--------------------------------------------------------------------------------");
    String st = obj == null? "NULL_Value" : obj.toString();
    System.out.println("Method to be demonstrated: remove(" + st + ")");
    ListNode prev, cur;
    prev = null;
    cur = head;
    showOneStep("Before the while loop", cur, "cur", -1, "");
    while ( cur != null )
    {
      if ( obj == cur.data || obj != null && obj.equals(cur.data) )
          break;
      prev = cur;
      cur = cur.link;
      showOneStep("prev = cur; cur = cur.link;", cur, "cur", -1, "");
    }
    showOneStep("After the loop", prev, "prev", -1, "");

    if ( cur == null )
    {
      showOneStep("return false", prev, "prev", -1, "");
      return false; 
    }
    if ( prev != null )
    {
      prev.link = cur.link;
      showOneStep("prev.link = cur.link;", prev, "prev", -1, "");
    }
    else
    {
      head = head.link; // cur == head
      showOneStep("head = head.link;", head, "head", -1, "");
    }
    numItems--;
    showOneStep("return true;", head, "head", -1, "");
    return true;
  }

  private void checkIndex(int pos)
  {
    if ( pos >= numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkIndex2(int pos)
  {
    if ( pos > numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkNode(ListNode cur)
  {
    if ( cur == null )
     throw new RuntimeException(
      "numItems: " + numItems + " is too large");
  }

  private ListNode findNode(int pos)
  {
    ListNode cur = head;
    for ( int i = 0; i < pos - 1; i++ )
    {
      checkNode(cur);
      cur = cur.link;
    }
    checkNode(cur);
    return cur;
  } // return the node at index pos - 1

  @Override
  public void add(int pos, E obj)
  {
    checkIndex2(pos);
    if ( pos == 0 )
    {
      addFirst(obj);
      return;
    }
    ListNode toAdd = new ListNode();
    toAdd.data = obj;
    ListNode cur = findNode(pos);
    toAdd.link = cur.link;
    cur.link = toAdd;
    numItems++;
  } // add it after the node at index pos - 1

  // for demo only
  public void add_Demo(int pos, E obj)
  {
    System.out.println("--------------------------------------------------------------------------------");
    String st = obj == null? "NULL_Value" : obj.toString();
    System.out.println("Method to be demonstrated: add(" + pos + ", " + st + ")");
    checkIndex2(pos);
    if ( pos == 0 )
    {
      addFirst(obj);
      showOneStep("addFirst(obj); return;", head, "head", pos, "pos");
      return;
    }
    ListNode toAdd = new ListNode();
    toAdd.data = obj;
    ListNode cur = findNode(pos);
    showOneStep("ListNode cur = findNode(pos);", cur, "cur", pos-1, "pos-1");
    toAdd.link = cur.link;
    cur.link = toAdd;
    showOneStep("toAdd.link = cur.link; cur.link = toAdd;", toAdd, "toAdd", pos, "pos");
    numItems++;
  } // add it after the node at index pos - 1

  @Override
  public boolean addAll(int pos, Collection< ? extends E> c)
  {
    checkIndex2(pos);
    if ( c.isEmpty() ) return false;
    int i = pos;
    for ( E e : c )
      add(i++, e);
    return true;
  } // addAll 2

  @Override
  public E remove(int pos)
  {
    checkIndex(pos);
    if ( pos == 0 )
    { if ( head == null )
       throw new NoSuchElementException("Index 0");
      E returnVal = head.data;
      removeFirst();
      return returnVal;
    }
    ListNode cur = findNode(pos);
    ListNode tmp = cur.link;
    checkNode(tmp);
    cur.link = tmp.link;
    numItems--;
    return tmp.data;
  } // handy to have findNode()

  @Override
  public E get(int pos)
  {
    checkIndex(pos);
    ListNode cur = findNode(pos + 1);
    return cur.data;
  } // would have redundant code without findNode()

  @Override
  public E set(int pos, E obj)
  {
    checkIndex(pos);
    ListNode cur = findNode(pos + 1);
    E tmp = cur.data;
    cur.data = obj;
    return tmp;
  } // O(n) time vs. O(1) time for an array list

  @Override
  public boolean contains(Object obj)
  { ListNode cur;
    for ( cur = head; cur != null; cur = cur.link )
      if ( obj == cur.data || obj != null && obj.equals(cur.data) )
         return true;
   return false;
  }

  @Override
  public boolean containsAll(Collection< ? > c)
  {
    for ( Object o : c )
      if ( ! contains( o ) )
         return false;
    return true;
  } // containsAll


  @Override
  public int indexOf(Object obj)
  { ListNode cur;
    int pos = 0;
    for ( cur = head; cur != null; cur = cur.link, pos++ )
      if ( obj == cur.data || obj != null && obj.equals(cur.data) )
         return pos;
   return -1;
  }

  @Override
  public int lastIndexOf(Object obj)
  {
    ListIterator<E> iter = listIterator(numItems);
    while ( iter.hasPrevious() )
    {
      E data = iter.previous();
      if ( obj == data || obj != null && obj.equals(data) )
          return iter.nextIndex();
    }
   return -1;
  }

  @Override
  public boolean removeAll(Collection<?> c)
  {
    if ( c == null )
      throw new NullPointerException();
    boolean changed = false;
    ListIterator<E> iter = listIterator();
    while ( iter.hasNext() )
    {
      E data = iter.next();
      if ( c.contains(data) )
      {
        iter.remove();
        changed = true;
      }
    }
    return changed;
  }

  @Override
  public boolean retainAll(Collection<?> c)
  {
    if ( c == null )
      throw new NullPointerException();
    boolean changed = false;
    ListIterator<E> iter = listIterator();
    while ( iter.hasNext() )
    {
      E data = iter.next();
      if ( ! c.contains(data) )
      {
        iter.remove();
        changed = true;
      }
    }
    return changed;
  }

  @Override
  public Object[] toArray()
  {
    Object[] arr = new Object[numItems];
    ListIterator<E> iter = listIterator();
    for(int i = 0; i < numItems; i++)
       arr[i] = iter.next();
    return arr;
  }
  
  @Override
  public <T> T[] toArray(T[] arr)
  {
    if(arr.length < numItems)
       arr = Arrays.copyOf(arr, numItems);
    System.arraycopy(toArray(), 0, arr, 0, numItems);
    if(arr.length > numItems)
       arr[numItems] = null;
    return arr;
  }

  @Override
  public List<E> subList(int fromPos, int toPos)
  {
    throw new UnsupportedOperationException();
  }

/*
A listiterator is used to traverse the list in either direction.
Its cursor position is between the element from a call to previous()
and the element from a call to next(). There are n+1 possible 
cursor positions (marked by the sign '^') for a list with n elements:

   Element(0)   Element(1)   Element(2)   ... Element(n-1) 
 ^            ^            ^            ^                  ^
*/
  private class SimpleLinkedListIterator implements ListIterator<E>
  {
    private int    index;  // cursor position or index of next node;
    private ListNode cur;  // node at index - 1
    private ListNode last; // node last visited by next() or previous()

    public SimpleLinkedListIterator()   // covered in class
    {
      cur = last = null;
      index = 0;
    }
    public SimpleLinkedListIterator(int pos)   // covered in class
    {
      checkIndex2(pos);
      index = pos;
      last = null;
      cur = pos == 0 ? null : findNode(pos);
    }

    @Override
    public boolean hasNext()   // covered in class
    {
      if ( cur == null )
        return head != null;
      else
        return cur.link != null;
    }

    // Returns the element right after the cursor position and advances it.
    @Override
    public E next()   // covered in class
    {
      if ( ! hasNext() )
        throw new NoSuchElementException();
      if ( index >= numItems )
        throw new RuntimeException("index 1");
      index++;
      last = cur = cur == null ? head : cur.link;
      return cur.data; // cur != null
    } 

    @Override
    public boolean hasPrevious()   // covered in class
    {
      return cur != null;
    }

    private ListNode lookPrev(ListNode vet)   // covered in class
    {
      ListNode tmp;
      if ( vet == head ) return null;
      for ( tmp = head; tmp.link != vet; tmp = tmp.link )
        if ( tmp.link == null )
	  throw new RuntimeException("vet not on list");
      return tmp;
    }

    // Returns the element right before the the cursor position and moves it backwards.
    @Override
    public E previous()   // covered in class
    {
      if ( ! hasPrevious() )
        throw new NoSuchElementException();
      if ( index <= 0 ) 
        throw new RuntimeException("index 2");
      index--;
      last = cur;
      cur = lookPrev(cur);
      return last.data;
    }
    
    @Override
    public int nextIndex()
    {
      return index;
    }

    @Override
    public int previousIndex()
    {
      return index - 1;
    }

    /**
     * Removes from the list the last element that was returned
     * by next() or previous().
     */
    @Override
    public void remove()
    {
      if ( last == null ) // no previous call
        throw new IllegalStateException();
      if ( cur == last )
      {
        if ( index <= 0 ) 
          throw new RuntimeException("index 3");
        index--;
      } // update index if cur is the last node

      if ( last == head )
      {
         removeFirst();
	 cur = null;
      }
      else
      {
	numItems--;
        if ( cur == last )
	   cur = lookPrev(cur);
	cur.link = last.link;
      }
      last = null;
    }

    // Note that this method is not defined in the ListIterator<E> interface.
    // To use this method, you need to swap the heading of remove_Demo() with
    // @Override & the heading of remove() and call the remove() method.
    public void remove_Demo()   // covered in class
    {
      System.out.println("--------------------------------------------------------------------------------");
      System.out.println("Method to be demonstrated: remove() in SimpleLinkedListIterator");
      if ( last == null ) // no previous call
        throw new IllegalStateException();
      if ( cur == last )
      {
        showOneStep("if ( cur == last )", last, "last", index, "index");
        if ( index <= 0 ) 
          throw new RuntimeException("index 3");
        index--;
        showOneStep("index--", cur, "cur", index, "index");
      } // update index if cur is the last node

      if ( last == head )
      {
         showOneStep("if ( last == head )", last, "last", index, "index");
         removeFirst();
	 cur = null;
         showOneStep("removeFirst(); cur = null;", head, "head", index, "index");
      }
      else
      {
	numItems--;
        showOneStep("numItems--;", cur, "cur", index, "index");
        if ( cur == last )
	{
           showOneStep("if ( cur == last )", last, "last", index, "index");
	   cur = lookPrev(cur);
           showOneStep("cur = lookPrev(cur);", cur, "cur", index, "index");
	}
	cur.link = last.link;
        showOneStep("cur.link = last.link", cur, "cur", index, "index");
      }
      last = null;
      showOneStep("last = null;", cur, "cur", index, "index");
    }

    /**
     * The new element is inserted before the implicit cursor:
     * a subsequent call to next() would be unaffected, and
     * a subsequent call to previous() would return the new element.
     */
    @Override
    public void add(E obj)
    { if ( cur == null )
      {
         addFirst(obj);
         last = null;
         cur = head;
         index = 1;
         return;
      }
      ListNode toAdd = new ListNode();
      toAdd.data = obj;
      toAdd.link = cur.link;
      cur.link = toAdd;
      cur = toAdd;
      last = null;
      index++;
      numItems++;
    } // add

    // Note that this method is not defined in the ListIterator<E> interface.
    // To use this method, you need to swap the heading of add_Demo() with
    // @Override & the heading of add() and call the add() method.
    public void add_Demo(E obj)
    {
      System.out.println("--------------------------------------------------------------------------------");
      String st = obj == null? "NULL_Value" : obj.toString();
      System.out.println("Method to be demonstrated: add(" + st + ") in SimpleLinkedListIterator");
      if ( cur == null )
      {
         showOneStep("if ( cur == null )", cur, "cur", index, "index");
         addFirst(obj);
         showOneStep("addFirst(" + st + ")", cur, "cur", index, "index");
         last = null;
         cur = head;
         index = 1;
         showOneStep("last = null; cur = head; index = 1; return;", cur, "cur", index, "index");
         return;
      }
      ListNode toAdd = new ListNode();
      showOneStep("ListNode toAdd = new ListNode();", cur, "cur", index, "index");
      toAdd.data = obj;
      toAdd.link = cur.link;
      cur.link = toAdd;
      showOneStep("toAdd.data = obj; toAdd.link = cur.link; cur.link = toAdd;", cur, "cur", index, "index");
      cur = toAdd;
      last = null;
      index++;
      numItems++;
      showOneStep("cur = toAdd; last = null; index++; numItems++;", cur, "cur", index, "index");
    } // add

    /**
     * Replaces the last element returned by next() or previous()
     * with the specified element.
     */
    @Override
    public void set(E obj)
    {
      if ( last == null )
        throw new IllegalStateException();
      last.data = obj;
    } // set
  } // SimpleLinkedListIterator
  
  @Override
  public boolean equals(Object obj)
  {
    if ( (obj == null) || ! ( obj instanceof List<?> ) )
      return false;
    List<?> list = (List<?>) obj;
    if ( list.size() != numItems ) return false;
    Iterator<?> iter = list.iterator();
    for ( ListNode tmp = head; tmp != null; tmp = tmp.link )
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
    return new SimpleLinkedListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    return new SimpleLinkedListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int pos)
  {
    checkIndex2(pos);
    return new SimpleLinkedListIterator(pos);
  }

  private void showOneStep(String stmt, ListNode node, String nst, int index, String ist)
  {
    System.out.println("After statement: " + stmt);
    System.out.print("List contents:   ");
    ListNode cur;
    int ind;
    for ( cur = head, ind = 0; cur != null; cur = cur.link, ind++ )
    {
      if ( cur.data != null )
         System.out.print(cur.data + " ");
      else
         System.out.print("NULL_Value ");
      if ( node == cur )
         System.out.print("(" + nst + ") ");
      if ( index == ind )
         System.out.print("(" + ist + ") ");
      if ( cur.link != null )
         System.out.print("-> ");
      else
         System.out.print("null ");
    }
    System.out.println();
    System.out.println();
  }
  
  /**
   * Returns a string representation of this list showing
   * the cursor position of the iterator.
   * @param iter an iterator for this list
   */
  public String toString(ListIterator<E> iter)  
  {
    ListNode cur;
    int ind;
    int index;
    if ( iter != null )
         index = iter.nextIndex();
    else
         index = -1;
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for ( cur = head, ind = 0; cur != null; cur = cur.link, ind++ )
    {
      if ( index == ind )
         sb.append("| ");  
      if ( cur.data != null )
         sb.append(cur.data.toString());
      else
         sb.append("-");
      if ( cur.link != null )
         sb.append(", ");
    }
    if ( index == ind && ind == numItems )
         sb.append("|");  
    sb.append("]");
    return sb.toString();
  }
}
