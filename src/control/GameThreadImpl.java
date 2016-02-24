package control;

import java.util.List;
import java.util.Optional;

import model.EntitiesInfo;
import model.EntitiesInfoToControl;
import model.Model;
import sun.awt.Mutex;
import utility.Pair;
import view.Notifications;

public class GameThreadImpl extends Thread implements GameThread {

    private final Model model;
    private final ViewDecorator view;
    private final EntitiesDatabase database;
    private final InputManager inputManager;
    private final Mutex mutex;
    volatile private boolean running;
    //TODO metti in lock le parti di lettura/scrittura
    private GameState gameState;
    private final Object stateLock;

    public GameThreadImpl(final Model model, final ViewDecorator view, EntitiesDatabase database,
            InputManager inputManager) {
        this.model = model;
        this.view = view;
        this.database = database;
        this.inputManager = inputManager;
        this.mutex = new Mutex();
        this.running = true;
        this.gameState = GameState.INGAME;
        this.stateLock = new Object();
    }

    public void run() {
        while (this.running) {

            this.mutex.lock();
            
            //notifico l'input al model
            Pair<model.Actions, Optional<model.Directions>> action = inputManager.getNextPGAction();
            if (action.getY().isPresent()) {
                this.model.notifyEvent(action.getY().get());
            }
            this.model.notifyEvent(action.getX());
            
            List<EntitiesInfo> bullets = this.model.updateArena();
            
            bullets = database.putBulletsAndSetCodes(bullets);
            this.model.putBullet(bullets);
            
            this.view.updateScene(Translator.entitiesListFromModelToView(controlGameState(this.model.getState()), database));
            this.mutex.unlock();
            try {
                Thread.sleep(28L);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        synchronized(this.stateLock) {
            //TODO aggiungi eccezione thread killato di cattiveria
            if(this.gameState == GameState.WON) {
                //TODO metti notifyEvent synchronized
                this.view.notifySceneEvent(Notifications.WIN);
            } else if (this.gameState == GameState.LOSE) {
                this.view.notifySceneEvent(Notifications.LOSE);
            }
        }
    }

    public void pause() {
        mutex.lock();
        synchronized(this.stateLock) {
            this.gameState = GameState.PAUSED;
        }
    }

    public void reStart() {
        synchronized(this.stateLock) {
            this.gameState = GameState.INGAME;
        }
            mutex.unlock();
    }
    
    public boolean isPaused() {
        synchronized(this.stateLock) {
            return this.gameState == GameState.PAUSED;
        }
    }

    public void stopGame() {
        this.running = false;
    }
    
    public GameState getGameState() {
        synchronized(this.stateLock) {
            return this.gameState;
        }
    }
    
    private List<EntitiesInfoToControl> controlGameState(List<EntitiesInfoToControl> list) {
        synchronized(this.stateLock) {
            if(list.size() == 1 && list.get(0).getCode() == 0) {
                //TODO unifica variabili nell'enum mettendo il campo synchronzed
                this.running = false;
                this.gameState = GameState.LOSE;
            }
            //TODO -1 magic number
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
            //TODO non permettere se è running
            this.gameState = GameState.FINISH;
        }
    }
}
