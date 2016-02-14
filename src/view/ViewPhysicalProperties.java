package view;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class ViewPhysicalProperties {
  
    private final Point2D point;
    private final Dimension2D dimension;
    private final int speed;
    private final Actions action;
   
    public ViewPhysicalProperties(final int x, final int y, final int width, final int height, final int speed, final Actions action) {
        this.point = new Point2D(x, y);
        this.dimension = new Dimension2D(width, height);
        this.speed = speed;
        this.action = action;
    }

    public Point2D getPoint() {
        return this.point;
    }
    
    public Dimension2D getDimension() {
        return this.dimension;
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public Actions getAction() {
        return this.action;
    }
}
