package model;

import java.util.Optional;

public class StaticOthers {
    
    private final int life;
    private final LifeManager lifemanager;
    private final Optional<Integer> contactDamage;
    private final ModelPosition position;
    private final boolean canFly;
    private final Bounds bounds;
    
    public StaticOthers(int life, LifeManager lifemanager, Optional<Integer> contactDamage, ModelPosition position, Bounds bounds, boolean canFly) {
        this.life = life;
        this.lifemanager = lifemanager;
        this.contactDamage = contactDamage;
        this.position = position;
        this.canFly = canFly;
        this.bounds = bounds;
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

    
}
