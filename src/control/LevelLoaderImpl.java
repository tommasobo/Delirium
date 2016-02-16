package control;

import java.util.LinkedList;
import java.util.List;

import model.EntitiesInfo;

public class LevelLoaderImpl {
    private final List<EntitiesInfo> entities;
    private final String filename;
    private EntitiesDatabase database;
    private final CodesIterator codeIter;
    
    public LevelLoaderImpl(final String filename) {
        this.filename = filename;
        this.entities = new LinkedList<>();
        this.codeIter = new CodesIteratorImpl();
    }
    
    public void load() {
        
    }

    public List<EntitiesInfo> getEntities() {
        return entities;
    }

    public String getFilename() {
        return filename;
    }

    public EntitiesDatabase getDatabase() {
        return database;
    }

    public CodesIterator getCodeIterator() {
        return codeIter;
    }
    
    
}
