package model;

import java.util.List;
import java.util.Map;

import control.Entities;
import control.Pair;
import control.Position;

public interface Arena {

    Map<Entities, List<Pair<Integer, Position>>> getHero();

}