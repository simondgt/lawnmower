package org.lawnmower.simulation.program;

import org.junit.Test;
import org.lawnmower.simulation.world.Lawn;

import static org.junit.Assert.*;

public class LawnProgramTest {

    @Test
    public void should_ThrowException_When_Instantiated_With_NegativesCoordinates() {
        try {
            new LawnProgram(-1, 0, 'N', "", new Lawn(100, 100));
            fail("InitX < 0");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of X (-1) must be positive.", e.getMessage());
        }

        try {
            new LawnProgram(0, -2, 'N', "", new Lawn(100, 100));
            fail("InitY < 0");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of Y (-2) must be positive.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_BadDirection() {
        try {
            new LawnProgram(1, 0, 'Z', "", new Lawn(100, 100));
            fail("Init Direction != N, W, E or S");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of Direction (Z) must be N, E, W or S.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_NullInstructions() {
        try {
            new LawnProgram(1, 0, 'E', null, new Lawn(100, 100));
            fail("Instructions are null");
        } catch (IllegalArgumentException e) {
            assertEquals("Instructions can't be null.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_BadInstructionsList() {
        //E is not a valid instruction
        try {
            new LawnProgram(1, 0, 'E', "AGDE", new Lawn(100, 100));
            fail("Instructions contain bad letters");
        } catch (IllegalArgumentException e) {
            assertEquals("Instructions can only contain A,D or G letters.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_NullSurfaceTester() {
        try {
            new LawnProgram(1, 0, 'E', "AGD", null);
            fail("Surface Tester is null");
        } catch (IllegalArgumentException e) {
            assertEquals("SurfaceTester is null.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_InitCoordinatesOutOufBounds() {
        try {
            new LawnProgram(1, 6, 'E', "AGDGDGDGDAAA", new Lawn(10, 5));
            fail("Coordinates (1,6) don't belong to surface.");
        } catch (IllegalArgumentException e) {
            assertEquals("Coordinates (1,6) don't belong to surface.", e.getMessage());
        }
    }

    @Test
    public void should_InitializeMembers_When_Instantiated() {
        //we compare constructor parameters with getter results
        LawnProgram p = new LawnProgram(1, 6, 'E', "ADDG", new Lawn(100, 100));
        assertEquals('E', p.getInitDirection());
        assertEquals(1, p.getInitX());
        assertEquals(6, p.getInitY());
        assertEquals("ADDG", p.getInstructions());
    }
}
