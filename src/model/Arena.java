package model;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import control.Pair;
import control.Point;
import control.Position;
import control.Position.Directions;

public interface Arena {

    Map<Integer, Pair<Integer, Position>> getHero();

    void moveHero(BiFunction<Point, Integer, Point> function, Optional<Directions> direction);

    void putOthers(Map<Integer, StaticOthers> staticOthers, Map<Integer, DinamicOthers> dinamicOthers);
    
    Map<Integer, Pair<Integer, Position>> getOthers();

}