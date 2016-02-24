package model;

public class Bounds {
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    public Bounds(final int minX, final int maxX, final int minY, final int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public int getMinX() {
        return this.minX;
    }

    public int getMaxX() {
        return this.maxX;
    }

    public int getMinY() {
        return this.minY;
    }

    public int getMaxY() {
        return this.maxY;
    }

}
