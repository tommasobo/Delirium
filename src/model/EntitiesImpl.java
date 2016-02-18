package model;

import java.util.Optional;

import control.Point;

public class EntitiesImpl implements Entities {
    
    private final int code;
    private Optional<Position> position;
//    private Optional<Actions> action;
//    private Optional<StaticManager> staticManager;
    private final Optional<LifeManager> lifeManager;
    private final Optional<MovementManager> movementManager;
    private final Optional<ShootManager> shootManager;
    private final Optional<Integer> contactDamage;

    public EntitiesImpl(int code, Optional<LifeManager> lifeManager, MovementManager movementManager, Optional<ShootManager> shootManager, Optional<Integer> contactDamage) {
        this.code = code;
        this.position = Optional.empty();
        this.lifeManager = lifeManager;
        this.movementManager = Optional.of(movementManager);
        this.contactDamage = contactDamage;
        this.shootManager = shootManager;
    }
    
    public EntitiesImpl(int code, Optional<LifeManager> lifeManager, Position position, Optional<ShootManager> shootManager, Optional<Integer> contactDamage) {
        this.code = code;
        this.position = Optional.of(position);
        this.lifeManager = lifeManager;
        this.movementManager = Optional.empty();
        this.contactDamage = contactDamage;
        this.shootManager = shootManager;
    }
    

    @Override
    public int getCode() {
        return this.code;
    }
    
    @Override
    public Position getPosition() {
        return this.position.isPresent() ? new Position(this.position.get().getPoint(), this.position.get().getDirection(), this.position.get().getDimension()) : this.movementManager.get().getPosition();
    }

    @Override
    public void setPosition(final Point point, final Directions direction) {
        if (this.position.isPresent()) {
            this.position = Optional.of(new Position(point, direction, this.position.get().getDimension()));
        } else {
            this.movementManager.get().setPosition(point, direction);
        }
    }


    @Override
    public Optional<LifeManager> getLifeManager() {
        return this.lifeManager;
    }
    
    public Optional<MovementManager> getMovementManager() {
        return this.movementManager;
    }
    
    public Optional<ShootManager> getShootManager() {
        return this.shootManager;
    }
    
    @Override
    public Optional<Integer> getContactDamage() {
        return this.contactDamage;
    }
    
    
    @Override
    public Actions getAction() {
//        if (!this.shootManager.haveShooted()) {
//           return this.movementManager.get().getAction();
//        } else {
//            return Actions.SHOOT;
//        }
        if (this.movementManager.isPresent()) {
           return this.movementManager.get().getAction();
        } else {
            return Actions.STOP;
        }
    }
    
    @Override
    public void setAction(Actions action) {
        if (this.movementManager.isPresent()) {
            this.movementManager.get().setAction(action);
        }
    }


    
   

}
