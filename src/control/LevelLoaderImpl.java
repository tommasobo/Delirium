package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import model.EntitiesInfo;

public class LevelLoaderImpl {
    private final Levels levelType;
    private final List<EntitiesInfo> entities;
    private LevelInfo levelInfo;
    private final EntitiesDatabase database;
    
    public LevelLoaderImpl(Levels level) {
        this.levelType = level;
        this.database = new EntitiesDatabaseImpl();
        //TODO crea classe loader per ottenere gli input stream?
        //TODO mettere eccezioni per mancato file load
        try (BufferedReader br = Files.newBufferedReader(Paths.get("res/storefiles/levels/" + level.getFilename() + ".json"));){
            Gson gson = new Gson();
            this.levelInfo = gson.fromJson(br, LevelInfo.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //TODO aggiungi metodo per fare put di entity multiple con entità multiple
        this.entities = new LinkedList<>();
        for(EntitiesInfoStore ent : this.levelInfo.getEntities()) {
            this.entities.add(this.database.putEntityAndSetCode(ent, ent.getEntityType()));
        }
        database.putArenaDimension(this.levelInfo.getLevelDimension());
    }

    public Levels getLevelType() {
        return levelType;
    }

    public List<EntitiesInfo> getEntities() {
        return entities;
    }

    public LevelInfo getLevelInfo() {
        return levelInfo;
    }

    public EntitiesDatabase getDatabase() {
        return database;
    }
}
