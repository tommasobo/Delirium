package model;

import java.util.Optional;

public class EntitiesInfo {
    
    private final int life;
    private final LifeManager lifemanager;
    private final Optional<Integer> contactDamage;
    private final ModelPosition position;
    private final boolean canFly;
    private final Bounds bounds;
    private final MovementTypes movementTypes;
    
    public EntitiesInfo(int life, LifeManager lifemanager, Optional<Integer> contactDamage, ModelPosition position, Bounds bounds, boolean canFly, MovementTypes movementTypes) {
        this.life = life;
        this.lifemanager = lifemanager;
        this.contactDamage = contactDamage;
        this.position = position;
        this.canFly = canFly;
        this.bounds = bounds;
        this.movementTypes = movementTypes;
    }

    public int getLife() {
        return life;
    }

    public LifeManager getLifemanager() {
        return lifemanager;
    }

    public Optional<Integer> getContactDamage() {
        return contactDamage;
    }

    public ModelPosition getPosition() {
        return position.getPosition();
    }
    
    public ModelDirections getDirection() {
        return this.position.getDirection();
    }

    public boolean isCanFly() {
        return canFly;
    }

    public Bounds getBounds() {
        return bounds;
    }
    
    public MovementTypes getMovementTypes() {
        return movementTypes;
    }

    
}
