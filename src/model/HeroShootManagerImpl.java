package model;

import java.util.Optional;

import control.Dimension;
import control.Point;

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
    public boolean isOnShoot(boolean wannaShoot) {
        return this.wannaShoot? super.isOnShoot(wannaShoot) : false;
    }
    
    @Override
    public boolean haveShooted() {
        wannaShoot = super.haveShooted() ? false : wannaShoot;
        return super.haveShooted();
    }
    
    

   

}
