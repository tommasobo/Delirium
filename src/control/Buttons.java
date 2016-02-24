package control;

public enum Buttons {
	NEWGAME(ViewEvents.LEVEL1), MAINMENU(ViewEvents.BACKTOMAINMENU), EXIT(ViewEvents.EXIT), NEXTLEVEL(ViewEvents.NEXTLEVEL);
	private final ViewEvents event;
	private Buttons(ViewEvents event) {
		this.event = event;
	}
	public ViewEvents getEvent() {
		return event;
	}
	
}
