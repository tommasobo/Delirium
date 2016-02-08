package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import control.Entities;
import control.Pair;
import control.Point;
import control.Position;
import control.Dimension;

public class ArenaImpl implements Arena {
    
    
    private final Dimension dimensions;
    private final Heroe hero;
    
    
    public ArenaImpl(final Entities heroName, final Dimension dimensions ) {
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


    @Override
    public void moveHero(Function<Integer, Integer> functionX, Function<Integer, Integer> functionY, final Optional<Position.Directions> direction) {
        Point actualPoint = this.hero.getPosition().getPoint();
//work in progress: per gestire salto
//        if (!direction.isPresent()) {
//            this.hero.onJump();
//        }
        this.hero.setPosition(new Point(functionX.apply(actualPoint.getX()), functionY.apply(actualPoint.getX())), direction.isPresent() ? direction.get() : this.hero.getPosition().getDirection());
    }
    
}
