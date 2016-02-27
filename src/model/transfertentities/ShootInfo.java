package model.transfertentities;

import model.arena.utility.MovementTypes;

/**
 * This interface specified the information of the shoot manager that have to
 * instance.
 * 
 * @author josephgiovanelli
 *
 */
public interface ShootInfo {

    /**
     * Get a value that means how often fires the entity.
     * 
     * @return : the value that you want to know.
     */
    int getMinTime();

    /**
     * Get the type of the shoot. Hero or Monster.
     * 
     * @return : the value that you want to know.
     */
    ShootTypes getShootType();

    /**
     * Get the damage of the bullet.
     * 
     * @return : the value that you want to know.
     */
    int getBulletDamage();

    /**
     * Get the types of movement of the bullet.
     * 
     * @return : the value that you want to know.
     */
    MovementTypes getMovementType();

    /**
     * Get established limits offset of the bullet.
     * 
     * @return : the value that you want to know.
     */
    int getRange();

    /**
     * Get the speed of the bullet.
     * 
     * @return : the value that you want to know.
     */
    int getSpeed();

}