package org.lawnmower;


import org.lawnmower.simulation.Simulation;
import org.lawnmower.simulation.SimulationBuilder;

import java.io.File;
import java.io.IOException;

public class App {

    /**
     * Entry point of program. If number of parameter is incorrect or if input file is invalid, print error on stderr
     * If everything is fine, print simulation results on stdout.
     *
     * exit status code is always 0 (even if there is an error).
     * @param args expects one argument : path to file to open
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("java -jar app.jar inputfile");
            return;
        }

        try {
            Simulation simulation = SimulationBuilder.fromFile(new File(args[0]));
            System.out.println(simulation.run());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("IO Error occurred  :" + e.getMessage());
        }
    }
}
