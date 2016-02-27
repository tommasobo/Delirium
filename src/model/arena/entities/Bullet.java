package model.arena.entities;

import java.util.Optional;

import model.arena.entities.life.LifeManagerImpl;
import model.arena.entities.life.LifePattern;
import model.arena.entities.movement.MovementManager;

/**
 * This son of the Entity define a bullet that cannot shoot and have to hurt at
 * the contact damage. For this reasons a bullet have to recognize from the
 * other entities and manage in different way.
 * 
 * @author josephgiovanelli
 *
 */
public class Bullet extends EntitiesImpl {

    /**
     * This constructor accept only the value that needed to the bullet.
     * 
     * @param code
     *            : the code of bullet.
     * @param movementManager
     *            :how the bullet has to move.
     * @param contactDamage
     *            : how much hurt.
     */
    public Bullet(final int code, final MovementManager movementManager, final int contactDamage) {
        super(code, new LifeManagerImpl(1, LifePattern.WITH_LIFE), movementManager, Optional.empty(),
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
