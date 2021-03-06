package org.lawnmower.simulation.world;

import java.util.function.BiPredicate;

/**
 * This API represents a lawn surface. Its users can only ask if a position is valid in that lawn surface.
 * Since we model that constraint as a simple predicate, is is very easy to mock or to extend.
 */
public class Lawn{

    private final long height;
    private final long width;

    public Lawn(long width, long height) {
        if (width < 0) {
            throw new IllegalArgumentException("Width  (" + width + ") must be positive.");
        }

        if (height < 0) {
            throw new IllegalArgumentException("Height (" + height + ") must be positive.");
        }

        this.height = height;
        this.width = width;
    }

    public boolean contains(Long x, Long y) {
        return 0 <= x && x <= width && 0 <= y && y <= height;
    }

}
