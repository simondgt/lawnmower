package org.lawnmower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;

public class StoryBuilder {

    private static class Init{

        public final long x;
        public final long y;
        public final char direction;

        public Init(long x, long y, char direction){

            this.x = x;
            this.y = y;
            this.direction = direction;

        }


    }

    public static Story newStory(Iterator<String> dataSource){

        BiPredicate<Long, Long> surfaceTester = null;
        if(dataSource.hasNext()) {
            long[] size = readSurface(dataSource.next());
            surfaceTester = new SurfaceTester(size[0], size[1]);
        }


        boolean hasMorePrograms = true;
        List<Program> programs = new ArrayList<>();

        while(hasMorePrograms){

            Init init = readInit(dataSource.next());
            String instructions = dataSource.next();

            hasMorePrograms = dataSource.hasNext();
            programs.add(new Program( init.x, init.y, init.direction,instructions,surfaceTester));
        }

        return new Story(surfaceTester,programs);

    }

    private static long[] readSurface(String line){

        String[] array = line.split(" ");
        return new long[]{Long.parseLong(array[0]),Long.parseLong(array[1])};

    }

    private static Init readInit(String line){
        String[] array = line.split(" ");
        return new Init(Long.parseLong(array[0]), Long.parseLong(array[1]), array[2].charAt(0));

    }


}
