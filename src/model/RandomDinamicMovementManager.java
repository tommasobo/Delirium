package model;

import java.util.Random;


public class RandomDinamicMovementManager extends DinamicMovementManager {
    
    private int count = 0;

    public RandomDinamicMovementManager(ModelPosition position, Bounds bounds, boolean canFly) {
        super(position, bounds, canFly);
    }

    @Override
    public ModelPosition getNextMove() {
        
        if(count % 10 == 0 || count == 0) {
            int rn = new Random().nextInt(4);
            switch(rn) {
            case 0: this.setDirection(ModelDirections.RIGHT);
                    this.setPattern(MovementPattern.LEFT_RIGHT);
                    break;
            case 1: this.setDirection(ModelDirections.LEFT);
                    this.setPattern(MovementPattern.LEFT_RIGHT);
                    break;
            case 2: this.setDirection(ModelDirections.DOWN);
                    this.setPattern(MovementPattern.UP_DOWN);
                    break;
            case 3: this.setDirection(ModelDirections.UP); 
                    this.setPattern(MovementPattern.UP_DOWN);
                    break;
            }
            
        }
        this.count++;
        
        return this.linearMovement(this.getPosition());
    }

}
