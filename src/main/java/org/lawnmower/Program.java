package org.lawnmower;

import java.util.Iterator;
import java.util.function.BiPredicate;

/**
 * A program contains all the information needed to setup and move a lawnmower.
 * It can be consume by a Story as an iterator of characters. Every character will be interpreted as a command
 * for the lawnmower.
 */
public class Program implements Iterator<Character>{

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
     * Current position in instructions
     */
    private int idx;

    /**
     * Creates a new program that will contain valid commands in order to pilot a lawnmower
     * @param initX initial X position of the lawnmower
     * @param initY initial Y position of the lawnmower
     * @param initDir initial direction of the lawnmower
     * @param instructions sequence of instructions to be executed. Can't be null. Can only contain 'A', 'G' or 'D'
     * @param surfaceTester predicate that enables to check if initial coordinates are in the lawn surface
     */
    public Program(long initX, long initY, char initDir, String instructions, BiPredicate<Long, Long> surfaceTester){

        if(initX < 0){

            throw new IllegalArgumentException("Value of X (" + initX+") must be positive.");

        }

        if(initY < 0){

            throw new IllegalArgumentException("Value of Y (" + initY+") must be positive.");

        }

        if(initDir!= 'N' && initDir != 'W' && initDir != 'E' && initDir != 'S'){

            throw new IllegalArgumentException("Value of Direction (" + initDir+") must be N, E, W or S.");

        }

        if(instructions == null){

            throw new IllegalArgumentException("Instructions can't be null.");

        }

        for(char car : instructions.toCharArray()){

            if("ADG".indexOf(car) == -1){

                throw new IllegalArgumentException("Instructions can only contain A,D or G letters.");

            }

        }

        if(surfaceTester == null){

            throw new IllegalArgumentException("SurfaceTester is null.");

        }

        if(!surfaceTester.test(initX, initY)){

            throw new IllegalArgumentException("Coordinates ("+initX+","+initY+") don't belong to surface.");

        }

        this.initX = initX;
        this.initY = initY;
        this.instructions = instructions;
        this.initDirection = initDir;
        idx = 0;

    }

    /**
     * Getter to initial X position
     * @return initial X position ( 0 <= X <= surfaceWidth).
     */
    public long getInitX(){

        return initX;

    }

    /**
     * Getter to initial Y position
     * @return initial Y position ( 0 <= Y <= surfaceHeight).
     */
    public long getInitY(){

        return initY;

    }

    /**
     * Getter to initial direction
     * @return initial direction. (
     */
    public char getInitDirection() {

        return initDirection;

    }

    /**
     * Check if new commands are still a still available
     * @return whether a new command is available
     */
    @Override
    public boolean hasNext() {

        return  idx < instructions.length();

    }

    /**
     * Returns new command to be interpreted
     * @return new command ( guaranteed to be only 'A', 'D' or 'G')
     */
    @Override
    public Character next() {

        return instructions.charAt(idx++);

    }
}
