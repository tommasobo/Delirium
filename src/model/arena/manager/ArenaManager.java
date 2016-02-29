package model.arena.manager;

/**
 * 
 * @author Matteo Magnani
 *
 */
public interface ArenaManager {
    
    /**
     * Move all entities of arena.
     */
    void moveEntities();
    /**
     * 
     * @return if game is won
     */
    boolean isGameWon();

}