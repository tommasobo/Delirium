package control.fileloading.levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import control.fileloading.levels.storestructures.EntitiesInfoStore;
import control.fileloading.levels.storestructures.LevelInfo;
import control.fileloading.levels.storestructures.LevelInfoImpl;
import control.game.settings.EntityStatsModifier;
import control.viewcomunication.translation.EntitiesDatabase;
import control.viewcomunication.translation.EntitiesDatabaseImpl;
import model.transfertentities.EntitiesInfo;
import utility.Pair;

/**
 * Class that can load levels from file and modify entities statistics using the
 * EntityStatsModifier passed through constructor
 * 
 * @author Matteo Magnani
 *
 */
public class LevelLoaderImpl implements LevelLoader {
    
    private final LevelInfo levelInfo;
    private final EntityStatsModifier statsModifier;
    
    /**
     * The constructor load the level from the file witch name is stored in Levels' enumeration element
     * @param level The level to load
     * @param statsModifier The modifier for the loaded entities' statistics 
     * @throws IOException
     */
    public LevelLoaderImpl(final Levels level, final EntityStatsModifier statsModifier) throws IOException {
        
        this.statsModifier = statsModifier;
        try (BufferedReader br = Files.newBufferedReader(Paths.get("res/storefiles/levels/" + level.getFilename() + ".json"));){
            final Gson gson = new Gson();
            this.levelInfo = gson.fromJson(br, LevelInfoImpl.class);
        } catch (IOException e) {
            throw e;
        }
    }

    
    public Pair<List<EntitiesInfo>, EntitiesDatabase> getLevelStructure() {
        List<EntitiesInfo> entities = new LinkedList<>();
        EntitiesDatabase database = new EntitiesDatabaseImpl(this.levelInfo.getLevelDimension());
        int i = 0;
        for(final EntitiesInfoStore ent : levelInfo.getEntities()) {
            EntitiesInfoStore entity = ent;
            if( i!=0 ) {
                entity = this.statsModifier.modifyEntity(entity); 
            }
            //L'entità di codice -1 è il goal (obbietivo del gioco) e quindi ha già il codice correttamente settato
            if(entity.getCode() == -1) {
                database.putEntity(entity, entity.getEntityType());
                entities.add(entity);
            } else {
                entities.add(database.putEntityAndSetCode(entity, entity.getEntityType()));
            }
            
            i++;
        }
        return new Pair<>(entities, database);
    }
}
