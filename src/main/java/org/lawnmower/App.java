package org.lawnmower;


import java.io.File;
import java.io.IOException;

public class App {

    public static void main( String[] args ) {

        if(args.length != 1){

            System.err.println("java -jar app.jar inputfile");
            System.exit(1);

        }

        try {

            Story story = StoryBuilder.newStory(new InputFIleIterator(new File(args[0])));
            System.out.println(story.run());

        }catch(IllegalArgumentException e){

            System.err.println(e.getMessage());

        } catch (IOException e) {

            System.err.println("IO Error occurred  :" + e.getMessage());

        }

    }

}
