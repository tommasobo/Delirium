package model;

import java.util.Optional;

import control.Position;

public class StaticOthers {
    
    private final int life;
    private final LifeManager lifemanager;
    private final Optional<Integer> contactDamage;
    private final Position position;
    
    public StaticOthers(int life, LifeManager lifemanager, Optional<Integer> contactDamage, Position position) {
        this.life = life;
        this.lifemanager = lifemanager;
        this.contactDamage = contactDamage;
        this.position = position;
    }

    public int getLife() {
        return life;
    }

    public LifeManager getLifemanager() {
        return lifemanager;
    }

    public Optional<Integer> getContactDamage() {
        return contactDamage;
    }

    public Position getPosition() {
        return position;
    }

}
