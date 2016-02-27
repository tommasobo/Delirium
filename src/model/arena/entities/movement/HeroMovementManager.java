package model.arena.entities.movement;

public interface HeroMovementManager extends MovementManager {
    
    /**
     * 
     * @param bool A boolean that indicate if hero is on a platform
     */
    void setOnPlatform(boolean bool);

}