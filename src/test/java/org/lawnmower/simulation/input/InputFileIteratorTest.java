package org.lawnmower.simulation.input;

import org.junit.Test;
import org.lawnmower.simulation.input.InputFIleIterator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import static org.junit.Assert.*;

public class InputFileIteratorTest {

    @Test
    public void should_ThrowException_When_FileNotFound() {
        try {
            new InputFIleIterator(new File("test/bad"));
            fail("file doesn't exits");
        } catch (FileNotFoundException e) {
            assertEquals("test/bad (No such file or directory)", e.getMessage());
        }
    }

    @Test
    public void should_IterateThroughFile_When_FileExists() {
        try {
            Iterator<String> iterator = new InputFIleIterator(new File("test/input1"));
            assertTrue(iterator.hasNext());
            assertEquals("5 5", iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals("1 2 N", iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals("GAGAGAGAA", iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals("3 3 E", iterator.next());
            assertTrue(iterator.hasNext());
            assertEquals("AADAADADDA", iterator.next());
            assertFalse(iterator.hasNext());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
