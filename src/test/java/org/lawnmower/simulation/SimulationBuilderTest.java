package org.lawnmower.simulation;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimulationBuilderTest {


    @Test
    public void should_ThrowException_When_InvalidLawnWidth() {
        try {
            // Bad lawn surface
            List<String> input = new ArrayList<>();
            input.add("-1 5");
            input.add("0 0 N");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("Width  (-1) must be positive.");
        } catch (IllegalArgumentException e) {
            assertEquals("Width  (-1) must be positive.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_InvalidLawnHeight() {
        try {
            // Bad lawn surface
            List<String> input = new ArrayList<>();
            input.add("1 -5");
            input.add("0 0 N");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("Height (-5) must be positive.");
        } catch (IllegalArgumentException e) {
            assertEquals("Height (-5) must be positive.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_MissingLawnHeight() {
        try {
            // Bad lawn surface
            List<String> input = new ArrayList<>();
            input.add("1");
            input.add("0 0 N");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("ParseError : Input doesn't respect the specification : 1");
        } catch (IllegalArgumentException e) {
            assertEquals("ParseError : Input doesn't respect the specification : 1", e.getMessage());
        }
    }


    @Test
    public void should_ThrowException_When_LawnHeightIsNotLong() {
        try {
            // Bad lawn surface
            List<String> input = new ArrayList<>();
            input.add("1 a");
            input.add("0 0 N");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("ParseError : Input doesn't respect the specification : 1 a");
        } catch (IllegalArgumentException e) {
            assertEquals("ParseError : Input doesn't respect the specification : 1 a", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_LawnHeightIsTooBig() {
        try {
            // Bad lawn surface
            List<String> input = new ArrayList<>();
            input.add("1 14546541351251651813518313515813881");
            input.add("0 0 N");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("ParseError : Input doesn't respect the specification : 1 a");
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "ParseError : Input doesn't respect the specification : 1 14546541351251651813518313515813881",
                    e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_LawnWidthIsNotLong() {
        try {
            // Bad lawn surface
            List<String> input = new ArrayList<>();
            input.add("1c 1");
            input.add("0 0 N");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("ParseError : Input doesn't respect the specification : 1c 1");
        } catch (IllegalArgumentException e) {
            assertEquals("ParseError : Input doesn't respect the specification : 1c 1", e.getMessage());
        }
    }


    @Test
    public void should_ThrowException_When_LawnLineIsEmpty() {
        try {
            // Bad lawn surface
            List<String> input = new ArrayList<>();
            input.add("");
            input.add("0 0 N");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("ParseError : Input doesn't respect the specification : ");
        } catch (IllegalArgumentException e) {
            assertEquals("ParseError : Input doesn't respect the specification : ", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_InitX_OutsideOfLawn() {
        try {
            // Bad init position
            List<String> input = new ArrayList<>();
            input.add("5 5");
            input.add("6 0 N");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("Coordinates (6,0) don't belong to surface.");
        } catch (IllegalArgumentException e) {
            assertEquals("Coordinates (6,0) don't belong to surface.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_InitY_OutsideOfLawn() {
        try {
            // Bad init position
            List<String> input = new ArrayList<>();
            input.add("5 5");
            input.add("3 6 N");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("Coordinates (3,6) don't belong to surface.");
        } catch (IllegalArgumentException e) {
            assertEquals("Coordinates (3,6) don't belong to surface.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_BadInitDirection() {
        try {
            // Bad init direction
            List<String> input = new ArrayList<>();
            input.add(" 5   10 ");
            input.add("3 6 Z");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("ParseError bad direction: Input doesn't respect the specification : 3 6 Z");
        } catch (IllegalArgumentException e) {
            assertEquals("ParseError bad direction: Input doesn't respect the specification : 3 6 Z",
                    e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_InitX_LongOverFlow() {
        try {
            // Bad init direction
            List<String> input = new ArrayList<>();
            input.add(" 5   10 ");
            input.add("3454654654654546456465465211 6 Z");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("ParseError bad x : Input doesn't respect the specification : 3454654654654546456465465211 6 Z");
        } catch (IllegalArgumentException e) {
            assertEquals("ParseError bad x : Input doesn't respect the specification : 3454654654654546456465465211 6 Z",
                    e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_InitY_NotLong() {
        try {
            // Bad init direction
            List<String> input = new ArrayList<>();
            input.add(" 5   10 ");
            input.add("1 a Z");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("ParseError bad y : Input doesn't respect the specification : 1 a Z");
        } catch (IllegalArgumentException e) {
            assertEquals("ParseError bad y : Input doesn't respect the specification : 1 a Z",
                    e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_InitDirection_Missing() {
        try {
            // Bad init direction
            List<String> input = new ArrayList<>();
            input.add(" 5   10 ");
            input.add("1 1");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("ParseError bad direction : Input doesn't respect the specification : 1 1");
        } catch (IllegalArgumentException e) {
            assertEquals("ParseError bad direction : Input doesn't respect the specification : 1 1",
                    e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_InitDirection_Bad() {
        try {
            // Bad init direction
            List<String> input = new ArrayList<>();
            input.add(" 5   10 ");
            input.add("1 1 Z");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("ParseError bad direction : Input doesn't respect the specification : 1 1 Z");
        } catch (IllegalArgumentException e) {
            assertEquals("ParseError bad direction: Input doesn't respect the specification : 1 1 Z",
                    e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_EmptyInitLine() {
        try {
            //empty line
            List<String> input = new ArrayList<>();
            input.add(" 5   10 ");
            input.add("");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("ParseError bad x : Input doesn't respect the specification : ");
        } catch (IllegalArgumentException e) {
            assertEquals("ParseError bad x : Input doesn't respect the specification : ",
                    e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_MissingInitY() {
        try {
            // Bad init direction
            List<String> input = new ArrayList<>();
            input.add(" 5   10 ");
            input.add("1");
            input.add("AGGDGGA");
            SimulationBuilder.newSimulation(input.iterator());
            fail("ParseError bad y : Input doesn't respect the specification : 1");
        } catch (IllegalArgumentException e) {
            assertEquals("ParseError bad y : Input doesn't respect the specification : 1",
                    e.getMessage());
        }
    }

    @Test
    public void should_DoNothing_When_NoInstructions() {
        // no instructions
        List<String> input = new ArrayList<>();
        input.add(" 5   10 ");
        input.add("1 1 E");
        Simulation simulation = SimulationBuilder.newSimulation(input.iterator());
        String out = simulation.run();
        assertEquals("", out);
    }

    @Test
    public void should_BuildSimulation_When_ValidInput() {
        try {
            // Everything is fine
            List<String> input = new ArrayList<>();
            input.add("5 5");
            input.add("1 1 N");
            input.add("AGGDGGA");
            Simulation simulation = SimulationBuilder.newSimulation(input.iterator());
            assertNotNull(simulation);
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void should_RunSimulation_When_ValidInput() {
        List<String> input = new ArrayList<>();
        input.add(" 5   10 ");
        input.add("1 1 N");
        input.add("AAAA");
        Simulation simulation = SimulationBuilder.newSimulation(input.iterator());
        String out = simulation.run();
        assertEquals("1 5 N", out);
    }

    @Test
    public void should_ThrowException_When_FileNotFound() {
        try {
            SimulationBuilder.fromFile(new File("test/bad"));
            fail("file doesn't exits");
        } catch (FileNotFoundException e) {
            assertEquals("test/bad (No such file or directory)", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void should_BuildSimulation_When_FileExists() {
        try {
            Simulation simulation = SimulationBuilder.fromFile(new File("test/input1"));
            assertNotNull(simulation);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }



}
