package org.lawnmower;

import java.util.Iterator;

public class LawnmowerProgram implements Iterator<Character>{

    private final long initX;
    private final long initY;
    private final String instructions;
    private final char initDirection;
    private int idx;

    public LawnmowerProgram(long initX, long initY, char initDirection, String instructions){

        this.initX = initX;
        this.initY = initY;
        this.instructions = instructions;
        this.initDirection = initDirection;
        idx = 0;

    }

    public long getInitX(){

        return initX;

    }

    public long getInitY(){

        return initY;

    }

    public char getInitDirection() {

        return initDirection;

    }

    @Override
    public boolean hasNext() {

        return  idx < instructions.length();

    }

    @Override
    public Character next() {

        return instructions.charAt(idx++);

    }
}
