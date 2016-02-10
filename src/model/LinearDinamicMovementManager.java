package model;

import control.Point;
import control.Position;

public class LinearDinamicMovementManager extends DinamicMovementManager {
    
    
    

    public LinearDinamicMovementManager(Position position, int speed, Bounds bounds) {
        super(position, speed, bounds);
        
    }

    @Override
    public Position getNextMove() {
        Point actualPoint = new Point(this.getPosition().getPoint().getX(), this.getPosition().getPoint().getY());
        Position actualPosition = new Position(actualPoint, this.getPosition().getDirection(), this.getPosition().getDimension());
        
        return this.linearMovement(actualPosition);
    }

}
