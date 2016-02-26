package model.arena.entities.shoot;

import model.arena.utility.MovementTypes;

class HeroShootManagerImpl extends MonsterShootManager implements HeroShootManager {

    private boolean shoot;

    public HeroShootManagerImpl(final int minTime, final int bulletDamage, final MovementTypes movementType,
            final int offset, final int speed) {
        super(minTime, bulletDamage, movementType, offset, speed);
        this.shoot = false;
    }

    @Override
    public void wannaShoot() {
        this.shoot = true;
    }

    @Override
    public boolean isOnShoot() {
        return this.shoot ? super.isOnShoot() : false;
    }

    @Override
    public boolean haveShooted() {
        shoot = super.haveShooted() ? false : shoot;
        return super.haveShooted();
    }

}
