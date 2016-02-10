package model;

import java.util.Map;

import control.Pair;

public interface Arena {

    Map<Integer, Pair<Integer, ModelPosition>> getHero();

    void moveHero(ModelDirections action);

    void putOthers(Map<Integer, StaticOthers> staticOthers, Map<Integer, DinamicOthers> dinamicOthers);
    
    Map<Integer, Pair<Integer, ModelPosition>> getOthers();
    
    void moveOthers();

}