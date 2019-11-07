package org.lawnmower;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StoryTest {

    @Test
    public void testConstructorPreconditions(){

        try {

            new Story(null, new ArrayList<>());
            fail("Surface Tester is null.");

        }catch(IllegalArgumentException e){

            assertEquals("SurfaceTester is null.", e.getMessage());

        }

        try {

            new Story((x,y) -> true, null);
            fail("Programs is null.");

        }catch(IllegalArgumentException e){

            assertEquals("Programs is null.", e.getMessage());

        }

        try {

            BiPredicate<Long, Long> tester = (x,y) -> true;
            new Story(tester, Arrays.asList(new Program(1,1,'E', "A", tester), null));
            fail("At least on program is null.");

        }catch(IllegalArgumentException e){

            assertEquals("At least on program is null.", e.getMessage());

        }

    }

    @Test
    public void testStory1(){

        //Lawn : unbounded
        BiPredicate<Long, Long> tester = (x,y) -> true;
        Program p1 = new Program(1,1,'E', "A", tester);
        Story story = new Story(tester, Arrays.asList(p1));
        String out = story.run();
        assertEquals("2 1 E\n", out);

    }

    @Test
    public void testStory2(){

        //Lawn : unbounded
        BiPredicate<Long, Long> tester = (x,y) -> true;
        Program p1 = new Program(1,1,'E', "AAADAGGA", tester);
        Story story = new Story(tester, Arrays.asList(p1));
        String out = story.run();
        assertEquals("4 1 N\n", out);

    }

    @Test
    public void testStory3(){

        //Lawn : 3x2
        BiPredicate<Long, Long> tester = (x,y) -> 0 <= x && x <= 3 && 0 <= y && y <= 2;
        Program p1 = new Program(1,1,'E', "AAADAGGA", tester);
        Story story = new Story(tester, Arrays.asList(p1));
        String out = story.run();
        assertEquals("3 1 N\n", out);

    }

    @Test
    public void testStory4(){

        //Lawn : 3x2
        BiPredicate<Long, Long> tester = (x,y) -> 0 <= x && x <= 3 && 0 <= y && y <= 2;
        Program p1 = new Program(1,1,'E', "AAADAGGA", tester);
        Program p2 = new Program(0,0,'N', "GAAAAADA", tester);
        Story story = new Story(tester, Arrays.asList(p1, p2));
        String out = story.run();
        assertEquals("3 1 N\n0 1 N\n", out);

    }


}
