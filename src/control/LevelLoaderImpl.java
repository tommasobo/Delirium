package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

import model.EntitiesInfo;

public class LevelLoaderImpl {
    private final Levels levelType;
    private final List<EntitiesInfo> entities;
    private LevelInfo levelInfo;
    private final EntitiesDatabase database;
    private final EntityStatsModifier statsModifier;
    
    public LevelLoaderImpl(final Levels level, final EntityStatsModifier statsModifier) {
        this.levelType = level;
        this.statsModifier = statsModifier;
        
        //TODO crea classe loader per ottenere gli input stream?
        //TODO mettere eccezioni per mancato file load
        try (BufferedReader br = Files.newBufferedReader(Paths.get("res/storefiles/levels/" + level.getFilename() + ".json"));){
            final Gson gson = new Gson();
            this.levelInfo = gson.fromJson(br, LevelInfoImpl.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //TODO aggiungi metodo per fare put di entity multiple con entità multiple
        this.entities = new LinkedList<>();
        int i = 0;
        this.database = new EntitiesDatabaseImpl(this.levelInfo.getLevelDimension());
        for(final EntitiesInfoStore ent : this.levelInfo.getEntities()) {
            EntitiesInfoStore entity = ent;
            if( i!=0 ) {
                entity = modifyEntityStats(entity); 
            }
            this.entities.add(this.database.putEntityAndSetCode(entity, entity.getEntityType()));
            i++;
        }
    }
    
    private EntitiesInfoStore modifyEntityStats(final EntitiesInfoStore entity) {
        final EntitiesInfoStore ent = new EntitiesInfoStore(entity);
        ent.setLife(this.statsModifier.getLifeIncremented(ent.getLife()));
        if(ent.getMovementInfoStore().isPresent()) {
            final MovementInfoStore mi = ent.getMovementInfoStore().get();
            mi.setSpeed(this.statsModifier.getSpeedIncremented(mi.getSpeed()));
        }
        if(ent.getShootInfoStore().isPresent()) {
            final ShootInfoStore si = ent.getShootInfoStore().get();
            si.setSpeed(this.statsModifier.getSpeedIncremented(si.getSpeed()));
            si.setBulletDamage(this.statsModifier.getDamageIncremented(si.getBulletDamage()));
        }
        if(ent.getShootInfoStore().isPresent()) {
            final ShootInfoStore si = ent.getShootInfoStore().get();
            si.setSpeed(this.statsModifier.getSpeedIncremented(si.getSpeed()));
            si.setBulletDamage(this.statsModifier.getDamageIncremented(si.getBulletDamage()));
        }
        if(ent.getContactDamage().isPresent()) {
            ent.setContactDamage(Optional.of(this.statsModifier.getDamageIncremented(ent.getContactDamage().get())));
        }
        return ent;
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
