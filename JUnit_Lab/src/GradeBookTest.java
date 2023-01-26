import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;    
    
public class GradeBookTest {

    GradeBook book;

    @Before
    public void setup(){
        book = new GradeBook(5);
        book.addScore(50);
        book.addScore(75);
    }

    @After
    public void tearDown(){
        book = null;
    }
        
    @Test
    public void testSum(){
        assertEquals(125,book.sum(),0.0001);
    }

    @Test
    public void testMinimum(){
        assertEquals(50,book.minimum(),0.001);
    }

    @Test
    public void testFinalScore(){
        assertEquals(75,book.finalScore(),0.001);
    }

    @Test
    public void testToString(){
        assertTrue("50.0 75.0 0.0 0.0 0.0 ".equals(book.toString()));
    }
}
    