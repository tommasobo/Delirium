package model.transfertentities;

/**
 * This enumeration specified the pattern of Strategy to use.
 * 
 * @author josephgiovanelli
 *
 */
public enum ShootTypes {
    
    /**
     * The shootManager has to implement also the @HeroShootManager interfaces.
     */
    HERO,
    /**
     * The shootManager is a simple monster type.
     */
    MONSTER;
}
