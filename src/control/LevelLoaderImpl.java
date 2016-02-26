package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import model.transfertentities.EntitiesInfo;

public class LevelLoaderImpl {
    private final List<EntitiesInfo> entities;
    private final EntitiesDatabase database;
    private final EntityStatsModifier statsModifier;
    
    public LevelLoaderImpl(final Levels level, final EntityStatsModifier statsModifier) throws IOException {
        this.statsModifier = statsModifier;
        
        LevelInfo levelInfo = null;
        try (BufferedReader br = Files.newBufferedReader(Paths.get("res/storefiles/levels/" + level.getFilename() + ".json"));){
            final Gson gson = new Gson();
            levelInfo = gson.fromJson(br, LevelInfoImpl.class);
        } catch (IOException e) {
            throw e;
        }
        this.entities = new LinkedList<>();
        int i = 0;
        this.database = new EntitiesDatabaseImpl(levelInfo.getLevelDimension());
        for(final EntitiesInfoStore ent : levelInfo.getEntities()) {
            EntitiesInfoStore entity = ent;
            if( i!=0 ) {
                entity = this.statsModifier.modifyEntity(entity); 
            }
            this.entities.add(this.database.putEntityAndSetCode(entity, entity.getEntityType()));
            i++;
        }
    }

    public List<EntitiesInfo> getEntities() {
        return entities;
    }

    public EntitiesDatabase getDatabase() {
        return database;
    }
}
