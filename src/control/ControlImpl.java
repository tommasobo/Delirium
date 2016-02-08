package control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Model;
import model.ModelImpl;
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
			Map<Entities, List<Pair<Integer, Position>>> map = new HashMap<>();
			map.put(Entities.JOYHERO, new LinkedList<>());
			this.model.createArena(map, new Dimension(1000, 300));
			this.view.changeScene(new Pair<SceneType, Dimension>(SceneType.DRAWABLE, new Dimension(1000, 300)));
			Thread game = new GameThread(this.model, this.view);
			game.start();
		}
		
	}
	
	public List<Buttons> getButtons() {
		List list = new LinkedList<>();
		list.add(Buttons.NEWGAME);
		return list;
	}

}
