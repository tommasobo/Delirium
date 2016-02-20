package control;

import java.util.List;
import java.util.Optional;

import model.EntitiesInfo;
import model.Model;
import sun.awt.Mutex;
import view.ViewController;
import view.Entities;

public class GameThreadImpl extends Thread implements GameThread {

    private final Model model;
    private final ViewController view;
    private final EntitiesDatabase database;
    private final InputManager inputManager;
    private final Mutex mutex;
    private boolean running;
    private boolean paused;

    public GameThreadImpl(final Model model, final ViewController view, EntitiesDatabase database,
            InputManager inputManager) {
        this.model = model;
        this.view = view;
        this.database = database;
        this.inputManager = inputManager;
        this.mutex = new Mutex();
        this.running = true;
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
            
            bullets = database.putEntitiesAndSetCodes(bullets, Entities.BULLET);
            this.model.putBullet(bullets);
            /*this.model.getState().stream().filter(t -> t.getCode() == 1).forEach(t -> {
                System.out.println(t.getPosition() + " " + t.getMovementInfo().get().getActions());
            });*/
            this.view.updateScene(Translator.mapFromModelToView(this.model.getState(), database));
            this.mutex.unlock();
            try {
                Thread.sleep(28L);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void pause() {
        this.paused = true;
        mutex.lock();
    }

    public void reStart() {
        this.paused = false;
        mutex.unlock();
    }
    
    public boolean isPaused() {
        return this.paused;
    }

    public void stopGame() {
        this.running = false;
    }
}
