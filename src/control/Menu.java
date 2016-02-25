package control;

public enum Menu {
    INITIAL("initialMenu"),
    PAUSE("pauseMenu"),
    LOSE("loseMenu"),
    WIN("winMenu"),
    WINEND("winEndMenu"),
    SETTINGS("settingsMenu"),
    NONE("");
    private final String filename;
    
    private Menu(final String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
