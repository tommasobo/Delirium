package model;

import java.util.Optional;


public class DinamicOthers extends StaticOthers {
    
    private final Bounds bounds;

    public DinamicOthers(int life, LifeManager lifemanager, Optional<Integer> contactDamage, ModelPosition position, Bounds bounds) {
        super(life, lifemanager, contactDamage, position);
        this.bounds = bounds;
    }

    public Bounds getBounds() {
        return bounds;
    }

}
