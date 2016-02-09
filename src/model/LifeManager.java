package model;

import java.util.function.BiFunction;

public enum LifeManager {
    
    WITH_LIFE((life, damage) -> (life - damage)),
    WITHOUT_LIFE((life, damage) -> (life));
    
    private final BiFunction<Integer, Integer, Integer> function;
    
    LifeManager( final BiFunction<Integer, Integer, Integer> function) {
        this.function = function;
    }

    public BiFunction<Integer, Integer, Integer> getFunction() {
        return function;
    }
    
    

}
