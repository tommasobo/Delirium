package control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.EntitiesInfo;
import model.EntitiesInfoImpl;
import utility.Dimension;
import view.Entities;

class EntitiesDatabaseImpl implements EntitiesDatabase {

    private Map<Integer, Entities> viewEntitiesCodes;
    private Dimension arenaDimension;
    private final CodesIterator codesIterator;

    public EntitiesDatabaseImpl() {
        this.viewEntitiesCodes = new HashMap<>();
        //TODO null brutto
        this.arenaDimension = null;
        this.codesIterator = new CodesIteratorImpl();
    }

    public void putEntity(Integer code, Entities entity) {
        this.viewEntitiesCodes.put(code, entity);
    }

    public void putEntity(EntitiesInfo modelEnt, Entities entity) {
        this.putEntity(modelEnt.getCode(), entity);
    }

    public EntitiesInfo putEntityAndSetCode(EntitiesInfo modelEnt, Entities entity) {
        EntitiesInfo modelEntCopy = new EntitiesInfoImpl(modelEnt.getCode() != -1 ? this.codesIterator.next() : -1, modelEnt.getPosition(),
                modelEnt.getMovementInfo(), modelEnt.getLife(), modelEnt.getLifePattern(), modelEnt.getShootInfo(),
                modelEnt.getContactDamage());
        this.putEntity(modelEntCopy, entity);
        return modelEntCopy;
    }

    public void putEntities(List<EntitiesInfo> entities, Entities entity) {
        for (EntitiesInfo ent : entities) {
            this.putEntity(ent.getCode(), entity);
        }
    }
    
    public List<EntitiesInfo> putEntitiesAndSetCodes(List<EntitiesInfo> entities, Entities entity) {
        List<EntitiesInfo> entitiesRet = new LinkedList<>();
        for (EntitiesInfo ent : entities) {
            entitiesRet.add(this.putEntityAndSetCode(ent, entity));
        }
        return entitiesRet;
    }
    
    public List<EntitiesInfo> putBulletsAndSetCodes(List<EntitiesInfo> entities) {
        List<EntitiesInfo> entitiesRet = new LinkedList<>();
        for (EntitiesInfo ent : entities) {
            entitiesRet.add(this.putEntityAndSetCode(ent, Translator.getEntityBulletType(this.getViewEntity(ent.getCode()))));
        }
        return entitiesRet;
    }

    public Entities getViewEntity(Integer code) {
        return this.viewEntitiesCodes.get(code);
    }

    public void putArenaDimension(Dimension dimension) {
        this.arenaDimension = dimension;
    }

    public Dimension getArenaDimension() {
        return this.arenaDimension;
    }

}
