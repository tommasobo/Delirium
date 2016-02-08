package control;

import java.util.List;
import java.util.Map;

import model.Model;
import view.ViewController;

public class GameThread extends Thread {
	
	private final Model model;
	private final ViewController view;
	
	public GameThread(final Model model, final ViewController view) {
		this.model = model;
		this.view = view;
	}
	public void run() {
		while(true){
			this.model.updateArena();
			Map<Entities, List<Pair<Integer, Position>>> map = this.model.getState();
			map.entrySet().stream().map(e -> e.getValue()).forEach(e -> {
                            e.stream().map(c -> c.getY().getPoint()).forEach(c -> {
                                c.setX(c.getX()*2);
                                c.setY(c.getY()*-2);
                            });;
                        });
			this.view.updateScene(map);
			try {
				Thread.sleep(30L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
