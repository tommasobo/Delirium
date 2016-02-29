package utility;

/**
 * A generic dimension with width and height
 *
 */
public class Dimension{

    private final int width;
    private final int height;
    
    /**
     * 
     * @param width
     *          The width of the something that you want.
     * @param height
     *          The height of the something that you want.
     */
    public Dimension(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        return "Dimension [width=" + width + ", height=" + height + "]";
    }
    
    
}
