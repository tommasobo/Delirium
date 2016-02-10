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
import control.Position;

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
    public Map<Integer, Pair<Integer, Position>> getHero() {
        Map<Integer, Pair<Integer, Position>> result = new HashMap<>();
        result.put(this.hero.getCode(), new Pair<Integer, Position>(this.hero.getLife(), new Position(new Point(this.hero.getPosition().getPoint().getX(), this.hero.getPosition().getPoint().getY()), this.hero.getPosition().getDirection(), new Dimension(this.hero.getPosition().getDimension().getWidth(), this.hero.getPosition().getDimension().getHeight()))));
        return result;
    }


    @Override
    public void moveHero(final BiFunction<Point, Integer, Point> function, final Optional<Position.Directions> direction) {
        Point actualPoint = this.hero.getPosition().getPoint();
        this.hero.setPosition(function.apply(actualPoint, this.hero.getSpeed()), direction.isPresent() ? direction.get() : this.hero.getPosition().getDirection());
    }


    @Override
    public void putOthers(Map<Integer, StaticOthers> staticOthers, Map<Integer, DinamicOthers> dinamicOthers) {
        dinamicOthers.entrySet().stream().forEach(t -> {
            this.others.add(new OtherImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), t.getValue().getPosition().getDirection() == Position.Directions.NONE ? new RandomDinamicMovementManager(t.getValue().getPosition(), t.getValue().getSpeed(), t.getValue().getBounds()) : new LinearDinamicMovementManager(t.getValue().getPosition(), t.getValue().getSpeed(), t.getValue().getBounds()), t.getValue().getContactDamage()));
        });
        
        staticOthers.entrySet().stream().forEach(t -> {
            this.others.add(new OtherImpl(t.getKey(), t.getValue().getLife(), t.getValue().getLifemanager(), new StaticMovementManager(t.getValue().getPosition()), t.getValue().getContactDamage()));
        });
        
    }


    @Override
    public Map<Integer, Pair<Integer, Position>> getOthers() {
        final Map<Integer, Pair<Integer, Position>> result = new HashMap<>();
        this.others.stream().forEach(e -> result.put(e.getCode(), new Pair<Integer, Position>(e.getLife(), new Position(new Point(e.getMovementManager().getPosition().getPoint().getX(), e.getMovementManager().getPosition().getPoint().getY()), e.getMovementManager().getPosition().getDirection(), new Dimension(e.getMovementManager().getPosition().getDimension().getWidth(), e.getMovementManager().getPosition().getDimension().getHeight())))));
        return result;
        
    }


    @Override
    public void moveOthers() {
        this.others.stream().forEach(t -> {
            Position newPosition = t.getMovementManager().getNextMove();
            t.getMovementManager().setPosition(newPosition.getPoint(), newPosition.getDirection());
        });
    }
    
    
    
    
    
}
