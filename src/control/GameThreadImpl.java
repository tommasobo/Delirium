package control;

import java.util.List;
import java.util.Optional;

import model.EntitiesInfo;
import model.EntitiesInfoToControl;
import model.Model;
import sun.awt.Mutex;
import utility.Pair;
import view.Notifications;
import view.SceneType;
import view.ViewController;

public class GameThreadImpl extends Thread implements GameThread {

    private final Model model;
    private final ViewDecorator view;
    private final EntitiesDatabase database;
    private final InputManager inputManager;
    private final Mutex mutex;
    volatile private boolean running;
    volatile private boolean paused;
    volatile private GameState gameState;

    public GameThreadImpl(final Model model, final ViewDecorator view, EntitiesDatabase database,
            InputManager inputManager) {
        this.model = model;
        this.view = view;
        this.database = database;
        this.inputManager = inputManager;
        this.mutex = new Mutex();
        this.running = true;
        this.gameState = GameState.INGAME;
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
        
        //TODO aggiungi eccezione thread killato di cattiveria
        if(this.gameState == GameState.WON) {
            //TODO metti notifyEvent synchronized
            this.view.notifySceneEvent(Notifications.WIN);
        } else if (this.gameState == GameState.LOSE) {
            this.view.notifySceneEvent(Notifications.LOSE);
        }
    }

    public void pause() {
        this.paused = true;;
        mutex.lock();
    }

    public void reStart() {
        this.paused = false;;
        mutex.unlock();
    }
    
    public boolean isPaused() {
        return this.paused;
    }

    public void stopGame() {
        this.running = false;
    }
    
    public GameState getGameState() {
        switch(this.gameState){
        /*case LOSE:
            this.gameState = GameState.FINISH;
            return GameState.LOSE;
        case WON:
            this.gameState = GameState.FINISH;
            return GameState.WON;*/
        default:
            return this.gameState;
        }
        
    }
    
    private List<EntitiesInfoToControl> controlGameState(List<EntitiesInfoToControl> list) {
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
        
        return list;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }
}
