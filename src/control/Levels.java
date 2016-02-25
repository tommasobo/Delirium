package control;

public enum Levels {
    LEVEL1("level1"),
    LEVEL2("level2"),
    LEVEL3("magnoLevel");
    
    private final String filename;
    private Levels(final String filename) {
        this.filename = filename;
    }
    
    public String getFilename() {
        return filename;
    }
}
