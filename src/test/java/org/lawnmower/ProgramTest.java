package org.lawnmower;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProgramTest {

    @Test
    public void should_ThrowException_When_Instantiated_With_NegativesCoordinates() {
        try {
            new Program(-1, 0, 'N', "", (x, y) -> true);
            fail("InitX < 0");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of X (-1) must be positive.", e.getMessage());
        }

        try {
            new Program(0, -2, 'N', "", (x, y) -> true);
            fail("InitY < 0");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of Y (-2) must be positive.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_BadDirection() {
        try {
            new Program(1, 0, 'Z', "", (x, y) -> true);
            fail("Init Direction != N, W, E or S");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of Direction (Z) must be N, E, W or S.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_NullInstructions() {
        try {
            new Program(1, 0, 'E', null, (x, y) -> true);
            fail("Instructions are null");
        } catch (IllegalArgumentException e) {
            assertEquals("Instructions can't be null.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_BadInstructionsList() {
        //E is not a valid instruction
        try {
            new Program(1, 0, 'E', "AGDE", (x, y) -> true);
            fail("Instructions contain bad letters");
        } catch (IllegalArgumentException e) {
            assertEquals("Instructions can only contain A,D or G letters.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_NullSurfaceTester() {
        try {
            new Program(1, 0, 'E', "AGD", null);
            fail("Surface Tester is null");
        } catch (IllegalArgumentException e) {
            assertEquals("SurfaceTester is null.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_InitCoordinatesOutOufBounds() {
        try {
            new Program(1, 6, 'E', "AGDGDGDGDAAA", (x, y) -> x <= 10 && y <= 5);
            fail("Coordinates (1,6) don't belong to surface.");
        } catch (IllegalArgumentException e) {
            assertEquals("Coordinates (1,6) don't belong to surface.", e.getMessage());
        }
    }

    @Test
    public void should_InitializeMembers_When_Instantiated() {
        //we compare constructor parameters with getter results
        Program p = new Program(1, 6, 'E', "ADDG", (x, y) -> true);
        assertEquals('E', p.getInitDirection());
        assertEquals(1, p.getInitX());
        assertEquals(6, p.getInitY());
        assertTrue(p.hasNext());
        assertEquals('A', (char) p.next());
        assertTrue(p.hasNext());
        assertEquals('D', (char) p.next());
        assertTrue(p.hasNext());
        assertEquals('D', (char) p.next());
        assertTrue(p.hasNext());
        assertEquals('G', (char) p.next());
        assertFalse(p.hasNext());
    }
}
