package control;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.transfertentities.EntitiesInfo;
import model.transfertentities.EntitiesInfoImpl;
import utility.Dimension;
import view.Entities;

class EntitiesDatabaseImpl implements EntitiesDatabase {

    final private Map<Integer, Entities> viewEntitiesCodes;
    private Dimension arenaDimension;
    private final Iterator<Integer> codesIterator;
    
    public EntitiesDatabaseImpl(final Dimension arenaDimension) {
        this.viewEntitiesCodes = new HashMap<>();
        this.arenaDimension = arenaDimension;
        this.codesIterator = new CodesIteratorImpl();
    }

    public void putEntity(final Integer code, final Entities entity) {
        this.viewEntitiesCodes.put(code, entity);
    }

    public void putEntity(final EntitiesInfo modelEnt, final Entities entity) {
        this.putEntity(modelEnt.getCode(), entity);
    }

    public EntitiesInfo putEntityAndSetCode(final EntitiesInfo modelEnt, final Entities entity) {
        final EntitiesInfo modelEntCopy = new EntitiesInfoImpl(modelEnt.getCode() == -1 ? -1 : this.codesIterator.next(), modelEnt.getPosition(),
                modelEnt.getMovementInfo(), modelEnt.getLife(), modelEnt.getLifePattern(), modelEnt.getShootInfo(),
                modelEnt.getContactDamage());
        this.putEntity(modelEntCopy, entity);
        return modelEntCopy;
    }

    public void putEntities(final List<EntitiesInfo> entities, final Entities entity) {
        for (final EntitiesInfo ent : entities) {
            this.putEntity(ent.getCode(), entity);
        }
    }
    
    public List<EntitiesInfo> putEntitiesAndSetCodes(final List<EntitiesInfo> entities, final Entities entity) {
        final List<EntitiesInfo> entitiesRet = new LinkedList<>();
        for (final EntitiesInfo ent : entities) {
            entitiesRet.add(this.putEntityAndSetCode(ent, entity));
        }
        return entitiesRet;
    }
    
    public List<EntitiesInfo> putBulletsAndSetCodes(final List<EntitiesInfo> entities) {
        final List<EntitiesInfo> entitiesRet = new LinkedList<>();
        for (final EntitiesInfo ent : entities) {
            entitiesRet.add(this.putEntityAndSetCode(ent, Translator.getEntityBulletType(this.getViewEntity(ent.getCode()))));
        }
        return entitiesRet;
    }

    public Entities getViewEntity(final Integer code) {
        return this.viewEntitiesCodes.get(code);
    }

    public void putArenaDimension(final Dimension dimension) {
        this.arenaDimension = new Dimension(dimension.getWidth(), dimension.getHeight());
    }

    public Dimension getArenaDimension() {
        return this.arenaDimension;
    }

}
