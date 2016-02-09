package control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Model;
import model.ModelImpl;
import model.PGActions;
import view.Entities;
import view.SceneType;
import view.ViewController;
import view.ViewControllerImpl;

public class ControlImpl implements Control {
	private final Model model;
	private ViewController view;
	
	public ControlImpl() {
		this.model = ModelImpl.getModel();
		this.view = ViewControllerImpl.getView();
		this.view.setListener(this);
		
	}
	
	public void startGame() {
		this.view.changeScene(new Pair<SceneType, Dimension>(SceneType.MENU, new Dimension(1000, 300)));
	}
	
	public void notifyEvent(ViewEvents event) {
		if(event.equals(ViewEvents.LEVEL1)) {
			gameLoop(ViewEvents.LEVEL1);
		} else if(event.equals(ViewEvents.MLEFT)) {
		    this.model.notifyEvent(PGActions.MLEFT);
		} else if(event.equals(ViewEvents.MRIGHT)) {
		    this.model.notifyEvent(PGActions.MRIGHT);
        } else if(event.equals(ViewEvents.JUMP)) {
            this.model.notifyEvent(PGActions.JUMP);
        }
		
	}
	
	public List<Buttons> getButtons() {
		List<Buttons> list = new LinkedList<>();
		list.add(Buttons.NEWGAME);
		return list;
	}
	
	private void gameLoop(ViewEvents level) {
		//codice lettura file e put dei mostri, creazione database
		EntitiesDatabase database = new EntitiesDatabaseImpl();
		CodesIterator codIterator = new CodesIteratorImpl(); 
		this.model.createArena(Entities.JOYHERO, new Dimension(1000, 300));
		
		this.view.changeScene(new Pair<SceneType, Dimension>(SceneType.DRAWABLE, new Dimension(1000, 300)));
		
		while(true) {
			this.model.updateArena();
			Map<Integer, Pair<Integer, Position>> modelMap = this.model.getState();
			Map<Integer, Pair<Entities, Pair<Integer, Position>>> viewMap = new HashMap<>();
			modelMap.entrySet().forEach(e -> {
				viewMap.put(e.getKey(), new Pair<Entities, Pair<Integer,Position>>(database.getViewEntity(e.getKey()), e.getValue()));
			});
			this.view.updateScene(viewMap);
		}
	}

}
