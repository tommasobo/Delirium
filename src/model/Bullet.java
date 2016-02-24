package model;

import java.util.Optional;

public class Bullet extends EntitiesImpl {

    public Bullet(final int code, final MovementManager movementManager, final int contactDamage) {
        super(code, new LifeManager(1, LifePattern.WITH_LIFE), movementManager, Optional.empty(),
                Optional.of(contactDamage));
    }

    @Override
    public Optional<Integer> getContactDamage() {

        if (super.getLifeManager().getLife() > 0) {
            super.getLifeManager().setLife(1);
            return super.getContactDamage();
        } else {
            return Optional.empty();
        }

    }

    @Override
    public void accept(final EntitiesVisitor visitor) {
        visitor.visit(this);
    }

}
