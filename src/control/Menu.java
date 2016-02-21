package control;

public enum Menu {
    INITIAL("InitialMenu.yaml");
    private final String filename;
    
    private Menu(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
