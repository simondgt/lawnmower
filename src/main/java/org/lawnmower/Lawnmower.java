package org.lawnmower;

import java.util.function.BiPredicate;

public class Lawnmower {

    private long x;
    private long y;
    private char direction;

    private final BiPredicate<Long, Long> surfaceTest;

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

    public long getX(){

        return x;

    }

    public long getY(){

        return y;

    }

    public char getDirection(){

        return direction;

    }

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

    public void advance(){

        long[]  newCoordinates = computeNewCoordinates();
        if(surfaceTest.test(newCoordinates[0], newCoordinates[1])){

            this.x = newCoordinates[0];
            this.y = newCoordinates[1];

        }

    }

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


}
