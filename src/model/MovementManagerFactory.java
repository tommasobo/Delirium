package model;

import java.util.Optional;

import control.Pair;

public class MovementManagerFactory {
    
    public static Pair<Optional<Position>, Optional<MovementManager>> getMovementManager(Position position, Optional<MovementInfoImpl> movementInfo) {
        
        if(!movementInfo.isPresent()) {
            return new Pair<>(Optional.of(position), Optional.empty());
        } else {
            MovementManager movementManager;
            switch (movementInfo.get().getMovementTypes()) {
            case HERO: 
                movementManager = new HeroMovementManager(position, movementInfo.get().getBounds(), movementInfo.get().getActions(), movementInfo.get().getSpeed(), movementInfo.get().isCanFly());
                break;
            case REACTIVE :
                movementManager = new ReactiveMovementManager(position, movementInfo.get().getBounds(), movementInfo.get().getActions(), movementInfo.get().getSpeed(), movementInfo.get().isCanFly());
                break;
            case RANDOM:
                movementManager = new RandomDinamicMovementManager(position, movementInfo.get().getBounds(), movementInfo.get().getSpeed(), movementInfo.get().isCanFly(), movementInfo.get().getMovementTypes());
                break;
            case VERTICAL_LINEAR:
            case HORIZONTAL_LINEAR:
                movementManager = new LinearDinamicMovementManager(position, movementInfo.get().getBounds(), movementInfo.get().getSpeed(), movementInfo.get().isCanFly(), movementInfo.get().getMovementTypes());
                break;
            default:
                throw new IllegalArgumentException();
            }
            return new Pair<>(Optional.empty(), Optional.of(movementManager));
        }
        
    }

}
