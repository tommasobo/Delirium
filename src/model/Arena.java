package model;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import control.Entities;
import control.Pair;
import control.Point;
import control.Position;
import control.Position.Directions;

public interface Arena {

    Map<Entities, List<Pair<Integer, Position>>> getHero();

    void moveHero(Function<Point, Point> function, Optional<Directions> direction);

}