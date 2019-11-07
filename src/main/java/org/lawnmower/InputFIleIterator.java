package org.lawnmower;

import java.io.*;
import java.util.Iterator;

public class InputFIleIterator implements Iterator<String> {

    private BufferedReader br;
    private String nextLine;

    public InputFIleIterator(File file) throws FileNotFoundException {

        br = new BufferedReader(new FileReader(file));
        try {
            nextLine = br.readLine();
        } catch (IOException e) {
            nextLine = null;
        }


    }

    @Override
    public boolean hasNext() {
        boolean hasNext =  nextLine != null;

        if(!hasNext){

            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return hasNext;

    }

    @Override
    public String next() {
        String tmp = nextLine;
        try {
            nextLine = br.readLine();
        } catch (IOException e) {
            nextLine = null;
        }

        return tmp;
    }
}
