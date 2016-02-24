package control;

public enum Buttons {
    NEWGAME("NEW GAME", ViewEvents.LEVEL1),
    MAINMENU("MAIN MENU", ViewEvents.BACKTOMAINMENU),
    EXIT("EXIT", ViewEvents.EXIT),
    NEXTLEVEL("NEXT LEVEL", ViewEvents.NEXTLEVEL),
    SETTINGS("SETTINGS", ViewEvents.SETTINGS), 
    EASYMODE("EASY", ViewEvents.EASYMODE),
    HARDMODE("HARD", ViewEvents.HARDMODE);
    
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
