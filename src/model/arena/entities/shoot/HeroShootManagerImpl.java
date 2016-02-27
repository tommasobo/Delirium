package model.arena.entities.shoot;

import model.arena.utility.MovementTypes;

/**
 * This implementation is very similar at MonsterShoot because also this always
 * shoot every min time but the hero hs to shoot when he want to shoot.
 * 
 * @author josephgiovanelli
 *
 */
class HeroShootManagerImpl extends MonsterShootManager implements HeroShootManager {

    private boolean shoot;

    HeroShootManagerImpl(final int minTime, final int bulletDamage, final MovementTypes movementType,
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
