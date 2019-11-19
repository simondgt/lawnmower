# lawnmower
Short coding exercise written in Java 11

This codebase is designed around very simple ideas and concepts that are assembled together.
It is composed of a few different parts :
- A Lawnmower class that contains the state of a Lawnmower and a simple API to access/alter that state
- A LawnProgram class that contains initial state of a Lawnmower and all the commands we want to apply on that lawnmower (turn left,  turn right, advance)
- A Lawn that can tell if a given position is valid or not
- A Simulation, that can simulate the execution of lawn programs over a given lawn
- A SimulationBuilder, that build a Simulation based on specified input. That input is defined by some specifications.
- App is the main class that takes care of arguments and world set up. It also notifies user if an error occurred.

The final results are printed on stdout.

Build the project
-----------------

Just run 
```bash
mvn package
```
It will compile the sources, run all the unit tests and generate a jar file in ```target``` folder.

Execute the program
-------------------
```bash
java -jar target/electric-lawnmower-1.0-SNAPSHOT.jar test/input1
```
Will execute the program on file input test/input1

Tests
-----

- This project contains some unit tests. They should be straightforward enough to describe how all the different pieces interact together
- There are also a few files in ```test/``` that can be used to play with the command line tool