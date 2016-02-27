package control.game.thread;

/**
 * Interface that declare methods to interact with the game thread
 * @author Matteo Magnani
 *
 */
public interface GameThread{

    /**
     * Pause the game
     */
    void pause();

    /**
     * re-start the game from a pause state
     */
    void reStart();
    
    /**
     * Starts the game loop
     */
    void start();
    
    /**
     * Stop the game loop
     */
    void stopGame();
    
    //TODO non so come dirlo
    /**
     * 
     * @return A boolean 
     */
    boolean isRunning();
    
    /**
     * 
     * @return A boolean
     */
    boolean isPaused();
    
    /**
     * 
     * @return The actual game state
     */
    GameState getGameState();
    
    /**
     * Set the game state to finish
     * @throws IllegalStateException if the game is running
     */
    void setGameEnd();

}