package org.lawnmower.simulation.program;

import org.lawnmower.simulation.world.Lawn;

/**
 * A program contains all the information needed to setup and move a lawnmower.
 * One can use the getInstructions method to retrieve all the instructions that need to be executed
 */
public class LawnProgram {

    /**
     * Initial X position of Lawnmower
     */
    private final long initX;

    /**
     * Initial Y position of Lawnmower
     */
    private final long initY;

    /**
     * Sequence of instructions to be executed by the lawnmower
     */
    private final String instructions;

    /**
     * Initial direction of the lawnmower
     */
    private final char initDirection;

    /**
     * Creates a new program that will contain valid commands in order to pilot a lawnmower
     *
     * @param initX         initial X position of the lawnmower
     * @param initY         initial Y position of the lawnmower
     * @param initDir       initial direction of the lawnmower
     * @param instructions  sequence of instructions to be executed. Can't be null. Can only contain 'A', 'G' or 'D'
     * @param lawn          lawn on which the lawnmower will execute their program
     */
    public LawnProgram(long initX, long initY, char initDir, String instructions, Lawn lawn) {
        if (initX < 0) {
            throw new IllegalArgumentException("Value of X (" + initX + ") must be positive.");
        }

        if (initY < 0) {
            throw new IllegalArgumentException("Value of Y (" + initY + ") must be positive.");
        }

        if (initDir != 'N' && initDir != 'W' && initDir != 'E' && initDir != 'S') {
            throw new IllegalArgumentException("Value of Direction (" + initDir + ") must be N, E, W or S.");
        }

        if (instructions == null) {
            throw new IllegalArgumentException("Instructions can't be null.");
        }

        for (char car : instructions.toCharArray()) {
            if ("ADG".indexOf(car) == -1) {
                throw new IllegalArgumentException("Instructions can only contain A,D or G letters.");
            }
        }

        if (lawn == null) {
            throw new IllegalArgumentException("SurfaceTester is null.");
        }

        if (!lawn.contains(initX, initY)) {
            throw new IllegalArgumentException("Coordinates (" + initX + "," + initY + ") don't belong to surface.");
        }
        this.initX = initX;
        this.initY = initY;
        this.instructions = instructions;
        this.initDirection = initDir;
    }

    /**
     * Getter to initial X position
     *
     * @return initial X position ( 0 <= X <= surfaceWidth).
     */
    public long getInitX() {
        return initX;
    }

    /**
     * Getter to initial Y position
     *
     * @return initial Y position ( 0 <= Y <= surfaceHeight).
     */
    public long getInitY() {
        return initY;
    }

    /**
     * Getter to initial direction
     *
     * @return initial direction. (
     */
    public char getInitDirection() {
        return initDirection;
    }

    /**
     * Returns instructions that can the be processed one at a time in order to execute the program
     * @return
     */
    public String getInstructions() {
        return instructions;
    }
}
