import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;    
    
public class GradeBookTest {

    GradeBook book, book2;

    @Before
    public void setup(){
        book = new GradeBook(5);
        book2 = new GradeBook(5);
        
        book.addScore(50);
        book.addScore(75);
        
        book2.addScore(90);
        book2.addScore(100);
    }

    @After
    public void tearDown(){
        book = null;
        book2 = null;
    }
        
    @Test
    public void testSum(){
        assertEquals(125,book.sum(),0.0001);
        assertEquals(190,book2.sum(),0.0001);
    }

    @Test
    public void testMinimum(){
        assertEquals(50,book.minimum(),0.001);
        assertEquals(90,book2.minimum(),0.001);
    }

    @Test
    public void testFinalScore(){
        assertEquals(75,book.finalScore(),0.001);
        assertEquals(100,book2.finalScore(),0.001);
    }

    @Test
    public void testToString(){
        assertTrue("50.0 75.0 ".equals(book.toString()));
        assertTrue("90.0 100.0 ".equals(book2.toString()));
    }
}
