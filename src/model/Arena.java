package model;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import control.Entities;
import control.Pair;
import control.Position;
import control.Position.Directions;

public interface Arena {

    Map<Entities, List<Pair<Integer, Position>>> getHero();

    void moveHero(Function<Integer, Integer> functionX, Function<Integer, Integer> functionY, Optional<Directions> direction);

}