package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import control.Dimension;
import control.Pair;
import control.Point;

public class ModelImpl implements Model{
    
    private static final ModelImpl singleton = new ModelImpl();
    //private Arena arena;
    private List<Entities> entities;
    
    private ModelImpl() {
        this.entities = new LinkedList<>();
    }
    
    public static ModelImpl getModel() {
        return singleton;
    }

    @Override
    public void notifyEvent(ModelDirections action) {
       //this.arena.moveHero(action);
        this.entities.stream().filter(t -> t.getCode() == 0).forEach(t -> {
            t.setPosition(action.getFunction().apply(t.getPoint(), t.getSpeed()), action);
            //t.setPosition(t.getNextMove().getPoint(), action);
        });
        
        
        
    }
    
    public void updateArena() {
        //this.arena.moveOthers();
        this.entities.stream().forEach(t -> {
           t.setPosition(t.getNextMove().getPoint(), t.getNextMove().getDirection());
        });
        
    }


    @Override
    public Map<Integer, Pair<Integer, ModelPosition>> getState() {
        //Map<Integer, Pair<Integer, ModelPosition>> result = new HashMap<>(this.arena.getHero());
        //result.putAll(this.arena.getOthers());
        //return result;
        final Map<Integer, Pair<Integer, ModelPosition>> result = new HashMap<>();
        this.entities.stream().forEach(e -> result.put(e.getCode(), new Pair<Integer, ModelPosition>(e.getLife(), e.getPosition())));
        return result;
    }

    /**
     * qui mi dovrai passare solo staticOthers e dinamicOthers
     */
    @Override
    public void createArena(Map<Integer, StaticOthers> staticOthers, Map<Integer, DinamicOthers> dinamicOthers) {
        //this.arena = new ArenaImpl(hero, dimensions);
        //this.arena.putOthers(staticOthers, dinamicOthers);
        
        /**
         * qui aggiungo l'eroe ma poi lo aggiungerò dentro ai dinamicOthers ma adesso non avendo il controller 
         * pronto ho preso le informazioni dall'interfaccia Hero che aveva prima ma poi verrà eliminata
         */
        //this.entities.add(new EntitiesImpl(hero.getCode(), hero.getLife(), LifeManager.WITH_LIFE, new HeroMovementManager(hero.getPosition(), Hero.INITIAL_SPEED, new Bounds(0, dimensions.getWidth(), 0, dimensions.getHeight())), 5));
        
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
            //this.others.add(new OtherImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), movementManager, t.getValue().getContactDamage()));
            this.entities.add(new EntitiesImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), movementManager, t.getValue().getContactDamage().get()));
        });
        
        staticOthers.entrySet().stream().forEach(t -> {
            this.entities.add(new EntitiesImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), new StaticMovementManager(t.getValue().getPosition()), t.getValue().getContactDamage().get()));
            //this.others.add(new OtherImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), new StaticMovementManager(t.getValue().getPosition()), t.getValue().getContactDamage()));
            
        });
    }

}
