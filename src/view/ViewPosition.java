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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ViewPosition other = (ViewPosition) obj;
        if (direction != other.direction)
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        return true;
    }
   
   
}
