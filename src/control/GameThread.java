package control;

import java.util.List;
import java.util.Map;

import model.Model;
import view.Entities;
import view.ViewController;

public class GameThread extends Thread {
	
	private final Model model;
	private final ViewController view;
	private final EntitiesDatabase database;
	
	public GameThread(final Model model, final ViewController view, EntitiesDatabase database) {
		this.model = model;
		this.view = view;
		this.database = database;
	}
	public void run() {
		while(true) {
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
