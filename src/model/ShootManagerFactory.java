package model;

import java.util.Optional;

public class ShootManagerFactory {
    
    public static Optional<ShootManager> getShootManager(final Optional<ShootInfoImpl> shootInfo) {
        
        if (!shootInfo.isPresent()) {
            return Optional.empty();
        } else {
            ShootManager shootManager;switch (shootInfo.get().getShootType()) {
            case HERO:
                shootManager = new HeroShootManagerImpl(shootInfo.get().getMinTime(), shootInfo.get().getBulletDamage(), shootInfo.get().getMovementType(), shootInfo.get().getRange(), shootInfo.get().getSpeed());
                break;
            case MONSTER:
                shootManager = new MonsterShootManager(shootInfo.get().getMinTime(), shootInfo.get().getBulletDamage(), shootInfo.get().getMovementType(), shootInfo.get().getRange(), shootInfo.get().getSpeed());
                break;
            default:
                throw new IllegalArgumentException();
            }
            return Optional.of(shootManager);
        }
    }

}
