package control;

public enum Levels {
    LEVEL1("level1.yaml");
    
    private final String filename;
    private Levels(String filename) {
        this.filename = filename;
    }
    
    public String getFilename() {
        return filename;
    }
}
