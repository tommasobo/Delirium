package model;

import control.Entities;
import control.Point;
import control.Position;

public class HeroeImpl implements Heroe {
    
    private final Entities name;
    private int life;
    private Position position;
    
    public HeroeImpl(final Entities name, final int life, final Position position) {
        this.name = name;
        this.life = life;
        this.position = position;
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
    public Entities getName() {
        return this.name;
    }
    
    
    
    

}
