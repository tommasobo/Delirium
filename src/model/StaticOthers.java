package model;

import java.util.Optional;

public class StaticOthers {
    
    private final int life;
    private final LifeManager lifemanager;
    private final Optional<Integer> contactDamage;
    private final ModelPosition position;
    
    public StaticOthers(int life, LifeManager lifemanager, Optional<Integer> contactDamage, ModelPosition position) {
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

    public ModelPosition getPosition() {
        return position;
    }

}
