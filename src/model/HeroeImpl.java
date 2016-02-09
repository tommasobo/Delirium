package model;

import control.Point;
import control.Position;

public class HeroeImpl implements Heroe {
    
    private final int code;
    private int life;
    private final Position position;
    private final int speed;
//work in progress: per gestire salto
//    private boolean isOnJump;
    
    public HeroeImpl(final int code, final int life, final Position position, final int speed) {
        this.code = code;
        this.life = life;
        this.position = position;
        this.speed = speed;
//work in progress: per gestire salto
//        this.isOnJump = false;
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
    public Position getPosition() {
        return this.position;
    }

    
    @Override
    public void setPosition(final Point point, final Position.Directions direction) {
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
