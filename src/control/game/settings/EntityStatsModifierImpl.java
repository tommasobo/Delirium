package control.game.settings;

import java.util.Optional;

import control.fileloading.levels.storestructures.EntitiesInfoStore;
import control.fileloading.levels.storestructures.EntitiesInfoStoreImpl;
import control.fileloading.levels.storestructures.MovementInfoStore;
import control.fileloading.levels.storestructures.ShootInfoStore;

/**
 * The class modify entities according to the game difficulty passed on the constructor
 * @author magna
 *
 */
public class EntityStatsModifierImpl implements EntityStatsModifier{
    private final GameDifficulty gameDifficulty;
    
    /**
     * 
     * @param gameDifficulty The game difficulty
     */
    public EntityStatsModifierImpl(final GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }
    
    public EntitiesInfoStore modifyEntity(EntitiesInfoStore entity) {
        final EntitiesInfoStore ent = new EntitiesInfoStoreImpl(entity);
        ent.setLife(this.gameDifficulty.getLifeIncrementer().apply(ent.getLife()));
        if(ent.getMovementInfoStore().isPresent()) {
            final MovementInfoStore mi = ent.getMovementInfoStore().get();
            mi.setSpeed(this.gameDifficulty.getSpeedIncrementer().apply(mi.getSpeed()));
        }
        if(ent.getShootInfoStore().isPresent()) {
            final ShootInfoStore si = ent.getShootInfoStore().get();
            si.setSpeed(this.gameDifficulty.getLifeIncrementer().apply(si.getSpeed()));
            si.setBulletDamage(this.gameDifficulty.getDamageIncrementer().apply(si.getBulletDamage()));
        }
        if(ent.getShootInfoStore().isPresent()) {
            final ShootInfoStore si = ent.getShootInfoStore().get();
            si.setSpeed(this.gameDifficulty.getSpeedIncrementer().apply(si.getSpeed()));
            si.setBulletDamage(this.gameDifficulty.getDamageIncrementer().apply(si.getBulletDamage()));
        }
        if(ent.getContactDamage().isPresent()) {
            ent.setContactDamage(Optional.of(this.gameDifficulty.getLifeIncrementer().apply(ent.getContactDamage().get())));
        }
        return ent;
    }
}
