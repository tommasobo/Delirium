package model;

import java.util.HashMap;
import java.util.Map;

import control.Dimension;
import control.Pair;

public class ModelImpl implements Model{
    
    private static final ModelImpl singleton = new ModelImpl();
    private Arena arena;
    
    private ModelImpl() {
    }
    
    public static ModelImpl getModel() {
        return singleton;
    }

    @Override
    public void notifyEvent(ModelDirections action) {
       this.arena.moveHero(action);
    }
    
    public void updateArena() {
        this.arena.moveOthers();
    }


    @Override
    public Map<Integer, Pair<Integer, ModelPosition>> getState() {
        Map<Integer, Pair<Integer, ModelPosition>> result = new HashMap<>(this.arena.getHero());
        result.putAll(this.arena.getOthers());
        return result;
    }

    @Override
    public void createArena(Heroes hero, Map<Integer, StaticOthers> staticOthers, Map<Integer, DinamicOthers> dinamicOthers,  Dimension dimensions) {
        this.arena = new ArenaImpl(hero, dimensions);
        this.arena.putOthers(staticOthers, dinamicOthers);
    }

   

}
