package org.lawnmower;


import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        try {
            Story story = StoryBuilder.newStory(new InputFIleIterator(new File("test/input1")));
            story.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
