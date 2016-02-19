package model;


public class HeroShootManagerImpl extends ShootManagerImpl implements HeroShootManager {
    
    private boolean wannaShoot = false;
    
    public HeroShootManagerImpl(int minTime, int bulletDamage, MovementTypes movementType, int offset, int speed) {
        super(minTime, bulletDamage, movementType, offset, speed);
    }

    @Override
    public void wannaShoot() {
        this.wannaShoot = true;
    }

    @Override
    public boolean isOnShoot() {
        return this.wannaShoot? super.isOnShoot() : false;
    }

   

}
