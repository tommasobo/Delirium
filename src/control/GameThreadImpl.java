package control;

import java.util.List;
import java.util.Optional;

import model.Model;
import model.transfertentities.EntitiesInfo;
import model.transfertentities.EntitiesInfoToControl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import utility.Pair;
import view.configs.Notifications;

/**
 * That class contains the structure of the periodic game loop, when this thread
 * starts, the game begin
 * 
 * @author magna
 *
 */
public class GameThreadImpl extends Thread implements GameThread {

    private final Model model;
    private final ViewDecorator view;
    private final EntitiesDatabase database;
    private final GameWorldTranslator translator;
    private final InputManager inputManager;
    private final Lock mutex;
    volatile private boolean running;
    private GameState gameState;
    private final Object stateLock;

    /**
     * 
     * @param model The model
     * @param view The view
     * @param database The database of view entities
     * @param inputManager The input manager in order to receive view inputs 
     */
    public GameThreadImpl(final Model model, final ViewDecorator view, final EntitiesDatabase database,
            final InputManager inputManager) {
        this.model = model;
        this.view = view;
        this.database = database;
        this.translator = new GameWorldTranslatorImpl(database, view.getScreenMultiplierFactor());
        this.inputManager = inputManager;
        this.mutex = new ReentrantLock(true);
        this.stateLock = new Object();
    }

    /**
     * The game loop periodically notify the model to update the game world, put
     * eventually fired bullets in the view database and put them in game, take
     * the state of the world from the model and communicate it to the view.
     * When the game is over notifies to view the game end.
     */
    public void run() {
        this.running = true;
        this.gameState = GameState.INGAME;
        while (this.running) {
            final Pair<model.arena.utility.Actions, Optional<model.arena.utility.Directions>> action = inputManager.getNextPGAction();
            if (action.getY().isPresent()) {
                this.model.notifyEvent(action.getY().get());
            }
            this.model.notifyEvent(action.getX());
            
            List<EntitiesInfo> bullets = this.model.updateArena();
            
            bullets = database.putBulletsAndSetCodes(bullets);
            this.model.putBullet(bullets);
            
            this.view.updateScene(translator.entitiesListFromModelToView(controlGameState(this.model.getState())));
            
            try {
                Thread.sleep(28L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.mutex.lock();
            this.mutex.unlock();
        }
        
        synchronized(this.stateLock) {
            if(this.gameState == GameState.WON) {
                this.view.notifySceneEvent(Notifications.WIN);
            } else if (this.gameState == GameState.LOSE) {
                this.view.notifySceneEvent(Notifications.LOSE);
            }
        }
    }

    @Override
    public void pause() {
        mutex.lock();
        synchronized(this.stateLock) {
            this.gameState = GameState.PAUSED;
        }
    }

    @Override
    public void reStart() {
        synchronized(this.stateLock) {
            this.gameState = GameState.INGAME;
        }
        mutex.unlock();
    }
    
    @Override
    public boolean isPaused() {
        synchronized(this.stateLock) {
            return this.gameState == GameState.PAUSED;
        }
    }

    @Override
    public void stopGame() {
        this.running = false;
    }
    
    @Override
    public GameState getGameState() {
        synchronized(this.stateLock) {
            return this.gameState;
        }
    }
    
    /**
     * The method check the game state and eventually stop it and set the state of WON or LOSE
     * @param list The list of entities in game
     * @return The list of entities in game
     */
    private List<EntitiesInfoToControl> controlGameState(final List<EntitiesInfoToControl> list) {
        synchronized(this.stateLock) {
            if(list.size() == 1 && list.get(0).getCode() == 0) {
                this.running = false;
                this.gameState = GameState.LOSE;
            }
            if(list.size() == 1 && list.get(0).getCode() == -1) {
                this.running = false;
                this.gameState = GameState.WON;
            }
        }
        
        return list;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void setGameEnd() {
        synchronized(this.stateLock) {
            if(!this.running) {
                this.gameState = GameState.FINISH;
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
