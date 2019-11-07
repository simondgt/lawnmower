package org.lawnmower;

import java.util.function.BiPredicate;

public class SurfaceTester implements BiPredicate<Long, Long> {

   private final long height;
   private final long width;

   public SurfaceTester(long width,long height){

       this.height = height;
       this.width = width;

   }


    @Override
    public boolean test(Long x, Long y) {
        return 0 <= x && x <= width  && 0 <= y && y <= height ;
    }
}
