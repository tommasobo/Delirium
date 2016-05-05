package control.viewcomunication;

/**
 * An enum that contains all buttons of game menus, each button contains a
 * string to be printed and a view event to to control that the button is pushed
 * 
 * @author Matteo Magnani
 *
 */
public enum Buttons {
    NEWGAME("NEW GAME", ViewEvents.LEVEL1),
    ARCADE("ARCADE", ViewEvents.LEVEL1),
    MYCHARACTER("MY CHARACTER", ViewEvents.LEVEL1),
    EDITOR("EDITOR", ViewEvents.LEVEL1),
    MAINMENU("MAIN MENU", ViewEvents.BACKTOMAINMENU),
    EXIT("EXIT", ViewEvents.EXIT),
    NEXTLEVEL("NEXT LEVEL", ViewEvents.NEXTLEVEL),
    SETTINGS("SETTINGS", ViewEvents.SETTINGS), 
    EASYMODE("EASY", ViewEvents.EASYMODE),
    NORMALMODE("NORMALMODE", ViewEvents.NORMALMODE),
    HARDMODE("HARD", ViewEvents.HARDMODE),
    DELIRIUMMODE("DELIRIUM", ViewEvents.DELIRIUMMODE);

    private final ViewEvents event;
    private final String name;

    Buttons(final String name, final ViewEvents event) {
        this.event = event;
        this.name = name;
    }

    /**
     * 
     * @return The view event associated to button
     */
    public ViewEvents getEvent() {
        return event;
    }

    /**
     * 
     * @return The string to print on the button
     */
    public String getName() {
        return name;
    }

}
