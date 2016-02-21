package control;

public enum Menu {
    INITIAL("initialMenu.yaml"), PAUSE("pauseMenu.yaml");
    private final String filename;
    
    private Menu(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
