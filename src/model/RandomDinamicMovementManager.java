package model;

import java.util.Random;


public class RandomDinamicMovementManager extends DinamicMovementManager {
    
    private int count = 0;

    public RandomDinamicMovementManager(Position position, Bounds bounds, int speed, boolean canFly) {
        super(position, bounds, speed, canFly);
    }

    @Override
    public Position getNextMove() {
        
        if(count % 10 == 0 || count == 0) {
            int rn = new Random().nextInt(4);
            switch(rn) {
            case 0: this.setDirection(Directions.RIGHT);
                    this.setPattern(MovementPattern.LEFT_RIGHT);
                    break;
            case 1: this.setDirection(Directions.LEFT);
                    this.setPattern(MovementPattern.LEFT_RIGHT);
                    break;
            case 2: this.setDirection(Directions.DOWN);
                    this.setPattern(MovementPattern.UP_DOWN);
                    break;
            case 3: this.setDirection(Directions.UP); 
                    this.setPattern(MovementPattern.UP_DOWN);
                    break;
            }
            
        }
        this.count++;
        
        return this.linearMovement(this.getPosition());
    }

}
