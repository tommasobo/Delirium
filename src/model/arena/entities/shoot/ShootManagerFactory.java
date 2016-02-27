package model.arena.entities.shoot;

import java.util.Optional;

import model.transfertentities.ShootInfo;

/**
 * This module is an example of Factory Pattern.
 * 
 * @author josephgiovanelli
 *
 */
public class ShootManagerFactory {

    /**
     * This method based on a filter return the right shoot manager.
     * 
     * @param shootInfo
     *            : the information of the firing entity.
     * @return : the right shoot manager.
     */
    public static Optional<ShootManager> getShootManager(final Optional<ShootInfo> shootInfo) {

        if (shootInfo.isPresent()) {
            ShootManager shootManager;
            switch (shootInfo.get().getShootType()) {
            case HERO:
                shootManager = new HeroShootManagerImpl(shootInfo.get().getMinTime(), shootInfo.get().getBulletDamage(),
                        shootInfo.get().getMovementType(), shootInfo.get().getRange(), shootInfo.get().getSpeed());
                break;
            case MONSTER:
                shootManager = new MonsterShootManager(shootInfo.get().getMinTime(), shootInfo.get().getBulletDamage(),
                        shootInfo.get().getMovementType(), shootInfo.get().getRange(), shootInfo.get().getSpeed());
                break;
            default:
                throw new IllegalArgumentException("Entities cannot have null shootTypes");
            }
            return Optional.of(shootManager);
        } else {
            return Optional.empty();
        }
    }

}
