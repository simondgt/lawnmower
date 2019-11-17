package org.lawnmower;

import java.util.function.BiPredicate;

/**
 * This API represents a lawn surface. Its users can only ask if a position is valid in that lawn surface.
 * Since we model that constraint as a simple predicate, is is very easy to mock or to extend.
 */
public class SurfaceTester implements BiPredicate<Long, Long> {

    private final long height;
    private final long width;

    public SurfaceTester(long width, long height) {
        if (width < 0) {
            throw new IllegalArgumentException("Width  (" + width + ") must be positive.");
        }

        if (height < 0) {
            throw new IllegalArgumentException("Height (" + height + ") must be positive.");
        }

        this.height = height;
        this.width = width;
    }

    @Override
    public boolean test(Long x, Long y) {
        return 0 <= x && x <= width && 0 <= y && y <= height;
    }
}
