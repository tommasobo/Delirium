package model;

import control.Point;
import control.PhisicalProprieties;
import control.Dimension;

public interface Hero {
    
    public static final int MAX_LIFE = 30;
    public static final ModelPosition INITIAL_POSITION = new ModelPosition(new PhisicalProprieties(new Point(20, 20), new Dimension(40, 60), 10), ModelDirections.RIGHT);
    public static final int INITIAL_SPEED = 10;
    public static final int STANDARD_CODE = 0;
    
    int getLife();

    void setLife(int life);

    ModelPosition getPosition();

    void setPosition(Point point, ModelDirections direction);

    int getCode();

    int getSpeed();
}