package control;

public enum Buttons {
	NEWGAME(ViewEvents.LEVEL1);
	private final ViewEvents event;
	private Buttons(ViewEvents event) {
		this.event = event;
	}
}
