package model;

import control.Point;
import control.Position;
import control.Dimension;

public interface Heroe {
    
    public static final int MAX_LIFE = 30;
    public static final Position INITIAL_POSITION = new Position(new Point(20, 20), Position.Directions.RIGHT, new Dimension(20, 50));
    public static final int INITIAL_SPEED = 1;
    
    int getLife();

    void setLife(int life);

    Position getPosition();

    void setPosition(Point point, Position.Directions direction);

    int getCode();

    int getSpeed();
    
//work in progress: per gestire salto    
//    void onJump();

}