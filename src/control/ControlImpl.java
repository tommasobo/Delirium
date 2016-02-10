package control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.Bounds;
import model.DinamicOthers;
import model.Heroes;
import model.LifeManager;
import model.Model;
import model.ModelDirections;
import model.ModelImpl;
import model.ModelPosition;
import model.StaticOthers;
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
			return;
		}
		this.model.notifyEvent(Translator.tranViewEvents(event));
	}
	
	public List<Buttons> getButtons() {
		List<Buttons> list = new LinkedList<>();
		list.add(Buttons.NEWGAME);
		list.add(Buttons.EXIT);
		return list;
	}
	
	private void gameLoop(ViewEvents level) {
		//codice lettura file e put dei mostri, creazione database
		EntitiesDatabase database = new EntitiesDatabaseImpl();
		database.putEntity(0, Entities.JOYHERO);
		CodesIterator codIterator = new CodesIteratorImpl(); 
		
		Map<Integer, StaticOthers> stati = new HashMap<>();
		
		Integer tmp = codIterator.next();
		stati.put(tmp, new StaticOthers(10, LifeManager.WITH_LIFE, Optional.of(0), new ModelPosition(new Position(new Point(900, 150), new Dimension(40, 60)), ModelDirections.RIGHT)));
		database.putEntity(tmp, Entities.MONSTER1);
		
		tmp = codIterator.next();
		stati.put(tmp, new StaticOthers(10, LifeManager.WITH_LIFE, Optional.of(0), new ModelPosition(new Position(new Point(100, 150), new Dimension(40, 60)), ModelDirections.RIGHT)));
		database.putEntity(tmp, Entities.MONSTER1);
		
		tmp = codIterator.next();
		stati.put(tmp, new StaticOthers(10, LifeManager.WITH_LIFE, Optional.of(0), new ModelPosition(new Position(new Point(300, 300), new Dimension(40, 60)), ModelDirections.RIGHT)));
		database.putEntity(tmp, Entities.MONSTER1);
		
		Map<Integer, DinamicOthers> din = new HashMap<>();
		
		tmp = codIterator.next();
		din.put(tmp, new DinamicOthers(10, LifeManager.WITH_LIFE, Optional.of(0), new ModelPosition(new Position(new Point(100, 150), new Dimension(40, 60)), ModelDirections.RIGHT), 10, new Bounds(0, 1000, 0, 300)));
		database.putEntity(tmp, Entities.MONSTER1);
		
		tmp = codIterator.next();
		din.put(tmp, new DinamicOthers(10, LifeManager.WITH_LIFE, Optional.of(0), new ModelPosition(new Position(new Point(200, 150), new Dimension(40, 60)), ModelDirections.NONE), 10, new Bounds(0, 1000, 0, 300)));
		database.putEntity(tmp, Entities.MONSTER1);
		
		tmp = codIterator.next();
		din.put(tmp, new DinamicOthers(10, LifeManager.WITH_LIFE, Optional.of(0), new ModelPosition(new Position(new Point(100, 300), new Dimension(40, 60)), ModelDirections.UP), 10, new Bounds(0, 1000, 0, 300)));
		database.putEntity(tmp, Entities.MONSTER1);
		
		tmp = codIterator.next();
		din.put(tmp, new DinamicOthers(10, LifeManager.WITH_LIFE, Optional.of(0), new ModelPosition(new Position(new Point(200, 50), new Dimension(40, 60)), ModelDirections.NONE), 10, new Bounds(150, 250, 0, 150)));
		database.putEntity(tmp, Entities.MONSTER1);
		
		Dimension arenaDim = new Dimension(1000, 300);
		
		this.model.createArena(Heroes.JOY, stati, din, arenaDim);
		database.putArenaDimension(arenaDim);
		
		this.view.changeScene(new Pair<SceneType, Dimension>(SceneType.DRAWABLE, new Dimension(1000, 300)));
		
		Thread t = new GameThread(this.model, this.view, database);
		t.start();
		
		
	}

}
