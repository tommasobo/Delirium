package control;

public enum Buttons {
    NEWGAME("New Game", ViewEvents.LEVEL1),
    MAINMENU("Main Menu", ViewEvents.BACKTOMAINMENU),
    EXIT("Exit", ViewEvents.EXIT),
    NEXTLEVEL("Next Level", ViewEvents.NEXTLEVEL);
    
    private final ViewEvents event;
    private final String name;

    private Buttons(String name, ViewEvents event) {
        this.event = event;
        this.name = name;
    }

    public ViewEvents getEvent() {
        return event;
    }

    public String getName() {
        return name;
    }

}
