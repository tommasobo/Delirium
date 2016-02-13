package model;

import control.Dimension;
import control.Point;

public class EntitiesImpl implements Entities {
    
    private final int code;
    private int life;
    private final LifeManager lifeManager;
    private final MovementManager movementManager;
    private int contactDamage;

    public EntitiesImpl(int code, int life, LifeManager lifeManager, MovementManager movementManager, int contactDamage) {
        this.code = code;
        this.life = life;
        this.lifeManager = lifeManager;
        this.movementManager = movementManager;
        this.contactDamage = contactDamage;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public LifeManager getLifeManager() {
        return this.lifeManager;
    }

    @Override
    public Position getPosition() {
        return this.movementManager.getPosition();
    }

    @Override
    public void setPosition(final Point point, final Directions direction) {
        this.movementManager.setPosition(point, direction);
    }

    @Override
    public Point getPoint() {
        return this.movementManager.getPosition().getPoint();
    }

    @Override
    public void setPoint(Point point) {
        this.movementManager.setPosition(point, this.getDirection());
    }

    @Override
    public Dimension getDimension() {
        return this.movementManager.getPosition().getDimension();
    }

    @Override
    public Directions getDirection() {
        return this.movementManager.getPosition().getDirection();
    }

    @Override
    public void setDirection(Directions direction) {
        this.movementManager.setPosition(this.getPoint(), direction);
    }

    @Override
    public int getContactDamage() {
        return this.contactDamage;
    }

    @Override
    public void setContactDamage(int contactDamage) {
        this.contactDamage = contactDamage;
    }

    @Override
    public Position getNextMove() {
        return this.movementManager.getNextMove();
    }

    @Override
    public int getSpeed() {
        return this.movementManager.getSpeed();
    }

    @Override
    public void setSpeed(int speed) {
        this.movementManager.setSpeed(speed);
        
    }
    
    public Bounds getBounds() {
        return this.movementManager.getBounds();
    }
    
    public boolean isCanFly() {
        return this.movementManager.isCanFly();
    }

   

}
