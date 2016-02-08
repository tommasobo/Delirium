package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import control.Dimension;
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
        actions.stream().forEach(t -> {
            if (t == PGActions.MRIGHT) {
                this.arena.moveHero(x -> x + 1, y -> y, Optional.of(Position.Directions.RIGHT));
            } else if (t == PGActions.MLEFT) {
                this.arena.moveHero(x -> x - 1, y -> y, Optional.of(Position.Directions.RIGHT));
            }
//work in progress: per il salto            
//            } else if (t == PGActions.JUMP) {
//                this.arena.moveHero(x -> x, y -> y + 1, Optional.empty());
//            }
        });
    }
    
    public void updateArena() {
        
    }


    @Override
    public Map<Entities, List<Pair<Integer, Position>>> getState() {
        Map<Entities, List<Pair<Integer, Position>>> result = new HashMap<>();
        result.putAll(this.arena.getHero());
        return result;
    }

    @Override
    public void createArena(Map<Entities, List<Pair<Integer, Position>>> entities, Dimension dimensions) {
        entities.entrySet().stream().forEach( t -> {
            if (t.getKey() == Entities.JOYHERO) {
                this.arena = new ArenaImpl(t.getKey(), dimensions);
            }
        });
        
    }

   

}