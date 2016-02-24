package control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.EntitiesInfo;
import model.EntitiesInfoImpl;
import utility.Dimension;
import utility.Pair;
import view.Entities;

class EntitiesDatabaseImpl implements EntitiesDatabase {

    private Map<Integer, Entities> viewEntitiesCodes;
    private Dimension arenaDimension;
    private final CodesIterator codesIterator;

    public EntitiesDatabaseImpl() {
        this.viewEntitiesCodes = new HashMap<>();
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
        // TODO come sotto, è più corretto e sicuro che il metodo non
        // restituisca nulla o che lavori su una copia protetta?
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

    public void putEntities(List<Pair<EntitiesInfo, Entities>> entities) {
        for (Pair<EntitiesInfo, Entities> ent : entities) {
            this.putEntity(ent.getX().getCode(), ent.getY());
        }
    }

    // TODO per i due metodi sotto, dopo aver risolto il dubbio sul return,
    // usare la put entity singola nel foreach
    public List<EntitiesInfo> putEntitiesAndSetCodes(List<EntitiesInfo> entities, Entities entity) {
        // il return è pseudo-inutile, infatti con il set modifico la lista che
        // mi passa, potrei creare direttamente oggetti nuovi, CHIEDI A VIROLI
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

    public List<EntitiesInfo> putEntitiesAndSetCodes(List<Pair<EntitiesInfo, Entities>> entities) {
        List<EntitiesInfo> ret = new LinkedList<>();
        for (Pair<EntitiesInfo, Entities> ent : entities) {
            ret.add(this.putEntityAndSetCode(ent.getX(), ent.getY()));
        }
        return ret;
    }

    @Override
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
