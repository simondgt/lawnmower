package org.lawnmower.simulation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lawnmower.App;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * End to end tests.
 * We execute main method and provide an input file as a parameter.
 * We then make assertions on stdout
 */
public class RunAppTest {

    /**
     * stdout pipe
     */
    private ByteArrayOutputStream stdout;

    /**
     *stderr pipe
     */
    private ByteArrayOutputStream stderr;

    @Before
    public void init(){
        stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));
        stderr = new ByteArrayOutputStream();
        System.setErr(new PrintStream(stderr));
    }

    @After
    public void close(){
        try {
            stdout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stderr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_PrintUsage_NoParameters() {
        App.main(new String[]{});
        assertEquals("",new String(stdout.toByteArray()));
        assertEquals("java -jar app.jar inputfile\n",new String(stderr.toByteArray()));
    }

    @Test
    public void should_PrintUsage_TooManyParameters() {
        App.main(new String[]{});
        assertEquals("",new String(stdout.toByteArray()));
        assertEquals("java -jar app.jar inputfile\n",new String(stderr.toByteArray()));
    }

    @Test
    public void should_RunTwoPrograms() {
        App.main(new String[]{"test/input1"});
        assertEquals("1 3 N\n5 1 E\n",new String(stdout.toByteArray()));
        assertEquals("",new String(stderr.toByteArray()));
    }

    @Test
    public void should_PrintNothing() {
        App.main(new String[]{"test/input3"});
        assertEquals("\n",new String(stdout.toByteArray()));
        assertEquals("",new String(stderr.toByteArray()));
    }


    @Test
    public void should_ThrowException_FileNotFound() {
        App.main(new String[]{"test/input0"});
        assertEquals("",new String(stdout.toByteArray()));
        assertEquals("IO Error occurred  :test/input0 (No such file or directory)\n",
                new String(stderr.toByteArray()));
    }

    @Test
    public void should_PrintError_NegativeWidth() {
        App.main(new String[]{"test/bad_input1"});
        assertEquals("",new String(stdout.toByteArray()));
        assertEquals("Width  (-1) must be positive.\n",new String(stderr.toByteArray()));
    }

    @Test
    public void should_PrintError_NumberMalFormatted() {
        App.main(new String[]{"test/bad_input2"});
        assertEquals("",new String(stdout.toByteArray()));
        assertEquals("ParseError : Input doesn't respect the specification : a 5\n",
                new String(stderr.toByteArray()));
    }

    @Test
    public void should_PrintError_NumberTooLarge() {
        App.main(new String[]{"test/bad_input3"});
        assertEquals("",new String(stdout.toByteArray()));
        assertEquals("ParseError : Input doesn't respect the specification : 1 554684415315384135846\n",
                new String(stderr.toByteArray()));
    }

    @Test
    public void should_PrintError_OutsideOfLawn() {
        App.main(new String[]{"test/bad_input4"});
        assertEquals("",new String(stdout.toByteArray()));
        assertEquals("Coordinates (1,10) don't belong to surface.\n",
                new String(stderr.toByteArray()));
    }

    @Test
    public void should_PrintError_BadDirection() {
        App.main(new String[]{"test/bad_input5"});
        assertEquals("",new String(stdout.toByteArray()));
        assertEquals("ParseError bad direction : Input doesn't respect the specification : 1 10\n",
                new String(stderr.toByteArray()));
    }

    @Test
    public void should_PrintError_BadInstructions() {
        App.main(new String[]{"test/bad_input6"});
        assertEquals("",new String(stdout.toByteArray()));
        assertEquals("Instructions can only contain A,D or G letters.\n",
                new String(stderr.toByteArray()));
    }
}
