package model;

import java.util.Optional;

public class HeroImpl extends EntitiesImpl implements Hero {

    public HeroImpl(final int code, final LifeManager lifeManager, final MovementManager movementManager,
            final ShootManager shootManager, final Integer contactDamage) {
        super(code, lifeManager, movementManager, Optional.of(shootManager), Optional.of(contactDamage));
    }

    @Override
    public void setAction(final Actions action) {
        if (action == Actions.SHOOT) {
            final HeroShootManager heroShootManager = (HeroShootManager) super.getShootManager().get();
            heroShootManager.wannaShoot();
        } else {
            super.getMovementManager().get().setAction(action);
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
