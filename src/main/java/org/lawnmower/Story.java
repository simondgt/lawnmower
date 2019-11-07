package org.lawnmower;

import java.util.List;
import java.util.function.BiPredicate;

/**
 * This represents the simulation world. It contains the lawn surface and all the program to be executed.
 * The run method will execute all the programs sequentially and print final state of lawnmower on stdout.
 */
public class Story {

    /**
     * The lawn surface. It's API falls down to a simple predicate : whether some coordinates are in the lawn or not
     */
    private final BiPredicate<Long, Long> surfaceTester;

    /**
     * List of programs that will be executed.
     */
    private final List<Program> programs;

    /**
     * Create a new story with a lawn surface and programs to execute.
     * @param surfaceTester lawn surface. Can't be null
     * @param programs Programs to execute. Can't be null
     */
    public Story(BiPredicate<Long, Long> surfaceTester, List<Program> programs){

        if(surfaceTester == null){

            throw new IllegalArgumentException("SurfaceTester is null.");

        }

        if(programs == null){

            throw new IllegalArgumentException("Programs is null.");

        }

        for(Program p : programs){

            if(p == null){

                throw new IllegalArgumentException("At least on program is null.");

            }

        }

        this.surfaceTester= surfaceTester;
        this.programs = programs;

    }

    /**
     * Execute all the programs and print in a string the final state of lawnmowers.
     */
    public String run(){

        StringBuilder builder = new StringBuilder();
        for(Program pg : programs){

            Lawnmower lm = new Lawnmower(pg.getInitX(), pg.getInitY(), pg.getInitDirection(), surfaceTester);
            while(pg.hasNext()){

                lm.executeCommand(pg.next());

            }

            if(builder.length() > 0){

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
