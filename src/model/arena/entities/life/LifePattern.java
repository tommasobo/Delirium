package model.arena.entities.life;

import java.util.function.BiFunction;

/**
 * This enum specified the Strategy of the @LifeManagerImpl so, decide the
 * future of the life of the entity.
 * 
 * @author josephgiovanelli
 *
 */
public enum LifePattern {

    /**
     * The new life depends on the damage.
     */
    WITH_LIFE((life, damage) -> (life - damage > 0 ? life - damage : 0)),

    /**
     * The new life not depends on the damage.
     */
    WITHOUT_LIFE((life, damage) -> life);

    private final BiFunction<Integer, Integer, Integer> function;

    LifePattern(final BiFunction<Integer, Integer, Integer> function) {
        this.function = function;
    }

    /**
     * Get the function in order apply that.
     * 
     * @return : the pattern that you used.
     */
    public BiFunction<Integer, Integer, Integer> getFunction() {
        return this.function;
    }
}
