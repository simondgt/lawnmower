package org.lawnmower;

import java.util.function.BiPredicate;

/**
 * This class describe the API and the state related to a lawnmower
 */
public class Lawnmower {

    /**
     * Current position X of the device
     */
    private long x;

    /**
     * Current position Y of the device
     */
    private long y;

    /**
     * Current direction of the device
     */
    private char direction;

    /**
     * Reference to the minimalistic surface API. It enables to ask if a new position is valid.
     */
    private final BiPredicate<Long, Long> surfaceTest;

    /**
     * Create a new Lawnmower with initial position and direction. A valid reference to a surface tester is required
     * @param initX Initial X position of the device
     * @param initY Initial Y position of the device
     * @param initDirection Initial direction of the device
     * @param surfaceTest Reference to the surface tester API
     */
    public Lawnmower(long initX, long initY, char initDirection, BiPredicate<Long, Long> surfaceTest){

        if(initX < 0){

            throw new IllegalArgumentException("Value of X (" + initX+") must be positive.");

        }

        if(initY < 0){

            throw new IllegalArgumentException("Value of Y (" + initY+") must be positive.");

        }

        if(initDirection != 'N' && initDirection != 'W' && initDirection != 'E' && initDirection != 'S'){

            throw new IllegalArgumentException("Value of Direction (" + initDirection+") must be N, E W or S.");

        }

        if(surfaceTest == null){

            throw new IllegalArgumentException("Surface Tester can't be null.");

        }

        this.x = initX;
        this.y = initY;
        this.direction = initDirection;
        this.surfaceTest = surfaceTest;

    }

    /**
     * Get current X position
     * @return
     */
    public long getX(){

        return x;

    }

    /**
     * Get current Y position
     * @return
     */
    public long getY(){

        return y;

    }

    /**
     * Get current direction of device
     * @return
     */
    public char getDirection(){

        return direction;

    }

    /**
     * Update direction of device based on previous direction.
     */
    public void turnLeft(){

        switch(direction){

            case 'N':
                direction = 'W';
                break;

            case 'W':
                direction = 'S';
                break;

            case 'S':
                direction = 'E';
                break;

            default:
                direction = 'N';

        }

    }

    /**
     * Update direction of device based on previous direction.
     */
    public void turnRight(){

        switch(direction){

            case 'N':
                direction = 'E';
                break;

            case 'E':
                direction = 'S';
                break;

            case 'S':
                direction = 'W';
                break;

            default:
                direction = 'N';

        }

    }

    /**
     * Try to advance the device. The movement is committed only if the surface tester accepts the new coordinates.
     */
    public void advance(){

        long[]  newCoordinates = computeNewCoordinates();
        if(surfaceTest.test(newCoordinates[0], newCoordinates[1])){

            this.x = newCoordinates[0];
            this.y = newCoordinates[1];

        }

    }

    /**
     * Compute new coordinates based on current direction and position
     * @return a array of size 2, first element contains new X position, second element contains Y position
     */
    private long[] computeNewCoordinates(){

        switch(direction){

            case 'N':
                return new long[]{x, y+1};

            case 'W':
                return new long[]{x-1, y};

            case 'S':
                return new long[]{x, y-1};

            default:
                return new long[]{x+1, y};

        }


    }

    public void executeCommand(char command){

        switch(command){

            case 'G':
                turnLeft();
                return;

            case 'D':
                turnRight();
                return;

            case 'A':
                advance();
                return;

            default:
                throw new IllegalArgumentException("Bad command " + command);

        }

    }


}
