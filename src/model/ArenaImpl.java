package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import control.Entities;
import control.Pair;
import control.Position;

public class ArenaImpl implements Arena {
    
    
    private final Position.Dimension dimensions;
    private final Heroe hero;
    
    
    public ArenaImpl(final Entities heroName, final Position.Dimension dimensions ) {
        this.dimensions = dimensions;
        this.hero = new HeroeImpl(heroName, Heroe.MAX_LIFE, Heroe.INITIAL_POSITION);
    }
    
    
    @Override
    public Map<Entities, List<Pair<Integer, Position>>> getHero() {
        Map<Entities, List<Pair<Integer, Position>>> result = new HashMap<>();
        List<Pair<Integer, Position>> list = new LinkedList<>();
        list.add(new Pair<Integer, Position>(this.hero.getLife(), this.hero.getPosition()));
        result.put(this.hero.getName(), list);
        return result;
    }
    
}
