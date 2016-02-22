package control;

public enum Levels {
    LEVEL1("level1");
    
    private final String filename;
    private Levels(String filename) {
        this.filename = filename;
    }
    
    public String getFilename() {
        return filename;
    }
}
