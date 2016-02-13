package control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.Bounds;
import model.LifeManager;
import model.Model;
import model.Actions;
import model.ModelImpl;
import model.Position;
import model.MovementTypes;
import model.EntitiesInfoImpl;
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
		CodesIterator codIterator = new CodesIteratorImpl(); 
		
		Map<Integer, EntitiesInfoImpl> stati = new HashMap<>();
		
		//aggiungo l'eroe
		database.putEntity(0, Entities.JOYHERO);
		stati.put(0, new EntitiesInfoImpl(10, LifeManager.WITH_LIFE, Optional.of(0), new Position(new PhisicalProprieties(new Point(0, 0), new Dimension(40, 60), 5), Actions.STOP), new Bounds(0,  1000, 0, 300), false, MovementTypes.HERO));
		
		Integer tmp = codIterator.next();
		stati.put(tmp, new EntitiesInfoImpl(10, LifeManager.WITH_LIFE, Optional.of(0), new Position(new PhisicalProprieties(new Point(20, 150), new Dimension(40, 60), 5), Actions.RIGHT), new Bounds(0,  1000, 0, 300), true, MovementTypes.LINEAR));
		database.putEntity(tmp, Entities.MONSTER1);
		
		tmp = codIterator.next();
                stati.put(tmp, new EntitiesInfoImpl(10, LifeManager.WITH_LIFE, Optional.of(0), new Position(new PhisicalProprieties(new Point(20, 150), new Dimension(40, 60), 0), Actions.RIGHT), new Bounds(0,  1000, 0, 300), true, MovementTypes.STATIC));
                database.putEntity(tmp, Entities.MONSTER1);
                
                tmp = codIterator.next();
                stati.put(tmp, new EntitiesInfoImpl(10, LifeManager.WITH_LIFE, Optional.of(0), new Position(new PhisicalProprieties(new Point(20, 150), new Dimension(40, 60), 5), Actions.UP), new Bounds(0,  1000, 0, 300), true, MovementTypes.LINEAR));
                database.putEntity(tmp, Entities.MONSTER1);
                
                tmp = codIterator.next();
                stati.put(tmp, new EntitiesInfoImpl(10, LifeManager.WITH_LIFE, Optional.of(0), new Position(new PhisicalProprieties(new Point(20, 150), new Dimension(40, 60), 5), Actions.RIGHT), new Bounds(0,  1000, 0, 300), true, MovementTypes.RANDOM));
                database.putEntity(tmp, Entities.MONSTER1);
                
                
                
		
		Dimension arenaDim = new Dimension(1000, 300);
		
		
                database.putArenaDimension(arenaDim);
                this.model.createArena(stati/*, din*/);
		
		this.view.changeScene(new Pair<SceneType, Dimension>(SceneType.DRAWABLE, new Dimension(1000, 300)));
		
		Thread t = new GameThread(this.model, this.view, database);
		t.start();
		
		
	}

}
