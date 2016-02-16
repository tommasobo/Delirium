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
				if(action.getX() == Actions.JUMP && actions.stream().anyMatch(t -> t.getX() == Actions.MOVE)) {
				    Optional<Pair<model.Actions, Optional<model.Directions>>> op = actions.stream().filter(t -> t.getX() == Actions.MOVE).findFirst();
				    this.model.notifyEvent(Actions.MOVEONJUMP);
				    this.model.notifyEvent(op.get().getY().get());
				} else if(action.getX() == Actions.MOVE && actions.stream().anyMatch(t -> t.getX() == Actions.JUMP)) {
				    this.model.notifyEvent(Actions.MOVEONJUMP);
				} else {
				    this.model.notifyEvent(action.getX());
				}
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
