package org.lawnmower.simulation;

import org.lawnmower.simulation.program.LawnProgram;
import org.lawnmower.simulation.world.Lawn;
import org.lawnmower.simulation.world.Lawnmower;

import java.util.List;

/**
 * This represents the simulation world. It contains the lawn surface and all the program to be executed.
 * The run method will execute all the programs sequentially and print final state of lawnmower on stdout.
 */
public class Simulation {

    /**
     * The lawn surface. It's API falls down to a simple predicate : whether some coordinates are in the lawn or not
     */
    private final Lawn lawn;

    /**
     * List of programs that will be executed.
     */
    private final List<LawnProgram> lawnPrograms;

    /**
     * Create a new story with a lawn surface and programs to execute.
     *
     * @param lawn lawn surface. Can't be null
     * @param lawnPrograms Programs to execute. Can't be null
     */
    Simulation(Lawn lawn, List<LawnProgram> lawnPrograms) {
        if (lawn == null) {
            throw new IllegalArgumentException("SurfaceTester is null.");
        }

        if (lawnPrograms == null) {
            throw new IllegalArgumentException("Programs is null.");
        }

        for (LawnProgram p : lawnPrograms) {
            if (p == null) {
                throw new IllegalArgumentException("At least on program is null.");
            }
        }

        this.lawn = lawn;
        this.lawnPrograms = lawnPrograms;
    }

    /**
     * Execute all the programs and print in a string the final state of lawnmowers.
     */
    public String run() {

        StringBuilder builder = new StringBuilder();
        for (LawnProgram pg : lawnPrograms) {
            Lawnmower lm = new Lawnmower(pg.getInitX(), pg.getInitY(), pg.getInitDirection(), lawn);
            for(int i = 0; i < pg.getInstructions().length(); i++) {
                lm.executeCommand(pg.getInstructions().charAt(i));
            }

            if (builder.length() > 0) {
                builder.append("\n");
            }

            builder.append(lm.getX())
                    .append(" ")
                    .append(lm.getY())
                    .append(" ")
                    .append(lm.getDirection());
        }

        return builder.toString();
    }

}
