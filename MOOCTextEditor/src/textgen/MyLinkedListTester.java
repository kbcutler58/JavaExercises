/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> longerList1;
	//MyLinkedList<Integer> lst;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
		
	}

	public String printListForwards(MyLinkedList<Integer> lst)
	{
		LLNode<Integer> curr;
                String ret = "";
		if (lst.head.data == null)
			curr = lst.head.next;
		else
			curr = lst.head;
		
		while (curr != null && curr.data != null)
		{
			ret += curr.data;
			curr = curr.next;
		}
                return ret;
	}
	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		// test out of bounds
		try {
			longerList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {			
		}
		
		// test for removal of last index + 1
		try {
			longerList.remove(longerList.size());
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {			
		}
		try {
			emptyList.remove(0);
			fail("Remove nonexisting data");
		}
		catch (IndexOutOfBoundsException e) {
		}	
		
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		int b = list1.remove(1);
		assertEquals("Remove: check b is correct ", 42, b);
		assertEquals("Remove: check new element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check new size correct ", 1, list1.size());
		
		for (int i = 0; i < LONG_LIST_LENGTH; i++ )
		{
			assertEquals("Removal of Numbers", (Integer)i, longerList.remove(0));
			if (longerList.size()<=0)
			{}
			else {
			assertEquals("Removal of Numbers Post", (Integer)(i+1) , (longerList.get(0)));
			assertEquals("Remove: Size of List", (9-(Integer)(i)) , (longerList.size() ) );
			}
		}
		assertEquals("Remove: list is now empty", 0, longerList.size());

	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		
		try {
			longerList.add(null);
			fail("Null Input");
		}
		catch (NullPointerException e)
		{}
		// test for removal of last index + 1
		emptyList.add(1);

		MyLinkedList<Integer> lst = new MyLinkedList<Integer>();
		
		int nums[] = {1, 2, 3, 4, 5};
		for (int i : nums)
		{
			lst.add(i);
			assertEquals("Add End: Correct new size", i, lst.size());
			assertEquals("Add End: Correctly place number", (Integer)i, lst.get(i-1));
		}
		
		
		longerList.add(11);
		int testVar = longerList.get(longerList.size()-1);
		assertEquals("Add End: last is incorrect", 11, testVar);
		
        // TODO: implement this test
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		int tempSize = longerList.size();
		assertEquals("Test Size of LongerList", 10, tempSize);
		longerList.add(11);
		assertEquals("Test Size of LongerList", 11, tempSize+1);
		//System.out.println(longerList.size());
		int tempSize2 = emptyList.size();
		assertEquals("Test Size of Empty List", 0, tempSize2);
		assertEquals("Short List Size", 2, shortList.size());
		assertEquals("List 1 Size", 3, list1.size());
		// Add change size testing after remove methods
		// TODO: implement this test
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			longerList.add(0, null);
			fail("Null Input");
		}
		catch (NullPointerException e)
		{}
		try {
			longerList.add(-10,1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {			
		}
		try {
			longerList.add(-1,1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {			
		}
		
		try {
			longerList.add(LONG_LIST_LENGTH+1,1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {			
		}
		shortList.add(0,"C");
		assertEquals("Changed Value of C", "C", shortList.get(0));
		shortList.add(1,"D");
		assertEquals("Changed Value of D", "D", shortList.get(1));
		try {
			System.out.println(shortList.size());
			shortList.add(5,"F");
			fail("set outside bounds");
		}
		catch(IndexOutOfBoundsException e) {
		}
		//shortList.add(3,"F");
        // TODO: implement this test
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		// Test for index < 0 and index > size
		try {
			longerList.set(-10,1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {			
		}
		
		try {
			longerList.set(50,1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {			
		}
		try {
			emptyList.set(0, 1);
			fail("Set nonexisting data");
		}
		catch (IndexOutOfBoundsException e) {
		}	
		
		try {
			longerList.set(0, null);
			fail("Null Input");
		}
		catch (NullPointerException e)
		{}
		
		String tempString = shortList.set(0,"C");
		assertEquals("Changed element 0 from A to C", "A", tempString );
		assertEquals("Changed Value of C", "C", shortList.get(0));
		String tempString2 = shortList.set(1,"D");
		assertEquals("Changed element 1 from B to D", "B", tempString2 );
		assertEquals("Changed Value of D", "D", shortList.get(1));
		
	    // TODO: implement this test
	    
	}
	
	public void moreTests()
	{
/*
		MyLinkedList<Integer> lst = new MyLinkedList<Integer>();
		int nums[] = {1, 2, 3, 4, 5};
		
		for (int i : nums)
		{
		
		LLNode<Integer> curr;
        String ret = "";
        if (lst.head.data == null)
        	curr = lst.head.next;
        else
        	curr = lst.head;

        while (curr != null && curr.data != null)
        {
        	ret += curr.data;
        	curr = curr.next;
        }
        System.out.print(curr);
		}
        
		
		
	*/	
	}
	// TODO: Optionally add more test methods.
	
}
