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
    public void notifyEvent(Directions action) {
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
    public Map<Integer, Pair<Integer, Position>> getState() {
        final Map<Integer, Pair<Integer, Position>> result = new HashMap<>();
        this.entities.stream().forEach(e -> result.put(e.getCode(), new Pair<Integer, Position>(e.getLife(), e.getPosition())));
        return result;
    }

    @Override
    public void createArena(List<EntitiesInfo> entitiesInfo) {
        
        
        entitiesInfo.stream().forEach(t -> {
            switch (t.getMovementTypes()) {
            case HERO: 
                this.entities.add(new EntitiesImpl(t.getCode(), t.getLife(), t.getLifemanager(), new HeroMovementManager(t.getPosition(), t.getBounds(), t.getSpeed(), t.isCanFly()), t.getContactDamage()));
                break;
            case STATIC : 
                this.entities.add(new EntitiesImpl(t.getCode(), t.getLife(), t.getLifemanager(), new StaticMovementManager(t.getPosition(), t.getBounds(), t.getSpeed(), t.isCanFly()), t.getContactDamage()));
                break;
            case RANDOM: 
                this.entities.add(new EntitiesImpl(t.getCode(), t.getLife(), t.getLifemanager(), new RandomDinamicMovementManager(t.getPosition(), t.getBounds(), t.getSpeed(), t.isCanFly()), t.getContactDamage()));
                break;
            case LINEAR:
                break;
            }
            
        });
    }

}
