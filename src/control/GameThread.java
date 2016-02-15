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
	private final Queue<Pair<model.Actions, Optional<model.Directions>>> actions;
	
	public GameThread(final Model model, final ViewController view, EntitiesDatabase database, Queue<Pair<model.Actions, Optional<model.Directions>>> actions) {
		this.model = model;
		this.view = view;
		this.database = database;
		this.actions = actions;
	}
	public void run() {
		while(true) {
			if(!this.actions.isEmpty()) {
				Pair<model.Actions, Optional<model.Directions>> action = this.actions.peek();
			
				if(action.getY().isPresent()) {
					this.model.notifyEvent(action.getY().get());
				}
				this.model.notifyEvent(action.getX());
				this.actions.add(this.actions.poll());
			} else {
				this.model.notifyEvent(Actions.STOP);
			}
			
			
			this.model.updateArena();
			this.view.updateScene(Translator.mapFromModelToView(this.model.getState(), database));
			try {
				Thread.sleep(30L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
