package org.lawnmower;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StoryBuilderTest {

    @Test
    public void testStoryBuilder(){

        {
            try {

                // Everything is fine
                List<String> input = new ArrayList<>();
                input.add("5 5");
                input.add("1 1 N");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());

            }catch(IllegalArgumentException e){

                fail(e.getMessage());

            }

        }

        {
            try {

                // Bad lawn surface
                List<String> input = new ArrayList<>();
                input.add("-1 5");
                input.add("0 0 N");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("Width  (-1) must be positive.");

            }catch(IllegalArgumentException e){

                assertEquals("Width  (-1) must be positive.", e.getMessage());

            }

        }

        {
            try {

                // Bad lawn surface
                List<String> input = new ArrayList<>();
                input.add("1 -5");
                input.add("0 0 N");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("Height (-5) must be positive.");

            }catch(IllegalArgumentException e){

                assertEquals("Height (-5) must be positive.", e.getMessage());

            }

        }

        {
            try {

                // Bad lawn surface
                List<String> input = new ArrayList<>();
                input.add("1");
                input.add("0 0 N");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("ParseError : Input doesn't respect the specification : 1");

            }catch(IllegalArgumentException e){

                assertEquals("ParseError : Input doesn't respect the specification : 1", e.getMessage());

            }

        }

        {
            try {

                // Bad lawn surface
                List<String> input = new ArrayList<>();
                input.add("1 a");
                input.add("0 0 N");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("ParseError : Input doesn't respect the specification : 1 a");

            }catch(IllegalArgumentException e){

                assertEquals("ParseError : Input doesn't respect the specification : 1 a", e.getMessage());

            }

        }

        {
            try {

                // Bad lawn surface
                List<String> input = new ArrayList<>();
                input.add("1 14546541351251651813518313515813881");
                input.add("0 0 N");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("ParseError : Input doesn't respect the specification : 1 a");

            }catch(IllegalArgumentException e){

                assertEquals(
                        "ParseError : Input doesn't respect the specification : 1 14546541351251651813518313515813881",
                        e.getMessage());

            }

        }

        {
            try {

                // Bad lawn surface
                List<String> input = new ArrayList<>();
                input.add("1c 1");
                input.add("0 0 N");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("ParseError : Input doesn't respect the specification : 1c 1");

            }catch(IllegalArgumentException e){

                assertEquals("ParseError : Input doesn't respect the specification : 1c 1", e.getMessage());

            }

        }

        {
            try {

                // Bad lawn surface
                List<String> input = new ArrayList<>();
                input.add("");
                input.add("0 0 N");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("ParseError : Input doesn't respect the specification : ");

            }catch(IllegalArgumentException e){

                assertEquals("ParseError : Input doesn't respect the specification : ", e.getMessage());

            }

        }




        {
            try {

                // Bad init position
                List<String> input = new ArrayList<>();
                input.add("5 5");
                input.add("6 0 N");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("Coordinates (6,0) don't belong to surface.");

            }catch(IllegalArgumentException e){

                assertEquals("Coordinates (6,0) don't belong to surface.", e.getMessage());

            }

        }

        {
            try {

                // Bad init position
                List<String> input = new ArrayList<>();
                input.add("5 5");
                input.add("3 6 N");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("Coordinates (3,6) don't belong to surface.");

            }catch(IllegalArgumentException e){

                assertEquals("Coordinates (3,6) don't belong to surface.", e.getMessage());

            }

        }

        {
            try {

                // Bad init direction
                List<String> input = new ArrayList<>();
                input.add(" 5   10 ");
                input.add("3 6 Z");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("ParseError bad direction: Input doesn't respect the specification : 3 6 Z");

            }catch(IllegalArgumentException e){

                assertEquals("ParseError bad direction: Input doesn't respect the specification : 3 6 Z",
                        e.getMessage());

            }

        }

    }


    @Test
    public void testInitPosition(){

        {
            try {

                // Bad init direction
                List<String> input = new ArrayList<>();
                input.add(" 5   10 ");
                input.add("3454654654654546456465465211 6 Z");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("ParseError bad x : Input doesn't respect the specification : 3454654654654546456465465211 6 Z");

            }catch(IllegalArgumentException e){

                assertEquals("ParseError bad x : Input doesn't respect the specification : 3454654654654546456465465211 6 Z",
                        e.getMessage());

            }

        }

        {
            try {

                // Bad init direction
                List<String> input = new ArrayList<>();
                input.add(" 5   10 ");
                input.add("1 a Z");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("ParseError bad y : Input doesn't respect the specification : 1 a Z");

            }catch(IllegalArgumentException e){

                assertEquals("ParseError bad y : Input doesn't respect the specification : 1 a Z",
                        e.getMessage());

            }

        }

        {
            try {

                // Bad init direction
                List<String> input = new ArrayList<>();
                input.add(" 5   10 ");
                input.add("1 1");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("ParseError bad direction : Input doesn't respect the specification : 1 1");

            }catch(IllegalArgumentException e){

                assertEquals("ParseError bad direction : Input doesn't respect the specification : 1 1",
                        e.getMessage());

            }

        }

        {
            try {

                // Bad init direction
                List<String> input = new ArrayList<>();
                input.add(" 5   10 ");
                input.add("1 1 Z");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("ParseError bad direction : Input doesn't respect the specification : 1 1 Z");

            }catch(IllegalArgumentException e){

                assertEquals("ParseError bad direction: Input doesn't respect the specification : 1 1 Z",
                        e.getMessage());

            }

        }

        {
            try {

                // Bad init direction
                List<String> input = new ArrayList<>();
                input.add(" 5   10 ");
                input.add("");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("ParseError bad x : Input doesn't respect the specification : ");

            }catch(IllegalArgumentException e){

                assertEquals("ParseError bad x : Input doesn't respect the specification : ",
                        e.getMessage());

            }


        }

        {
            try {

                // Bad init direction
                List<String> input = new ArrayList<>();
                input.add(" 5   10 ");
                input.add("1");
                input.add("AGGDGGA");
                StoryBuilder.newStory(input.iterator());
                fail("ParseError bad y : Input doesn't respect the specification : 1");

            }catch(IllegalArgumentException e){

                assertEquals("ParseError bad y : Input doesn't respect the specification : 1",
                        e.getMessage());

            }


        }

    }

    @Test
    public void testProgram(){

        {

            // no instructions
            List<String> input = new ArrayList<>();
            input.add(" 5   10 ");
            input.add("1 1 E");
            Story story = StoryBuilder.newStory(input.iterator());
            String out = story.run();
            assertEquals("", out);

        }
        {
            //good example
            List<String> input = new ArrayList<>();
            input.add(" 5   10 ");
            input.add("1 1 N");
            input.add("AAAA");
            Story story = StoryBuilder.newStory(input.iterator());
            String out = story.run();
            assertEquals("1 5 N", out);

        }

    }


}
