import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {
    Town town1,town2;
    @Before
    public void setUp(){
        town1 = new Town("Town 1");
        town2 = new Town("Town 2");
    }

    @Test
    public void testEquals(){
        assertTrue(!town1.equals(town2));
    }
}
