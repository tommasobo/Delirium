package control.fileloading.levels;

/**
 * Enumeration that contains all game levels with relative store file name
 * @author Matteo Magnani
 *
 */
public enum Levels {
    LEVEL1("level1"),
    LEVEL2("boccLevel"),
    LEVEL3("magnoLevel");
    
    private final String filename;
    private Levels(final String filename) {
        this.filename = filename;
    }
    
    public String getFilename() {
        return filename;
    }
}
