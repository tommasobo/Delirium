package control;

import java.util.function.Function;

/**
 * Enumeration that contains all game difficulties. Its elements contains
 * functions to elaborate some entities statistics
 * 
 * @author Matteo Magnani
 *
 */
public enum GameDifficulty {
    EASY(x -> (int) Math.round(x * 0.5), x -> (int) Math.round(x * 0.5), x -> (int) Math.round(x * 0.5)), 
    NORMAL(x -> (int) Math.round(x * 1),x -> (int) Math.round(x * 1), x -> (int) Math.round(x * 1)), 
    HARD(x -> (int) Math.round(x * 1.5), x -> (int) Math.round(x * 1.5), x -> (int) Math.round(x * 1.5)), 
    DELIRIUM(x -> (int) Math.round(x * 3), x -> (int) Math.round(x * 3), x -> (int) Math.round(x * 3));
    private final Function<Integer, Integer> speedIncrementer;
    private final Function<Integer, Integer> damageIncrementer;
    private final Function<Integer, Integer> lifeIncrementer;

    /**
     * 
     * @param speedIncrementer
     *            The function that increments entities' speed
     * @param damageIncrementer
     *            The function that increments entities' damage
     * @param lifeIncrementer
     *            The function that increments entities' life
     */
    private GameDifficulty(final Function<Integer, Integer> speedIncrementer,
            final Function<Integer, Integer> damageIncrementer, final Function<Integer, Integer> lifeIncrementer) {
        this.speedIncrementer = speedIncrementer;
        this.damageIncrementer = damageIncrementer;
        this.lifeIncrementer = lifeIncrementer;
    }

    /**
     * 
     * @return The function that increments entities' speed
     */
    public Function<Integer, Integer> getSpeedIncrementer() {
        return speedIncrementer;
    }

    /**
     * 
     * @return The function that increments entities' damage
     */
    public Function<Integer, Integer> getDamageIncrementer() {
        return damageIncrementer;
    }

    /**
     * 
     * @return The function that increments entities' life
     */
    public Function<Integer, Integer> getLifeIncrementer() {
        return lifeIncrementer;
    }

}
