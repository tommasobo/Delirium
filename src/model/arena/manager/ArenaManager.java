package model.arena.manager;

public interface ArenaManager {
    
    /**
     * Move all entities of arena
     */
    void moveEntities();
    /**
     * 
     * @return if game is won
     */
    boolean isGameWon();

}