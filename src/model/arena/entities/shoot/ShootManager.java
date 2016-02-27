package model.arena.entities.shoot;

import java.util.Optional;

import model.arena.entities.Position;
import model.transfertentities.EntitiesInfo;
import utility.Dimension;

/**
 * Another example of Strategy Pattern. The implementation of this interface
 * determinate the shoot of the entity.
 * 
 * @author josephgiovanelli
 *
 */
public interface ShootManager {

    /**
     * The default distance of the bullet from the entity shooter.
     */
    int BULLET_OFFSET = 10;

    /**
     * The default dimension of the bullet.
     */
    Dimension BULLET_DIMENSION = new Dimension(20, 20);

    /**
     * Get if the entity is shooting or not.
     * 
     * @return : if is shooting.
     */
    boolean isOnShoot();

    /**
     * If the entity can shoot, then shoot.
     * @param code : the father code.
     * @param position : the initial position of the bullet.
     * @return : the bullet in order to added it into the arena.
     */
    Optional<EntitiesInfo> getBullet(final int code, final Position position);

    /**
     * Get if the entity has shouted or not.
     * @return if has shouted.
     */
    boolean haveShooted();

}
