package model;

import java.util.Random;


public class RandomDinamicMovementManager extends DinamicMovementManager {
    
    private int count = 0;

    public RandomDinamicMovementManager(ModelPosition position, int speed, Bounds bounds) {
        super(position, speed, bounds);
    }

    @Override
    public ModelPosition getNextMove() {
        ModelPosition actualPosition = new ModelPosition(this.getPosition().getPrimitivePosition(), this.getPosition().getDirection());
        
        if(count % 10 == 0 || count == 0) {
            int rn = new Random().nextInt(4);
            switch(rn) {
            case 0: this.getPosition().setDirection(ModelDirections.RIGHT);
                    this.setPattern(MovementPattern.LEFT_RIGHT);
                    break;
            case 1: this.getPosition().setDirection(ModelDirections.LEFT);
                    this.setPattern(MovementPattern.LEFT_RIGHT);
                    break;
            case 2: this.getPosition().setDirection(ModelDirections.DOWN);
                    this.setPattern(MovementPattern.UP_DOWN);
                    break;
            case 3: this.getPosition().setDirection(ModelDirections.UP); 
                    this.setPattern(MovementPattern.UP_DOWN);
                    break;
            }
            
        }
        actualPosition = this.linearMovement(actualPosition);
        this.count++;
        
        return actualPosition;
    }

}
