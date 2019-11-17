package org.lawnmower.simulation;

import org.junit.Test;
import org.lawnmower.simulation.program.LawnProgram;
import org.lawnmower.simulation.world.Lawn;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SimulationTest {

    @Test
    public void should_ThrowException_When_Instantiated_With_NullLawnSurface() {
        try {
            new Simulation(null, new ArrayList<>());
            fail("Surface Tester is null.");
        } catch (IllegalArgumentException e) {
            assertEquals("SurfaceTester is null.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_NullProgram() {
        try {
            new Simulation(new Lawn(100, 100), null);
            fail("Programs is null.");
        } catch (IllegalArgumentException e) {
            assertEquals("Programs is null.", e.getMessage());
        }
    }

    @Test
    public void should_ThrowException_When_Instantiated_With_AtLeastOneNullProgram() {
        try {
            Lawn lawn = new Lawn(100, 100);
            new Simulation(lawn, Arrays.asList(new LawnProgram(1, 1, 'E', "A", lawn), null));
            fail("At least on program is null.");
        } catch (IllegalArgumentException e) {
            assertEquals("At least on program is null.", e.getMessage());
        }
    }

    @Test
    public void should_OutputSimulation_When_Instantiated_With_OneInstructionProgram_And_Run() {
        Lawn lawn = new Lawn(100, 100);
        LawnProgram p1 = new LawnProgram(1, 1, 'E', "A", lawn);
        Simulation simulation = new Simulation(lawn, Arrays.asList(p1));
        String out = simulation.run();
        assertEquals("2 1 E", out);
    }

    @Test
    public void should_OutputSimulation_When_Instantiated_With_ManyInstructionsProgram_UnboundedLawn_And_Run() {
        Lawn lawn = new Lawn(100, 100);
        LawnProgram p1 = new LawnProgram(1, 1, 'E', "AAADAGGA", lawn);
        Simulation simulation = new Simulation(lawn, Arrays.asList(p1));
        String out = simulation.run();
        assertEquals("4 1 N", out);
    }

    @Test
    public void should_OutputDifferentSimulation_When_Instantiated_With_ManyInstructionsProgram_SmallerLawn_And_Run() {
        Lawn lawn = new Lawn(3, 2);
        LawnProgram p1 = new LawnProgram(1, 1, 'E', "AAADAGGA", lawn);
        Simulation simulation = new Simulation(lawn, Arrays.asList(p1));
        String out = simulation.run();
        assertEquals("3 1 N", out);
    }

    @Test
    public void should_OutputMultiLine_When_Instantiated_With_Several_Programs() {
        Lawn lawn = new Lawn(3, 2);
        LawnProgram p1 = new LawnProgram(1, 1, 'E', "AAADAGGA", lawn);
        LawnProgram p2 = new LawnProgram(0, 0, 'N', "GAAAAADA", lawn);
        Simulation simulation = new Simulation(lawn, Arrays.asList(p1, p2));
        String out = simulation.run();
        assertEquals("3 1 N\n0 1 N", out);
    }
}
