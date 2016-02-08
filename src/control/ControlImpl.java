package control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Model;
import model.ModelImpl;
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
		Map<Entities, List<Pair<Integer, Position>>> map = new HashMap<>();
		map.put(Entities.JOYHERO, new LinkedList<>());
		this.model.createArena(map, new Dimension(1000, 300));
		
		while(true) {
			
		}
	}
	
	public void notifyEvent(ViewEvents event) {
		
	}
	
	public List<Buttons> getButtons() {
		return null;
	}

}
