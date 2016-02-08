package model;

import control.Entities;
import control.Point;
import control.Position;

public class HeroeImpl implements Heroe {
    
    private final Entities name;
    private int life;
    private final Position position;
//work in progress: per gestire salto
//    private boolean isOnJump;
    
    public HeroeImpl(final Entities name, final int life, final Position position) {
        this.name = name;
        this.life = life;
        this.position = position;
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
//work in progress: per gestire salto
//        if (isOnJump && this.position.getPoint().getY() != point.getY()) {
//            this.position.setPoint(new Point(point.getX(), this.position.getPoint().getY()));
//        } else {
            this.position.setPoint(point);
//        }
        this.position.setDirection(direction);
    }

    
    @Override
    public Entities getName() {
        return this.name;
    }
    
//work in progress: per gestire salto    
//    public void onJump() {
//        this.isOnJump = true;
//    }
    
    
    
    

}
