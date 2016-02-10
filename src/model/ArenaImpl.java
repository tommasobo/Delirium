package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import control.Dimension;
import control.Pair;
import control.Point;

public class ArenaImpl implements Arena {
    
    
    private final Dimension dimensions;
    private final Hero hero;
    private List<Other> others;
    
    
    public ArenaImpl(final Heroes hero, final Dimension dimensions ) {
        this.dimensions = dimensions;
        this.hero = new HeroImpl(hero.getCode(), hero.getLife(), hero.getPosition(), hero.gestSpeed());
        this.others = new LinkedList<>();
    }
    
    
    @Override
    public Map<Integer, Pair<Integer, ModelPosition>> getHero() {
        Map<Integer, Pair<Integer, ModelPosition>> result = new HashMap<>();
        result.put(this.hero.getCode(), new Pair<Integer, ModelPosition>(this.hero.getLife(), this.hero.getPosition()));
        return result;
    }


    @Override
    public void moveHero(final ModelDirections action) {
        Point actualPoint = this.hero.getPosition().getPoint();
        this.hero.setPosition(action.getFunction().apply(actualPoint, this.hero.getSpeed()), action);
    }


    @Override
    public void putOthers(Map<Integer, StaticOthers> staticOthers, Map<Integer, DinamicOthers> dinamicOthers) {
        dinamicOthers.entrySet().stream().forEach(t -> {
            this.others.add(new OtherImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), t.getValue().getPosition().getDirection() == ModelDirections.NONE ? new RandomDinamicMovementManager(t.getValue().getPosition(), t.getValue().getSpeed(), t.getValue().getBounds()) : new LinearDinamicMovementManager(t.getValue().getPosition(), t.getValue().getSpeed(), t.getValue().getBounds()), t.getValue().getContactDamage()));
        });
        
        staticOthers.entrySet().stream().forEach(t -> {
            this.others.add(new OtherImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), new StaticMovementManager(t.getValue().getPosition()), t.getValue().getContactDamage()));
        });
        
    }


    @Override
    public Map<Integer, Pair<Integer, ModelPosition>> getOthers() {
        final Map<Integer, Pair<Integer, ModelPosition>> result = new HashMap<>();
        this.others.stream().forEach(e -> result.put(e.getCode(), new Pair<Integer, ModelPosition>(e.getLife(), e.getMovementManager().getPosition())));
        return result;
        
    }


    @Override
    public void moveOthers() {
        this.others.stream().forEach(t -> {
            ModelPosition newPosition = t.getMovementManager().getNextMove();
            t.getMovementManager().setPosition(newPosition.getPoint(), newPosition.getDirection());
        });
    }
    
    
    
    
    
}
