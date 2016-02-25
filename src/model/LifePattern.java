package model;

import java.util.function.BiFunction;

public enum LifePattern {

    WITH_LIFE((life, damage) -> (life - damage > 0 ? life - damage : 0)),
    WITHOUT_LIFE((life, damage) -> life);

    private final BiFunction<Integer, Integer, Integer> function;

    LifePattern(final BiFunction<Integer, Integer, Integer> function) {
        this.function = function;
    }

    public BiFunction<Integer, Integer, Integer> getFunction() {
        return this.function;
    }
}
