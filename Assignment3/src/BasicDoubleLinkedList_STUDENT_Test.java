import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<Integer> linkedInteger;
	IntegerComparator comparator;
    public ArrayList<Integer> fill = new ArrayList<Integer>();

    @Before
	public void setUp() throws Exception {
		
		linkedInteger = new BasicDoubleLinkedList<Integer>();
		linkedInteger.addToEnd(15);
		linkedInteger.addToEnd(100);
		comparator = new IntegerComparator();
	}

    @After
	public void tearDown() throws Exception {
        linkedInteger = null;
		comparator = null;
	}
    
    @Test
	public void testGetSize() {
		assertEquals(2,linkedInteger.getSize());
	}

    @Test
	public void testAddToEnd() {
		assertEquals(100, linkedInteger.getLast(), 1);
        linkedInteger.addToEnd(50);
        assertEquals(50, linkedInteger.getLast(), 1);
	}
    
    @Test
	public void testAddToFront() {
		assertEquals(15, linkedInteger.getFirst(), 1);
		linkedInteger.addToFront(75);
		assertEquals(75, linkedInteger.getFirst(), 1);
	}

    @Test
	public void testGetFirst() {
		assertEquals(15, linkedInteger.getFirst(), 1);
		linkedInteger.addToFront(4);
		assertEquals(4, linkedInteger.getFirst(),1);
	}

    @Test
	public void testGetLast() {
		assertEquals(100, linkedInteger.getLast(),1);
		linkedInteger.addToEnd(5);
		assertEquals(5, linkedInteger.getLast(),1);
	}

    @Test
	public void testToArrayList()
	{
		ArrayList<Integer> list;
		linkedInteger.addToFront(3);
		linkedInteger.addToEnd(4);
		list = linkedInteger.toArrayList();
		assertEquals(3,list.get(0),1);
		assertEquals(15,list.get(1),1);
        assertEquals(100,list.get(2),1);
        assertEquals(4, list.get(3), 1);
	}

    @Test
	public void testIteratorSuccessfulNext() {
		linkedInteger.addToFront(1);
		linkedInteger.addToEnd(200);
		ListIterator<Integer> iterator = linkedInteger.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(1, iterator.next(), 1);
		assertEquals(15, iterator.next(),1);
		assertEquals(100, iterator.next(),1);
		assertEquals(true, iterator.hasNext());
		assertEquals(200, iterator.next(),1);
	}

@Test
	public void testIteratorSuccessfulPrevious() {
		linkedInteger.addToFront(1);
		linkedInteger.addToEnd(200);
		ListIterator<Integer> iteratorInt = linkedInteger.iterator();
		assertEquals(true, iteratorInt.hasNext());
		assertEquals(1, iteratorInt.next(),1);
        assertEquals(15, iteratorInt.next(), 1);
		assertEquals(100, iteratorInt.next(),1);
		assertEquals(200, iteratorInt.next(),1);
		assertEquals(true, iteratorInt.hasPrevious());
		assertEquals(200, iteratorInt.previous(),1);
		assertEquals(100, iteratorInt.previous(),1);
		assertEquals(15, iteratorInt.previous(),1);
		assertEquals(1, iteratorInt.previous(),1);
	}
    
    @Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedInteger.addToFront(1);
		linkedInteger.addToEnd(200);
		ListIterator<Integer> iteratorInt = linkedInteger.iterator();		
		assertEquals(true, iteratorInt.hasNext());
		assertEquals(1, iteratorInt.next(),1);
		assertEquals(15, iteratorInt.next(),1);
		assertEquals(100, iteratorInt.next(),1);
		assertEquals(true, iteratorInt.hasNext());
		assertEquals(200, iteratorInt.next(),1);
		
		try{
			//no more elements in list
			iteratorInt.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
    @Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedInteger.addToFront(1);
		linkedInteger.addToEnd(200);
		ListIterator<Integer> iteratorInt = linkedInteger.iterator();		
		assertEquals(true, iteratorInt.hasNext());
		assertEquals(1, iteratorInt.next(),1);
		assertEquals(15, iteratorInt.next(),1);
		assertEquals(100, iteratorInt.next(),1);
		assertEquals(200, iteratorInt.next(),1);
		assertEquals(true, iteratorInt.hasPrevious());
		assertEquals(200, iteratorInt.previous(),1);
		assertEquals(100, iteratorInt.previous(),1);
		assertEquals(15, iteratorInt.previous(),1);
		assertEquals(1, iteratorInt.previous(),1);
		
		try{
			//no more elements in list
			iteratorInt.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedInteger.addToFront(1);
		linkedInteger.addToEnd(200);
		ListIterator<Integer> iteratorInt = linkedInteger.iterator();		
		assertEquals(true, iteratorInt.hasNext());
		assertEquals(1, iteratorInt.next(),1);
		assertEquals(15, iteratorInt.next(),1);
		assertEquals(100, iteratorInt.next(),1);
		assertEquals(true, iteratorInt.hasNext());
		assertEquals(200, iteratorInt.next(),1);
		
		try{
			//remove is not supported for the iterator
			iteratorInt.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}
	
	@Test
	public void testRemove() {
		// remove the first
		assertEquals(15, linkedInteger.getFirst(),1);
		assertEquals(100, linkedInteger.getLast(),1);
		linkedInteger.addToFront(1);
		assertEquals(1, linkedInteger.getFirst(),1);
		linkedInteger.remove(1, comparator);
		assertEquals(15, linkedInteger.getFirst(), 1);
		//remove from the end of the list
		linkedInteger.addToEnd(200);
		assertEquals(200, linkedInteger.getLast(),1);
		linkedInteger.remove(200, comparator);
		assertEquals(100, linkedInteger.getLast(),1);
		//remove from middle of list
		linkedInteger.addToFront(1);
		assertEquals(1, linkedInteger.getFirst(),1);
		assertEquals(100, linkedInteger.getLast(),1);
		linkedInteger.remove(15, comparator);
		assertEquals(1, linkedInteger.getFirst(),1);
		assertEquals(100, linkedInteger.getLast(),1);
		
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(15, linkedInteger.getFirst(),1);
		linkedInteger.addToFront(1);
		assertEquals(1, linkedInteger.getFirst(),1);
		assertEquals(1, linkedInteger.retrieveFirstElement(),1);
		assertEquals(15,linkedInteger.getFirst(),1);
		assertEquals(15, linkedInteger.retrieveFirstElement(),1);
		assertEquals(100,linkedInteger.getFirst(),1);
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(100, linkedInteger.getLast(),1);
		linkedInteger.addToEnd(200);
		assertEquals(200, linkedInteger.getLast(),1);
		assertEquals(200, linkedInteger.retrieveLastElement(),1);
		assertEquals(100,linkedInteger.getLast(),1);
	}

    private class IntegerComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer arg0, Integer arg1){
            return arg0.compareTo(arg1);
        }
    }
}

