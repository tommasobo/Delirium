package control;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.transfertentities.EntitiesInfo;
import model.transfertentities.EntitiesInfoImpl;
import utility.Dimension;
import view.configs.Entities;

/**
 * Implementation of EntityDatabase interface with a map that associates to each
 * entity code an element of view's enumeration Entities. This enum in used to
 * determinate the view's representation of the entity
 * 
 * @author Matteo Magnani
 *
 */
class EntitiesDatabaseImpl implements EntitiesDatabase {

    final private Map<Integer, Entities> viewEntitiesCodes;
    private final Dimension arenaDimension;
    private final Iterator<Integer> codesIterator;
    
    /**
     * 
     * @param arenaDimension The dimension of the level's arena
     */
    public EntitiesDatabaseImpl(final Dimension arenaDimension) {
        this.viewEntitiesCodes = new HashMap<>();
        this.arenaDimension = arenaDimension;
        this.codesIterator = new CodesIteratorImpl();
    }
    
    @Override
    public void putEntity(final Integer code, final Entities entity) {
        this.viewEntitiesCodes.put(code, entity);
    }
    
    @Override
    public void putEntity(final EntitiesInfo modelEnt, final Entities entity) {
        this.putEntity(modelEnt.getCode(), entity);
    }
    
    @Override
    public EntitiesInfo putEntityAndSetCode(final EntitiesInfo modelEnt, final Entities entity) {
        final EntitiesInfo modelEntCopy = new EntitiesInfoImpl(this.codesIterator.next(), modelEnt.getPosition(),
                modelEnt.getMovementInfo(), modelEnt.getLife(), modelEnt.getLifePattern(), modelEnt.getShootInfo(),
                modelEnt.getContactDamage());
        this.putEntity(modelEntCopy, entity);
        return modelEntCopy;
    }
    
    @Override
    public void putEntities(final List<EntitiesInfo> entities, final Entities entity) {
        for (final EntitiesInfo ent : entities) {
            this.putEntity(ent.getCode(), entity);
        }
    }
    
    @Override
    public List<EntitiesInfo> putEntitiesAndSetCodes(final List<EntitiesInfo> entities, final Entities entity) {
        final List<EntitiesInfo> entitiesRet = new LinkedList<>();
        for (final EntitiesInfo ent : entities) {
            entitiesRet.add(this.putEntityAndSetCode(ent, entity));
        }
        return entitiesRet;
    }
    
    @Override
    public List<EntitiesInfo> putBulletsAndSetCodes(final List<EntitiesInfo> entities) {
        final List<EntitiesInfo> entitiesRet = new LinkedList<>();
        for (final EntitiesInfo ent : entities) {
            entitiesRet.add(this.putEntityAndSetCode(ent, Translator.getEntityBulletType(this.getViewEntity(ent.getCode()))));
        }
        return entitiesRet;
    }
    
    @Override
    public Entities getViewEntity(final Integer code) {
        //TODO exception se non è in database
        return this.viewEntitiesCodes.get(code);
    }
    
    
    @Override
    public Dimension getArenaDimension() {
        return this.arenaDimension;
    }

}
