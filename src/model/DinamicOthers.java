package model;

import java.util.Optional;

import control.Position;

public class DinamicOthers extends StaticOthers {
    
    private final int speed;
    private final Bounds bounds;

    public DinamicOthers(int life, LifeManager lifemanager, Optional<Integer> contactDamage, Position position, int speed, Bounds bounds) {
        super(life, lifemanager, contactDamage, position);
        this.speed = speed;
        this.bounds = bounds;
    }

    public int getSpeed() {
        return speed;
    }

    public Bounds getBounds() {
        return bounds;
    }

}
