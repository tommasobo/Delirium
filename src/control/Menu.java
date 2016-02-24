package control;

public enum Menu {
    INITIAL("initialMenu"), PAUSE("pauseMenu"), LOSE("loseMenu"), WIN("winMenu"), WINEND("winEndMenu");
    private final String filename;
    
    private Menu(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
