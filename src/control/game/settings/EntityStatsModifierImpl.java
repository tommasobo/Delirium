package control.game.settings;

import java.util.Optional;

import control.fileloading.levels.storestructures.EntitiesInfoStore;
import control.fileloading.levels.storestructures.EntitiesInfoStoreImpl;
import control.fileloading.levels.storestructures.ShootInfoStore;

/**
 * The class modifies entities according to the game difficulty passed on the
 * constructor.
 * 
 * @author Matteo Magnani
 *
 */
public class EntityStatsModifierImpl implements EntityStatsModifier {
    private final GameDifficulty gameDifficulty;

    /**
     * 
     * @param gameDifficulty
     *            The game difficulty
     */
    public EntityStatsModifierImpl(final GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    @Override
    public EntitiesInfoStore modifyEntity(final EntitiesInfoStore entity) {
        final EntitiesInfoStore ent = new EntitiesInfoStoreImpl(entity);
        ent.setLife(this.gameDifficulty.getLifeIncrementer().apply(ent.getLife()));
        if (ent.getShootInfoStore().isPresent()) {
            final ShootInfoStore si = ent.getShootInfoStore().get();
            si.setBulletDamage(this.gameDifficulty.getDamageIncrementer().apply(si.getBulletDamage()));
            si.setMinTime(this.gameDifficulty.getFireRatioIncrementer().apply(si.getMinTime()));
        }
        if (ent.getContactDamage().isPresent()) {
            ent.setContactDamage(
                    Optional.of(this.gameDifficulty.getLifeIncrementer().apply(ent.getContactDamage().get())));
        }
        return ent;
    }
}
