package model.arena.utility;
/**
 * This class specified the limits of the entities.
 * @author josephgiovanelli
 *
 */
public class Bounds {
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    /**
     * This constructor specified the horizontal limits and the vertical one.
     * 
     * @param minX : the minimum horizontal limit. 
     * @param maxX : the maximum horizontal limit.
     * @param minY : the minimum vertical limit.
     * @param maxY : the maximum vertical limit.
     */
    public Bounds(final int minX, final int maxX, final int minY, final int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    /**
     * Get the minimum horizontal limit.
     * @return : value that you want.
     */
    public int getMinX() {
        return this.minX;
    }

    /**
     * Get the maximum horizontal limit.
     * @return : value that you want.
     */
    public int getMaxX() {
        return this.maxX;
    }

    /**
     * Get the minimum vertical limit.
     * @return : value that you want.
     */
    public int getMinY() {
        return this.minY;
    }

    /**
     * Get the maximum vertical limit.
     * @return : value that you want.
     */
    public int getMaxY() {
        return this.maxY;
    }

}
