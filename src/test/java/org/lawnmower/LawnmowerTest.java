package org.lawnmower;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LawnmowerTest {

    @Test
    public void lawnmowerApiTest() {
        // First of all, we check if the constructor can catch bad cases
        try {
            new Lawnmower(-1, 0, 'N', (x, y) -> true);
            fail("Constructor preconditions must throw an exception here.");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of X (-1) must be positive.", e.getMessage());
        }

        try {
            new Lawnmower(0, -1, 'N', (x, y) -> true);
            fail("Constructor preconditions must throw an exception here.");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of Y (-1) must be positive.", e.getMessage());
        }

        try {
            new Lawnmower(0, 0, 'C', (x, y) -> true);
            fail("Constructor preconditions must throw an exception here.");
        } catch (IllegalArgumentException e) {
            assertEquals("Value of Direction (C) must be N, E W or S.", e.getMessage());
        }

        try {
            new Lawnmower(0, 0, 'W', null);
            fail("Constructor preconditions must throw an exception here.");
        } catch (IllegalArgumentException e) {
            assertEquals("Surface Tester can't be null.", e.getMessage());
        }

        {
            // Then, we check if the constructor initialize the lawnmower properly
            Lawnmower lm = new Lawnmower(5, 10, 'W', (x, y) -> true);
            assertEquals(5, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('W', lm.getDirection());

            // Finally, we play with the API in order to check if the lawnmower can execute commands properly
            lm.advance();
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('W', lm.getDirection());

            lm.turnLeft();
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('S', lm.getDirection());

            lm.advance();
            assertEquals(4, lm.getX());
            assertEquals(9, lm.getY());
            assertEquals('S', lm.getDirection());

            lm.turnLeft();
            assertEquals(4, lm.getX());
            assertEquals(9, lm.getY());
            assertEquals('E', lm.getDirection());

            lm.advance();
            assertEquals(5, lm.getX());
            assertEquals(9, lm.getY());
            assertEquals('E', lm.getDirection());

            lm.turnLeft();
            assertEquals(5, lm.getX());
            assertEquals(9, lm.getY());
            assertEquals('N', lm.getDirection());

            lm.advance();
            assertEquals(5, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('N', lm.getDirection());

            lm.turnLeft();
            assertEquals(5, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('W', lm.getDirection());

            lm.advance();
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('W', lm.getDirection());

            lm.turnRight();
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('N', lm.getDirection());

            lm.turnRight();
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('E', lm.getDirection());

            lm.turnRight();
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('S', lm.getDirection());

            lm.turnRight();
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('W', lm.getDirection());

        }

        // We apply the same commands than before, but with executeCommand function
        {
            Lawnmower lm = new Lawnmower(5, 10, 'W', (x, y) -> true);
            assertEquals(5, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('W', lm.getDirection());

            // Finally, we play with the API in order to check if the lawnmower can execute commands properly
            lm.executeCommand('A');
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('W', lm.getDirection());

            lm.executeCommand('G');
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('S', lm.getDirection());

            lm.executeCommand('A');
            assertEquals(4, lm.getX());
            assertEquals(9, lm.getY());
            assertEquals('S', lm.getDirection());

            lm.executeCommand('G');
            assertEquals(4, lm.getX());
            assertEquals(9, lm.getY());
            assertEquals('E', lm.getDirection());

            lm.executeCommand('A');
            assertEquals(5, lm.getX());
            assertEquals(9, lm.getY());
            assertEquals('E', lm.getDirection());

            lm.executeCommand('G');
            assertEquals(5, lm.getX());
            assertEquals(9, lm.getY());
            assertEquals('N', lm.getDirection());

            lm.executeCommand('A');
            assertEquals(5, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('N', lm.getDirection());

            lm.executeCommand('G');
            assertEquals(5, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('W', lm.getDirection());

            lm.executeCommand('A');
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('W', lm.getDirection());

            lm.executeCommand('D');
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('N', lm.getDirection());

            lm.executeCommand('D');
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('E', lm.getDirection());

            lm.executeCommand('D');
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('S', lm.getDirection());

            lm.executeCommand('D');
            assertEquals(4, lm.getX());
            assertEquals(10, lm.getY());
            assertEquals('W', lm.getDirection());

            try {
                lm.executeCommand('F');
                fail("executeCommand must fail when parameter not in A, G or D");
            } catch (IllegalArgumentException e) {
                assertEquals("Bad command F", e.getMessage());
            }
        }
    }
}
