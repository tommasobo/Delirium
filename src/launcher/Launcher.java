package launcher;

import control.Control;
import control.ControlImpl;

public class Launcher {

	public static void main(String[] args) {
		Control control= new ControlImpl();
		control.startGame();
	}

}
