package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import control.Pair;

public class ModelImpl implements Model{
    
    private static final ModelImpl singleton = new ModelImpl();
    private List<Entities> entities;
    
    private ModelImpl() {
        this.entities = new LinkedList<>();
    }
    
    public static ModelImpl getModel() {
        return singleton;
    }

    @Override
    public void notifyEvent(ModelDirections action) {
        this.entities.stream().filter(t -> t.getCode() == 0).forEach(t -> {
            t.setDirection(action);
        });
    }
    
    public void updateArena() {
        this.entities.stream().forEach(t -> {
           t.setPosition(t.getNextMove().getPoint(), t.getNextMove().getDirection());
        });
        
        //MAGNI PART BEGIN
        
        //GESTION COLLISIONI
        
        //MAGNANI PART FINISH
        
    }


    @Override
    public Map<Integer, Pair<Integer, ModelPosition>> getState() {
        final Map<Integer, Pair<Integer, ModelPosition>> result = new HashMap<>();
        this.entities.stream().forEach(e -> result.put(e.getCode(), new Pair<Integer, ModelPosition>(e.getLife(), e.getPosition())));
        return result;
    }

    @Override
    public void createArena(Map<Integer, EntitiesInfo> entitiesInfo) {
        
        
        entitiesInfo.entrySet().stream().forEach(t -> {
            if(t.getKey() == 0) {
                this.entities.add(new EntitiesImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), new HeroMovementManager(t.getValue().getPosition(), t.getValue().getBounds(), t.getValue().isCanFly()), t.getValue().getContactDamage().get()));
            } else {
                    switch (t.getValue().getMovementTypes()) {
                    case HERO: 
                        this.entities.add(new EntitiesImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), new HeroMovementManager(t.getValue().getPosition(), t.getValue().getBounds(), t.getValue().isCanFly()), t.getValue().getContactDamage().get()));
                        break;
                    case STATIC : 
                        this.entities.add(new EntitiesImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), new StaticMovementManager(t.getValue().getPosition(), t.getValue().getBounds(), t.getValue().isCanFly()), t.getValue().getContactDamage().get()));    
                        break;
                    case RANDOM: 
                        this.entities.add(new EntitiesImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), new RandomDinamicMovementManager(t.getValue().getPosition(), t.getValue().getBounds(), t.getValue().isCanFly()), t.getValue().getContactDamage().get()));    
                        break;
                    case LINEAR:
                        this.entities.add(new EntitiesImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), new LinearDinamicMovementManager(t.getValue().getPosition(), t.getValue().getBounds(), t.getValue().isCanFly()), t.getValue().getContactDamage().get()));
                        break;
                    }
            }
            
        });
    }

}
