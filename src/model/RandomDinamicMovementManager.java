package model;

import java.util.Random;


public class RandomDinamicMovementManager extends DinamicMovementManager {
    
    private int count = 0;

    public RandomDinamicMovementManager(ModelPosition position, int speed, Bounds bounds) {
        super(position, speed, bounds);
    }

    @Override
    public ModelPosition getNextMove() {
        int rn = new Random().nextInt(4);
        ModelPosition actualPosition = new ModelPosition(this.getPosition().getPrimitivePosition(), this.getPosition().getDirection());
        if(count % 10 == 0) {
            
            switch(rn) {
            case 0: actualPosition.setDirection(ModelDirections.RIGHT);
                    this.setPattern(MovementPattern.LEFT_RIGHT);
                    break;
            case 1: actualPosition.setDirection(ModelDirections.LEFT);
                    this.setPattern(MovementPattern.LEFT_RIGHT);
                    break;
            case 2: actualPosition.setDirection(ModelDirections.DOWN);
                    this.setPattern(MovementPattern.UP_DOWN);
                    break;
            case 3: actualPosition.setDirection(ModelDirections.UP); 
                    this.setPattern(MovementPattern.UP_DOWN);
                    break;
            }
            
        }
        actualPosition = this.linearMovement(actualPosition);
        this.count++;
        
        return actualPosition;
    }

}
