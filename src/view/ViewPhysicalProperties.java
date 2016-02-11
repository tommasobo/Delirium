package view;

import control.Dimension;
import control.Point;
import control.PhisicalProprieties;

public class ViewPhysicalProperties {
    
    public static enum Directions{
        
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE;
        
    }
  
   private final PhisicalProprieties physicalProperties;
   private final Directions direction;
   
   public ViewPhysicalProperties(final PhisicalProprieties phisicalProperties, final Directions direction) {
       this.physicalProperties = phisicalProperties;
       this.direction = direction;
   }
   
   public Point getPoint() {
       return this.physicalProperties.getPoint();
   }

   public Dimension getDimension() {
       return this.physicalProperties.getDimension();
   }
   
   public int getSpeed() {
       return this.physicalProperties.getSpeed();
   }
    
   public Directions getDirection() {
       return this.direction;
   }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + ((physicalProperties == null) ? 0 : physicalProperties.hashCode());
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
        ViewPhysicalProperties other = (ViewPhysicalProperties) obj;
        if (direction != other.direction)
            return false;
        if (physicalProperties == null) {
            if (other.physicalProperties != null)
                return false;
        } else if (!physicalProperties.equals(other.physicalProperties))
            return false;
        return true;
    }
   
   
}
