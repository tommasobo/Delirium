package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import control.Entities;
import control.PGActions;
import control.Pair;
import control.Position;

public class ModelImpl implements Model{
    
    private static final ModelImpl singleton = new ModelImpl();
    private Arena arena;
    
    private ModelImpl() {
    }
    
    public static ModelImpl getModel() {
        return singleton;
    }

    @Override
    public void notifyEvent(List<PGActions> actions) {
        
    }


    @Override
    public Map<Entities, List<Pair<Integer, Position>>> getState() {
        Map<Entities, List<Pair<Integer, Position>>> result = new HashMap<>();
        result.putAll(this.arena.getHero());
        return result;
    }

    @Override
    public void CreateArena(Map<Entities, List<Pair<Integer, Position>>> entities, Position.Dimension dimensions) {
        entities.entrySet().stream().forEach( t -> {
            if (t.getKey() == Entities.JOYHERO) {
                this.arena = new ArenaImpl(t.getKey(), dimensions);
            }
        });
        
        
    }

   
    
    
    
    

}
