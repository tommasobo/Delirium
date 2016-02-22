package model;

import java.util.Optional;

public class Hero extends EntitiesImpl {

    //TODO cambiare costruttore levando optional
    public Hero(int code, LifeManager lifeManager, MovementManager movementManager,
            ShootManager shootManager, Integer contactDamage) {
        super(code, lifeManager, movementManager, Optional.of(shootManager), Optional.of(contactDamage));
    }

    @Override
    public void setAction(Actions action) {
        if (action != Actions.SHOOT) {
            super.getMovementManager().get().setAction(action);
        } else {
            super.getMovementManager().get().setAction(Actions.STOP);
            HeroShootManager heroShootManager = (HeroShootManager) getShootManager().get();
            heroShootManager.wannaShoot();
        }
    }
    
    public void setOnPlatform(boolean bool) {
        HeroMovementManager move = (HeroMovementManager) this.getMovementManager().get();
        move.setOnPlatform(bool);
    }

}
