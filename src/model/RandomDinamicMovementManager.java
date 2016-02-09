package model;

import java.util.Random;

import control.Point;
import control.Position;

public class RandomDinamicMovementManager extends DinamicMovementManager {

    public RandomDinamicMovementManager(Position position, int speed) {
        super(position, speed);
    }

    @Override
    public Position getNextMove() {
        int rn = new Random().nextInt(4);
        Point actualPoint = new Point(this.getPosition().getPoint().getX(), this.getPosition().getPoint().getX());
        Position actualPosition = new Position(actualPoint, this.getPosition().getDirection(), this.getPosition().getDimension());
        switch(rn) {
        case 0: actualPoint.setX(actualPoint.getX() + this.getSpeed()); 
                actualPosition.setDirection(Position.Directions.RIGHT);
                break;
        case 1: actualPoint.setX(actualPoint.getX() - this.getSpeed()); 
                actualPosition.setDirection(Position.Directions.RIGHT);
                break;
        case 2: actualPoint.setY(actualPoint.getY() + this.getSpeed()); 
                break;
        case 3: actualPoint.setY(actualPoint.getY() - this.getSpeed()); 
                break;
        }
        
        return actualPosition;
    }

}
