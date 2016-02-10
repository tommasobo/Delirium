package model;

import control.Point;

public class HeroImpl implements Hero {
    
    private final int code;
    private int life;
    private final ModelPosition position;
    private final int speed;
    
    public HeroImpl(final int code, final int life, final ModelPosition position, final int speed) {
        this.code = code;
        this.life = life;
        this.position = position;
        this.speed = speed;
    }
    
    @Override
    public int getLife() {
        return this.life;
    }

    
    @Override
    public void setLife(final int life) {
        this.life = life;
    }

    
    @Override
    public ModelPosition getPosition() {
        return this.position.getPosition();
    }
    
    

    
    @Override
    public void setPosition(final Point point, final ModelDirections direction) {
        this.position.setPoint(point);
        this.position.setDirection(direction);
    }

    
    @Override
    public int getCode() {
        return this.code;
    }
    
    @Override
    public int getSpeed() {
        return this.speed;
    }

    
    
    

}
