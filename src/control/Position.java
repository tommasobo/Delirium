package control;

public class Position {
    
    public static enum Directions {
        LEFT,
        RIGHT;
    }
    
    private int x;
    private int y;
    private Directions direction;
    
    public Position(final int x, final int y, final Directions direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    
    public void setY(final int y) {
        this.y = y;
    }

    public Directions getDirection() {
        return direction;
    }
    
    public void setDirection(final Directions direction) {
        this.direction = direction;
    }

}
