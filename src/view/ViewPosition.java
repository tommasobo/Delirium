package view;

import control.Dimension;
import control.Point;
import control.Position;

public class ViewPosition {
    
    public static enum Directions{
        
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE;
        
    }
  
   private final Position position;
   private final Directions direction;
   
   public ViewPosition(final Position position, final Directions direction) {
       this.position = position;
       this.direction = direction;
   }
   
   public Point getPoint() {
       return this.position.getPoint();
   }

   public Dimension getDimension() {
       return this.position.getDimension();
   }
    
   public Directions getDirection() {
       return this.direction;
   }
}
