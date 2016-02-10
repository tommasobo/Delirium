package model;

import java.util.Random;

import control.Point;
import control.Position;

public class RandomDinamicMovementManager extends DinamicMovementManager {
    
    private int count = 0;

    public RandomDinamicMovementManager(Position position, int speed, Bounds bounds) {
        super(position, speed, bounds);
    }

    @Override
    public Position getNextMove() {
        int rn = new Random().nextInt(4);
        Point actualPoint = new Point(this.getPosition().getPoint().getX(), this.getPosition().getPoint().getX());
        Position actualPosition = new Position(actualPoint, this.getPosition().getDirection(), this.getPosition().getDimension());
        if(count % 10 == 0) {
            
            switch(rn) {
            case 0: actualPosition.setDirection(Position.Directions.RIGHT);
                    break;
            case 1: actualPosition.setDirection(Position.Directions.LEFT);
                    break;
            case 2: actualPosition.setDirection(Position.Directions.DOWN);
                    break;
            case 3: actualPosition.setDirection(Position.Directions.UP); 
                    break;
            }
            
        } 
        
        actualPosition = this.linearMovement(actualPosition);
        
        this.count++;
        
        return actualPosition;
    }

}
