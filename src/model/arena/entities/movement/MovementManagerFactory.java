package model.arena.entities.movement;

import java.util.Optional;

import model.arena.entities.Position;
import model.transfertentities.MovementInfo;
import utility.Pair;

public class MovementManagerFactory {

    public static Pair<Optional<Position>, Optional<MovementManager>> getMovementManager(final Position position,
            final Optional<MovementInfo> movementInfo) {

        if (movementInfo.isPresent()) {
            MovementManager movementManager;
            switch (movementInfo.get().getMovementTypes()) {
            case HERO:
                movementManager = new HeroMovementManagerImpl(position, movementInfo.get().getBounds(),
                        movementInfo.get().getActions(), movementInfo.get().getSpeed(), movementInfo.get().isCanFly());
                break;
            case REACTIVE:
                movementManager = new ReactiveMovementManager(position, movementInfo.get().getBounds(),
                        movementInfo.get().getActions(), movementInfo.get().getSpeed(), movementInfo.get().isCanFly());
                break;
            case RANDOM:
                movementManager = new RandomProactiveMovementManager(position, movementInfo.get().getBounds(),
                        movementInfo.get().getSpeed(), movementInfo.get().isCanFly(),
                        movementInfo.get().getMovementTypes());
                break;
            case VERTICAL_LINEAR:
            case HORIZONTAL_LINEAR:
                movementManager = new LinearProactiveMovementManager(position, movementInfo.get().getBounds(),
                        movementInfo.get().getSpeed(), movementInfo.get().isCanFly(),
                        movementInfo.get().getMovementTypes());
                break;
            default:
                throw new IllegalArgumentException();
            }
            return new Pair<>(Optional.empty(), Optional.of(movementManager));

        } else {
            return new Pair<>(Optional.of(position), Optional.empty());
        }

    }

}
