package control;

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
			this.view.updateScene(this.model.getState());
			try {
				Thread.sleep(30L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
