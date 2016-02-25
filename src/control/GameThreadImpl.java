package control;

import java.util.List;
import java.util.Optional;

import model.EntitiesInfo;
import model.EntitiesInfoToControl;
import model.Model;import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import utility.Pair;
import view.Notifications;

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

    public GameThreadImpl(final Model model, final ViewDecorator view, final EntitiesDatabase database,
            final InputManager inputManager) {
        this.model = model;
        this.view = view;
        this.database = database;
        this.translator = new GameWorldTranslatorImpl(database, view.getScreenMoltiplicatorFactor());
        this.inputManager = inputManager;
        this.mutex = new ReentrantLock(true);
        this.stateLock = new Object();
    }

    public void run() {
        this.running = true;
        this.gameState = GameState.INGAME;
        while (this.running) {
            final Pair<model.Actions, Optional<model.Directions>> action = inputManager.getNextPGAction();
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.mutex.lock();
            this.mutex.unlock();
        }
        
        synchronized(this.stateLock) {
            //TODO aggiungi eccezione thread killato di cattiveria
            if(this.gameState == GameState.WON) {
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
    
    private List<EntitiesInfoToControl> controlGameState(final List<EntitiesInfoToControl> list) {
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
