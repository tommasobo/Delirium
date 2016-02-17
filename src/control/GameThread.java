package control;

import java.util.Optional;
import java.util.Queue;

import model.Actions;
import model.Model;
import view.ViewController;

public class GameThread extends Thread {
	
	private final Model model;
	private final ViewController view;
	private final EntitiesDatabase database;
	private final InputManager inputManager;
	
	public GameThread(final Model model, final ViewController view, EntitiesDatabase database, InputManager inputManager) {
		this.model = model;
		this.view = view;
		this.database = database;
		this.inputManager = inputManager;
	}
	public void run() {
		while(true) {
			
		    Pair<model.Actions, Optional<model.Directions>> action = inputManager.getNextPGAction();
		    if(action.getY().isPresent()) {
		        this.model.notifyEvent(action.getY().get());
		    }
		    this.model.notifyEvent(action.getX());
			this.model.updateArena();
			this.view.updateScene(Translator.mapFromModelToView(this.model.getState(), database));
			try {
				Thread.sleep(28L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
