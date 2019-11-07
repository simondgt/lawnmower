package org.lawnmower;


import java.io.File;
import java.io.IOException;

public class App 
{
    public static void main( String[] args )
    {

        try {
            Story story = StoryBuilder.newStory(new InputFIleIterator(new File("test/input1")));
            System.out.println(story.run());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
