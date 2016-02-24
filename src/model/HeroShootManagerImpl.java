package model;

public class HeroShootManagerImpl extends MonsterShootManager implements HeroShootManager {

    private boolean wannaShoot = false;

    public HeroShootManagerImpl(final int minTime, final int bulletDamage, final MovementTypes movementType,
            final int offset, final int speed) {
        super(minTime, bulletDamage, movementType, offset, speed);
    }

    @Override
    public void wannaShoot() {
        this.wannaShoot = true;
    }

    @Override
    public boolean isOnShoot() {
        return this.wannaShoot ? super.isOnShoot() : false;
    }

    @Override
    public boolean haveShooted() {
        wannaShoot = super.haveShooted() ? false : wannaShoot;
        return super.haveShooted();
    }

}
