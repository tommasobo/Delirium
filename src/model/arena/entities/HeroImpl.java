package model.arena.entities;

import java.util.Optional;

import model.arena.entities.life.LifeManager;
import model.arena.entities.movement.HeroMovementManager;
import model.arena.entities.movement.MovementManager;
import model.arena.entities.shoot.HeroShootManager;
import model.arena.entities.shoot.ShootManager;
import model.arena.utility.Actions;

/**
 * This is the instance of the hero and has a particular features that the
 * others haven't.
 * 
 * @author josephgiovanelli
 *
 */
public class HeroImpl extends EntitiesImpl implements Hero {

    /**
     * This constructor has any field mandatory.
     * 
     * @param code : the unique code of the hero.
     * @param lifeManager : the manager of the life.
     * @param movementManager : manager of the movement.
     * @param shootManager : the manager of the shoot.
     * @param contactDamage : how much he hurts at the contact.
     */
    public HeroImpl(final int code, final LifeManager lifeManager, final MovementManager movementManager,
            final ShootManager shootManager, final Integer contactDamage) {
        super(code, lifeManager, movementManager, Optional.of(shootManager), Optional.of(contactDamage));
    }

    @Override
    public void setAction(final Actions action) {
        final HeroMovementManager movement = (HeroMovementManager) this.getMovementManager().get();
        if (action == Actions.SHOOT) {
            final HeroShootManager heroShootManager = (HeroShootManager) super.getShootManager().get();
            heroShootManager.wannaShoot();
        } else if (action == Actions.BEND) {
            movement.bendPG(true);
        } else if (action == Actions.UNBEND) {
            movement.bendPG(false);
        } else {
            movement.setAction(action);
        }
    }
    
    @Override
    public Actions getAction() {
        final HeroMovementManager movement = (HeroMovementManager) this.getMovementManager().get();
        if(movement.isBended()) {
            return Actions.BEND;
        } else {
            return super.getAction();
        }
    }

    @Override
    public void setOnPlatform(final boolean bool) {
        final HeroMovementManager move = (HeroMovementManager) this.getMovementManager().get();
        move.setOnPlatform(bool);
    }

    @Override
    public void accept(final EntitiesVisitor visitor) {
        visitor.visit(this);
    }

}
