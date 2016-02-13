package model;

import java.util.Optional;

public class EntitiesInfoImpl implements EntitiesInfo {
    
    private final int code;
    private final int life;
    private final LifeManager lifemanager;
    
    private final MovementTypes movementTypes;
    private final Position position;
    private final Bounds bounds;
    private final int speed;
    private final boolean canFly;
    
    private final int contactDamage;

    public EntitiesInfoImpl(int code, int life, LifeManager lifemanager, MovementTypes movementTypes, Position position,
            Bounds bounds, int speed, boolean canFly, int contactDamage) {
        this.code = code;
        this.life = life;
        this.lifemanager = lifemanager;
        this.movementTypes = movementTypes;
        this.position = position;
        this.bounds = bounds;
        this.speed = speed;
        this.canFly = canFly;
        this.contactDamage = contactDamage;
    }

    /* (non-Javadoc)
     * @see model.EntitiesInfo#getCode()
     */
    @Override
    public int getCode() {
        return code;
    }

    /* (non-Javadoc)
     * @see model.EntitiesInfo#getLife()
     */
    @Override
    public int getLife() {
        return life;
    }

    /* (non-Javadoc)
     * @see model.EntitiesInfo#getLifemanager()
     */
    @Override
    public LifeManager getLifemanager() {
        return lifemanager;
    }

    /* (non-Javadoc)
     * @see model.EntitiesInfo#getMovementTypes()
     */
    @Override
    public MovementTypes getMovementTypes() {
        return movementTypes;
    }

    /* (non-Javadoc)
     * @see model.EntitiesInfo#getPosition()
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /* (non-Javadoc)
     * @see model.EntitiesInfo#getBounds()
     */
    @Override
    public Bounds getBounds() {
        return bounds;
    }

    /* (non-Javadoc)
     * @see model.EntitiesInfo#getSpeed()
     */
    @Override
    public int getSpeed() {
        return speed;
    }

    /* (non-Javadoc)
     * @see model.EntitiesInfo#isCanFly()
     */
    @Override
    public boolean isCanFly() {
        return canFly;
    }

    /* (non-Javadoc)
     * @see model.EntitiesInfo#getContactDamage()
     */
    @Override
    public int getContactDamage() {
        return contactDamage;
    }
    
}
