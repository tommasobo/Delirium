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
            t.setPosition(action.getFunction().apply(t.getPoint(), t.getSpeed()), action);
        });
    }
    
    public void updateArena() {
        this.entities.stream().forEach(t -> {
           t.setPosition(t.getNextMove().getPoint(), t.getNextMove().getDirection());
        });
        
    }


    @Override
    public Map<Integer, Pair<Integer, ModelPosition>> getState() {
        final Map<Integer, Pair<Integer, ModelPosition>> result = new HashMap<>();
        this.entities.stream().forEach(e -> result.put(e.getCode(), new Pair<Integer, ModelPosition>(e.getLife(), e.getPosition())));
        return result;
    }

    @Override
    public void createArena(Map<Integer, StaticOthers> staticOthers, Map<Integer, DinamicOthers> dinamicOthers) {
        
        dinamicOthers.entrySet().stream().filter(t -> t.getKey() == 0).forEach(t -> {
            this.entities.add(new EntitiesImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), new HeroMovementManager(t.getValue().getPosition(), t.getValue().getBounds()), t.getValue().getContactDamage().get()));
        });
        
        dinamicOthers.entrySet().stream().filter(t -> t.getKey() != 0).forEach(t -> {
            MovementManager movementManager;
            if(t.getValue().getDirection() == ModelDirections.NONE) {
                movementManager = new RandomDinamicMovementManager(t.getValue().getPosition(), t.getValue().getBounds());
            } else {
                movementManager = new LinearDinamicMovementManager(t.getValue().getPosition(), t.getValue().getBounds());
            }
            this.entities.add(new EntitiesImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), movementManager, t.getValue().getContactDamage().get()));
        });
        
        staticOthers.entrySet().stream().forEach(t -> {
            this.entities.add(new EntitiesImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), new StaticMovementManager(t.getValue().getPosition()), t.getValue().getContactDamage().get()));
            
        });
    }

}
