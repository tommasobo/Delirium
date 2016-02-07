package control;

public class Position {
    
    public static enum Directions {
        LEFT,
        RIGHT;
    }
    
    public static class Dimension {
        
        private final int width;
        private final int height;
        
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
        
    }

    private Point point;
    private Directions direction;
    private final Dimension dimension;
    
    public Position(final Point point, final Directions direction, final Dimension dimension) {
        this.point = point;
        this.direction = direction;
        this.dimension = dimension;
    }

    public Point getPoint() {
        return this.point;
    }

    public void setPoint(final Point point) {
        this.point = point;
    }

    public Directions getDirection() {
        return this.direction;
    }

    public void setDirection(final Directions direction) {
        this.direction = direction;
    }

    public Dimension getDimension() {
        return this.dimension;
    }
    
}
