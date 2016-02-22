package control;

public enum Menu {
    INITIAL("initialMenu"), PAUSE("pauseMenu");
    private final String filename;
    
    private Menu(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
