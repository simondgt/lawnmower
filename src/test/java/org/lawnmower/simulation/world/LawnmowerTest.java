package org.lawnmower.simulation.world;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LawnmowerTest {

    @Test
    public void should_ThrowException_When_Instantiated_With_NegativesCoordinates() {
        //negative initX
        try {
            new Lawnmower(-1, 0, 'N', new Lawn(100, 100));
            fail("Constructor preconditions must throw an exception here.");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of X (-1) must be positive.", e.getMessage());
        }

        //negative initY
        try {
            new Lawnmower(0, -1, 'N', new Lawn(100, 100));
            fail("Constructor preconditions must throw an exception here.");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of Y (-1) must be positive.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_BadDirection() {
        //C is not a valid direction
        try {
            new Lawnmower(0, 0, 'C', new Lawn(100, 100));
            fail("Constructor preconditions must throw an exception here.");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of Direction (C) must be N, E W or S.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_NullSurfaceTest() {
        try {
            new Lawnmower(0, 0, 'W', null);
            fail("Constructor preconditions must throw an exception here.");
        } catch (IllegalArgumentException e) {
            assertEquals("Surface Tester can't be null.", e.getMessage());
        }
    }

    @Test
    public void should_InitializeMembers_When_Instantiated() {
        //we compare constructor parameters with getter results
        Lawnmower lm = new Lawnmower(5, 10, 'W', new Lawn(100, 100));
        assertEquals(5, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('W', lm.getDirection());
    }

    @Test
    public void should_ThrowException_When_BadCommand() {

        Lawnmower lm = new Lawnmower(5, 10, 'W', new Lawn(100, 100));
        try {
            //the only valid commands are G, D and A
            lm.executeCommand('F');
            fail("executeCommand must fail when parameter not in A, G or D");
        } catch (IllegalArgumentException e) {
            assertEquals("Bad command F", e.getMessage());
        }
    }

    @Test
    public void should_DecrementX_When_FacingWest_And_Advance() {
        Lawnmower lm = new Lawnmower(5, 10, 'W', new Lawn(100, 100));
        lm.executeCommand('A');

        //X is decremented. Other members are not updated.
        assertEquals(4, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('W', lm.getDirection());
    }

    @Test
    public void should_NotUpdateX_When_FacingWest_And_TouchingWestFence_And_Advance() {
        Lawnmower lm = new Lawnmower(0, 10, 'W', new Lawn(100, 100));
        lm.executeCommand('A');

        //nothing changed
        assertEquals(0, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('W', lm.getDirection());
    }

    @Test
    public void should_FaceSouth_When_FacingWest_And_TurnLeft() {
        Lawnmower lm = new Lawnmower(5, 10, 'W', new Lawn(100, 100));
        lm.executeCommand('G');

        //direction is updated
        assertEquals(5, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('S', lm.getDirection());
    }

    @Test
    public void should_FaceNorth_When_FacingWest_And_TurnRight() {
        Lawnmower lm = new Lawnmower(5, 10, 'W', new Lawn(100, 100));
        lm.executeCommand('D');

        //direction is updated
        assertEquals(5, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('N', lm.getDirection());
    }

    @Test
    public void should_FaceSouth_When_FacingWest_And_TouchingWestFence_And_TurnLeft() {
        Lawnmower lm = new Lawnmower(0, 10, 'W', new Lawn(100, 100));
        lm.executeCommand('G');

        //direction is updated
        assertEquals(0, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('S', lm.getDirection());
    }

    @Test
    public void should_FaceNorth_When_FacingWest_And_TouchingWestFence_And_TurnRight() {
        Lawnmower lm = new Lawnmower(0, 10, 'W', new Lawn(100, 100));
        lm.executeCommand('D');

        //direction is updated
        assertEquals(0, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('N', lm.getDirection());
    }

    @Test
    public void should_IncrementX_When_FacingEast_And_Advance() {
        Lawnmower lm = new Lawnmower(5, 10, 'E', new Lawn(100, 100));
        lm.executeCommand('A');

        //X is incremented. Other members are not updated.
        assertEquals(6, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('E', lm.getDirection());
    }

    @Test
    public void should_NotUpdateX_When_FacingEast_And_TouchingEastFence_And_Advance() {
        Lawnmower lm = new Lawnmower(100, 10, 'E', new Lawn(100, 100));
        lm.executeCommand('A');

        //nothing changed
        assertEquals(100, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('E', lm.getDirection());
    }

    @Test
    public void should_FaceSouth_When_FacingEast_And_TurnRight() {
        Lawnmower lm = new Lawnmower(5, 10, 'E', new Lawn(100, 100));
        lm.executeCommand('D');

        //direction is updated
        assertEquals(5, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('S', lm.getDirection());
    }

    @Test
    public void should_FaceNorth_When_FacingEast_And_TurnLeft() {
        Lawnmower lm = new Lawnmower(5, 10, 'E', new Lawn(100, 100));
        lm.executeCommand('G');

        //direction is updated
        assertEquals(5, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('N', lm.getDirection());
    }

    @Test
    public void should_FaceSouth_When_FacingEast_And_TouchingEastFence_And_TurnRight() {
        Lawnmower lm = new Lawnmower(100, 10, 'E', new Lawn(100, 100));
        lm.executeCommand('D');

        //direction is updated
        assertEquals(100, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('S', lm.getDirection());
    }

    @Test
    public void should_FaceNorth_When_FacingEast_And_TouchingEastFence_And_TurnLeft() {
        Lawnmower lm = new Lawnmower(100, 10, 'E', new Lawn(100, 100));
        lm.executeCommand('G');

        //direction is updated
        assertEquals(100, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('N', lm.getDirection());
    }

    @Test
    public void should_DecrementY_When_FacingSouth_And_Advance() {
        Lawnmower lm = new Lawnmower(5, 10, 'S', new Lawn(100, 100));
        lm.executeCommand('A');

        //Y is decremented. Other members are not updated.
        assertEquals(5, lm.getX());
        assertEquals(9, lm.getY());
        assertEquals('S', lm.getDirection());
    }

    @Test
    public void should_NotUpdateY_When_FacingSouth_And_TouchingSouthFence_And_Advance() {
        Lawnmower lm = new Lawnmower(100, 0, 'S', new Lawn(100, 100));
        lm.executeCommand('A');

        //nothing changed
        assertEquals(100, lm.getX());
        assertEquals(0, lm.getY());
        assertEquals('S', lm.getDirection());
    }

    @Test
    public void should_FaceWest_When_FacingSouth_And_TurnRight() {
        Lawnmower lm = new Lawnmower(5, 10, 'S', new Lawn(100, 100));
        lm.executeCommand('D');

        //direction is updated
        assertEquals(5, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('W', lm.getDirection());
    }

    @Test
    public void should_FaceEast_When_FacingSouth_And_TurnLeft() {
        Lawnmower lm = new Lawnmower(5, 10, 'S', new Lawn(100, 100));
        lm.executeCommand('G');

        //direction is updated
        assertEquals(5, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('E', lm.getDirection());
    }

    @Test
    public void should_FaceWest_When_FacingSouth_And_TouchingSouthFence_And_TurnRight() {
        Lawnmower lm = new Lawnmower(5, 0, 'S', new Lawn(100, 100));
        lm.executeCommand('D');

        //direction is updated
        assertEquals(5, lm.getX());
        assertEquals(0, lm.getY());
        assertEquals('W', lm.getDirection());
    }

    @Test
    public void should_FaceEast_When_FacingSouth_And_TouchingSouthFence_And_TurnLeft() {
        Lawnmower lm = new Lawnmower(5, 0, 'S', new Lawn(100, 100));
        lm.executeCommand('G');

        //direction is updated
        assertEquals(5, lm.getX());
        assertEquals(0, lm.getY());
        assertEquals('E', lm.getDirection());
    }

    @Test
    public void should_IncrementY_When_FacingNorth_And_Advance() {
        Lawnmower lm = new Lawnmower(5, 10, 'N', new Lawn(100, 100));
        lm.executeCommand('A');

        //Y is incremented. Other members are not updated.
        assertEquals(5, lm.getX());
        assertEquals(11, lm.getY());
        assertEquals('N', lm.getDirection());
    }

    @Test
    public void should_NotUpdateY_When_FacingNorth_And_TouchingNorthFence_And_Advance() {
        Lawnmower lm = new Lawnmower(5, 100, 'N', new Lawn(100, 100));
        lm.executeCommand('A');

        //nothing changed
        assertEquals(5, lm.getX());
        assertEquals(100, lm.getY());
        assertEquals('N', lm.getDirection());
    }

    @Test
    public void should_FaceEast_When_FacingNorth_And_TurnRight() {
        Lawnmower lm = new Lawnmower(5, 10, 'N', new Lawn(100, 100));
        lm.executeCommand('D');

        //direction is updated
        assertEquals(5, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('E', lm.getDirection());
    }

    @Test
    public void should_FaceWest_When_FacingNorth_And_TurnLeft() {
        Lawnmower lm = new Lawnmower(5, 10, 'N', new Lawn(100, 100));
        lm.executeCommand('G');

        //direction is updated
        assertEquals(5, lm.getX());
        assertEquals(10, lm.getY());
        assertEquals('W', lm.getDirection());
    }

    @Test
    public void should_FaceEast_When_FacingNorth_And_TouchingNorthFence_And_TurnRight() {
        Lawnmower lm = new Lawnmower(5, 100, 'N', new Lawn(100, 100));
        lm.executeCommand('D');

        //direction is updated
        assertEquals(5, lm.getX());
        assertEquals(100, lm.getY());
        assertEquals('E', lm.getDirection());
    }

    @Test
    public void should_FaceWest_When_FacingNorth_And_TouchingNorthFence_And_TurnLeft() {
        Lawnmower lm = new Lawnmower(5, 100, 'N', new Lawn(100, 100));
        lm.executeCommand('G');

        //direction is updated
        assertEquals(5, lm.getX());
        assertEquals(100, lm.getY());
        assertEquals('W', lm.getDirection());
    }
}
