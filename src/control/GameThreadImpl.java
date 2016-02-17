package control;

import java.util.List;
import java.util.Optional;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import model.Actions;
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
	private final CodesIterator codeIterator;
	
	public GameThreadImpl(final Model model, final ViewController view, EntitiesDatabase database, InputManager inputManager, CodesIterator codeIterator) {
		this.model = model;
		this.view = view;
		this.database = database;
		this.inputManager = inputManager;
		this.mutex = new Mutex();
		this.running = true;
		this.codeIterator = codeIterator;
	}

    public void run() {
		while(this.running) {
			
		    this.mutex.lock();
		    Pair<model.Actions, Optional<model.Directions>> action = inputManager.getNextPGAction();
		    if(action.getY().isPresent()) {
		        this.model.notifyEvent(action.getY().get());
		    }
		    this.model.notifyEvent(action.getX());
			List<EntitiesInfo> bullets = this.model.updateArena();
			for(EntitiesInfo ent : bullets) {
			    Integer tmp = this.codeIterator.next();
			    ent.setCode(tmp);
			    database.putEntity(tmp, Entities.PLATFORM);
			}
			this.model.putBullet(bullets);
			this.model.getState().stream().filter(e -> e.getAction() == Actions.SHOOT).forEach(t -> System.out.println("aaaaaaaa"));
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
	    mutex.lock();
	}
	
    public void reStart() {
	    mutex.unlock();
	}
    
    public void stopGame() {
        this.running = false;
    }
	
	/*private class Lock{

	    private boolean isLocked = false;

	    public synchronized void lock()
	    throws InterruptedException{
	      while(isLocked){
	        wait();
	      }
	      isLocked = true;
	    }

	    public synchronized void unlock(){
	      isLocked = false;
	      notify();
	    }
	  }*/
}
