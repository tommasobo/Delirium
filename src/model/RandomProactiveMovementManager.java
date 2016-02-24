package model;

import java.util.Random;


public class RandomProactiveMovementManager extends LinearProactiveMovementManager {
    
    private int count = 0;

    public RandomProactiveMovementManager(Position position, Bounds bounds, int speed, boolean canFly, MovementTypes movementTypes) {
        super(position, bounds, speed, canFly, movementTypes);
    }

    @Override
    public Position getNextMove() {
        
        if(count % 10 == 0 || count == 0) {
            switch(new Random().nextInt(4)) {
            case 0: this.setAction(Actions.MOVE);
                    this.setDirection(Directions.RIGHT);
                    this.setMovementTypes(MovementTypes.HORIZONTAL_LINEAR);
                    break;
            case 1: this.setAction(Actions.MOVE);
                    this.setDirection(Directions.LEFT);
                    this.setMovementTypes(MovementTypes.HORIZONTAL_LINEAR);
                    break;
            case 2: this.setAction(Actions.JUMP);
                    this.setMovementTypes(MovementTypes.VERTICAL_LINEAR);
                    break;
            case 3: this.setAction(Actions.FALL);
                    this.setMovementTypes(MovementTypes.VERTICAL_LINEAR);
                    break;
            }
            
        }
        this.count++;
        
        return super.getNextMove();
    }

}
