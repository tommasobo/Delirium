package model.arena.manager;

/**
 * Difference between two points.
 * 
 * @author Matteo Magnani
 *
 */
class PointOffset {
    private final int offsetX;
    private final int offsetY;

    public PointOffset(final int offsetX, final int offsetY) {
        super();
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }
}
