package org.lawnmower;

import java.io.*;
import java.util.Iterator;

/**
 * This class wraps an iterator around a file input stream.
 * That enables easy consumption of a file in a streaming fashions
 */
public class InputFIleIterator implements Iterator<String> {

    /**
     * Input stream that we decorate
     */
    private final BufferedReader br;

    /**
     * Content of the next line of the file, that can be serve through iterator::next
     */
    private String nextLine;

    /**
     * Create a new iterator around a input stream of file
     *
     * @param file file to open
     * @throws FileNotFoundException thrown if file doesn't exists or can't be opened
     */
    public InputFIleIterator(File file) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(file));
        try {
            nextLine = br.readLine();
        } catch (IOException e) {
            System.err.println("Stop reading input file, because : " + e.getMessage());
            nextLine = null;
        }
    }

    /**
     * Check if we reach EOF. When EOF is reached, we close the input stream.
     *
     * @return whether we reached the last line of the file or not
     */
    @Override
    public boolean hasNext() {
        boolean hasNext = nextLine != null;
        if (!hasNext) {
            try {
                br.close();
            } catch (IOException e) {
                System.err.println("Stop reading input file, because : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return hasNext;
    }

    /**
     * Returns the current line of file and read the next one. If IO error occurs, the next line is set to null,
     * which will cause the end of iterator on next hasNext() call
     *
     * @return a new line of the file
     */
    @Override
    public String next() {
        String tmp = nextLine;
        try {
            nextLine = br.readLine();
        } catch (IOException e) {
            System.err.println("Stop reading input file, because : " + e.getMessage());
            nextLine = null;
        }
        return tmp;
    }
}
