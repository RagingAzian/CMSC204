import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {
    Town town1 = new Town("Town 1");
    Town town2 = new Town("Town 2");
    Town town3 = new Town("Town 3");
    Town town4 = new Town("Town 4");

    Road road1 = new Road(town1, town2, 3, "Road 1");
    Road road2 = new Road(town3,town4, 2, "Road 2");

    @Test
    public void testContains(){
        assertTrue(road1.contains(town1));
        assertTrue(!road1.contains(town3));
    }

    @Test 
    public void testEquals(){
        assertTrue(!road1.equals(road2));
    }
}
