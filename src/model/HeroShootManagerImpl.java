package model;


public class HeroShootManagerImpl extends ShootManagerImpl implements HeroShootManager {
    
    private boolean wannaShoot = false;
    
    
    public HeroShootManagerImpl(int minTime) {
        super(minTime);
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
