package control.fileloading.menu;

/**
 * Enumeration that contains all possible game menus with relative file names.
 * 
 * @author Matteo Magnani
 *
 */
public enum Menu {
    INITIAL("initialMenu"),
    PAUSE("pauseMenu"),
    LOSE("loseMenu"),
    WIN("winMenu"),
    WINEND("winEndMenu"),
    SETTINGS("settingsMenu"),
    NONE("");
    private final String filename;
    
    Menu(final String filename) {
        this.filename = filename;
    }

    /**
     * 
     * @return The file name
     */
    public String getFilename() {
        return filename;
    }
}
