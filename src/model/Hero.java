package model;

import java.util.Optional;

public class Hero extends EntitiesImpl {

    public Hero(int code, int life, LifeManager lifeManager, MovementManager movementManager, ShootManager shootManager, int contactDamage) {
        super(code, life, lifeManager, movementManager, shootManager, contactDamage);
    }
    

    @Override
    public void setAction(Actions action) {
        if (action != Actions.SHOOT) {
            super.getMovementManager().setAction(action);
        } else {
            HeroShootManager heroShootManager = (HeroShootManager) getShootManager();
            heroShootManager.wannaShoot();
        }
    }

    @Override
    public Optional<EntitiesInfo> shoot() {
        return super.getShootManager().getBullet(this.getPosition());
    }

}
