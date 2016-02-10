package model;

import control.Point;
import control.Position;

public class LinearDinamicMovementManager extends DinamicMovementManager {
    
    public LinearDinamicMovementManager(ModelPosition position, int speed, Bounds bounds) {
        super(position, speed, bounds);
        
    }

    @Override
    public ModelPosition getNextMove() {
        Point actualPoint = new Point(this.getPosition().getPoint().getX(), this.getPosition().getPoint().getY());
        ModelPosition actualPosition = new ModelPosition(this.getPosition().getPrimitivePosition(), this.getPosition().getDirection());
        
        return this.linearMovement(actualPosition);
    }

}
