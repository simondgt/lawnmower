package org.lawnmower.simulation;

import org.lawnmower.simulation.program.LawnProgram;
import org.lawnmower.simulation.world.Lawn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * This builder is in charge of constructing a new story from an input stream (string iterator)
 */
public class SimulationBuilder {

    /**
     * Helper struct that will be used internally in order to represent the initial position of a lawnmower
     */
    private static class Init {

        public final long x;
        public final long y;
        public final char direction;

        public Init(long x, long y, char direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    /**
     * Construct a story by reading content in a string iterator
     *
     * @param dataSource string iterator that contains all the inputs required in order to setup a Story.
     * @return a new Story simulation, based on dataSource
     */
    public static Simulation newSimulation(Iterator<String> dataSource) {

        //creation of the lawn surface
        Lawn lawn = null;
        if (dataSource.hasNext()) {
            long[] size = readLawn(dataSource.next());
            lawn = new Lawn(size[0], size[1]);
        }

        //then, fetch lines two by two and build programs out of them
        boolean hasMorePrograms = dataSource.hasNext();
        List<LawnProgram> lawnPrograms = new ArrayList<>();

        while (hasMorePrograms) {
            //that line contains the initial state of the lawnmower
            Init init = readInit(dataSource.next());
            hasMorePrograms = dataSource.hasNext();
            if (hasMorePrograms) {
                //that line contains the instructions to apply to the lawnmower
                String instructions = dataSource.next();
                hasMorePrograms = dataSource.hasNext();
                lawnPrograms.add(new LawnProgram(init.x, init.y, init.direction, instructions, lawn));
            }
        }
        return new Simulation(lawn, lawnPrograms);
    }

    /**
     * That helper method converts a string into 2 long values, separated by spaces.
     * Here we can handle several spaces delimiter, and we can catch several bad cases during parsing.
     * If the parsing doesn't go well, we trow an IllegalArgumentException
     *
     * @param line line to parse
     * @return two long numbers packed into a long[2]
     */
    private static long[] readLawn(String line) {
        long x, y;
        StringTokenizer tokenizer = new StringTokenizer(line, " ", false);
        if (tokenizer.hasMoreTokens()) {
            try {
                x = Long.parseLong(tokenizer.nextToken().trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("ParseError : Input doesn't respect the specification : " + line);
            }
        } else {
            throw new IllegalArgumentException("ParseError : Input doesn't respect the specification : " + line);
        }

        if (tokenizer.hasMoreTokens()) {
            try {
                y = Long.parseLong(tokenizer.nextToken().trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("ParseError : Input doesn't respect the specification : " + line);
            }
        } else {
            throw new IllegalArgumentException("ParseError : Input doesn't respect the specification : " + line);
        }

        return new long[]{x, y};
    }

    /**
     * That Helper method converts a string into a lawnmower initial state.
     * Here we can handle several spaces delimiter, and we can catch several bad cases during parsing.
     * If the parsing doesn't go well, we trow an IllegalArgumentException
     *
     * @param line line to parse
     * @return an lawnmower initial state
     */
    private static Init readInit(String line) {

        long x, y;
        char direction;
        StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

        if (tokenizer.hasMoreTokens()) {
            try {
                x = Long.parseLong(tokenizer.nextToken().trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("ParseError bad x : Input doesn't respect the specification : " + line);
            }
        } else {
            throw new IllegalArgumentException("ParseError bad x : Input doesn't respect the specification : " + line);
        }

        if (tokenizer.hasMoreTokens()) {
            try {
                y = Long.parseLong(tokenizer.nextToken().trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("ParseError bad y : Input doesn't respect the specification : " + line);
            }
        } else {
            throw new IllegalArgumentException("ParseError bad y : Input doesn't respect the specification : " + line);
        }

        if (tokenizer.hasMoreTokens()) {
            String directionToken = tokenizer.nextToken().trim();
            if ("NSEW".contains(directionToken)) {
                direction = directionToken.charAt(0);
            } else {
                throw new IllegalArgumentException("ParseError bad direction: Input doesn't respect the specification : " + line);
            }
        } else {
            throw new IllegalArgumentException("ParseError bad direction : Input doesn't respect the specification : " + line);
        }
        return new Init(x, y, direction);
    }
}
