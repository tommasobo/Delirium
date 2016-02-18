package model;

import java.util.Optional;

public class Bullet extends EntitiesImpl {

    public Bullet(int code, MovementManager movementManager, int contactDamage) {
        super(code, new LifeManager(1, LifePattern.WITH_LIFE), movementManager, Optional.empty(), Optional.of(contactDamage));
    }
    
    @Override
    public Optional<Integer> getContactDamage() {
        super.getLifeManager().setLife(1);
        return super.getContactDamage();
    }

}
