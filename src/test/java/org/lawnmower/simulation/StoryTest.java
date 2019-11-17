package org.lawnmower.simulation;

import org.junit.Test;
import org.lawnmower.simulation.Story;
import org.lawnmower.simulation.program.Program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StoryTest {

    @Test
    public void should_ThrowException_When_Instantiated_With_NullLawnSurface() {
        try {
            new Story(null, new ArrayList<>());
            fail("Surface Tester is null.");
        } catch (IllegalArgumentException e) {
            assertEquals("SurfaceTester is null.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_NullProgram() {
        try {
            new Story((x, y) -> true, null);
            fail("Programs is null.");
        } catch (IllegalArgumentException e) {
            assertEquals("Programs is null.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_AtLeastOneNullProgram() {
        try {
            BiPredicate<Long, Long> tester = (x, y) -> true;
            new Story(tester, Arrays.asList(new Program(1, 1, 'E', "A", tester), null));
            fail("At least on program is null.");
        } catch (IllegalArgumentException e) {
            assertEquals("At least on program is null.", e.getMessage());
        }
    }

    @Test
    public void should_OutputSimulation_When_Instantiated_With_OneInstructionProgram_And_Run() {
        //Lawn : unbounded
        BiPredicate<Long, Long> tester = (x, y) -> true;
        Program p1 = new Program(1, 1, 'E', "A", tester);
        Story story = new Story(tester, Arrays.asList(p1));
        String out = story.run();
        assertEquals("2 1 E", out);
    }

    @Test
    public void should_OutputSimulation_When_Instantiated_With_ManyInstructionsProgram_UnboundedLawn_And_Run() {
        //Lawn : unbounded
        BiPredicate<Long, Long> tester = (x, y) -> true;
        Program p1 = new Program(1, 1, 'E', "AAADAGGA", tester);
        Story story = new Story(tester, Arrays.asList(p1));
        String out = story.run();
        assertEquals("4 1 N", out);
    }

    @Test
    public void should_OutputDifferentSimulation_When_Instantiated_With_ManyInstructionsProgram_SmallerLawn_And_Run() {
        BiPredicate<Long, Long> tester = (x, y) -> 0 <= x && x <= 3 && 0 <= y && y <= 2;
        Program p1 = new Program(1, 1, 'E', "AAADAGGA", tester);
        Story story = new Story(tester, Arrays.asList(p1));
        String out = story.run();
        assertEquals("3 1 N", out);
    }

    @Test
    public void should_OutputMultiLine_When_Instantiated_With_Several_Programs() {
        BiPredicate<Long, Long> tester = (x, y) -> 0 <= x && x <= 3 && 0 <= y && y <= 2;
        Program p1 = new Program(1, 1, 'E', "AAADAGGA", tester);
        Program p2 = new Program(0, 0, 'N', "GAAAAADA", tester);
        Story story = new Story(tester, Arrays.asList(p1, p2));
        String out = story.run();
        assertEquals("3 1 N\n0 1 N", out);
    }
}
