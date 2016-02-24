package model;

import java.util.Optional;

public class HeroImpl extends EntitiesImpl implements Hero {

    //TODO cambiare costruttore levando optional
    public HeroImpl(int code, LifeManager lifeManager, MovementManager movementManager,
            ShootManager shootManager, Integer contactDamage) {
        super(code, lifeManager, movementManager, Optional.of(shootManager), Optional.of(contactDamage));
    }

    @Override
    public void setAction(Actions action) {
        if (action != Actions.SHOOT) {
            super.getMovementManager().get().setAction(action);
        } else {
            HeroShootManager heroShootManager = (HeroShootManager) getShootManager().get();
            heroShootManager.wannaShoot();
        }
    }
    
    /* (non-Javadoc)
     * @see model.Hero#setOnPlatform(boolean)
     */
    @Override
    public void setOnPlatform(boolean bool) {
        HeroMovementManager move = (HeroMovementManager) this.getMovementManager().get();
        move.setOnPlatform(bool);
    }
    
    @Override
    public void accept(EntitiesVisitor visitor) {
        visitor.visit(this);
    }

}
