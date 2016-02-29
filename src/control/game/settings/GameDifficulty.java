package control.game.settings;

import java.util.function.Function;

/**
 * Enumeration that contains all game's difficulties. Its elements contain
 * functions to elaborate some entities' statistics
 * 
 * @author Matteo Magnani
 *
 */
public enum GameDifficulty {
    EASY(x -> (int) Math.round(x * 1.5), x -> (int) Math.round(x * 0.5), x -> (int) Math.round(x * 0.5)), 
    NORMAL(x -> (int) Math.round(x * 1),x -> (int) Math.round(x * 1), x -> (int) Math.round(x * 1)), 
    HARD(x -> (int) Math.round(x * 0.7), x -> (int) Math.round(x * 1.5), x -> (int) Math.round(x * 1.5)), 
    DELIRIUM(x -> (int) Math.round(x * 0.5), x -> (int) Math.round(x * 3), x -> (int) Math.round(x * 3));
    private final Function<Integer, Integer> fireRatioIncrementer;
    private final Function<Integer, Integer> damageIncrementer;
    private final Function<Integer, Integer> lifeIncrementer;

    /**
     * 
     * @param fireRatioIncrementer
     *            The function that increments entities' speed
     * @param damageIncrementer
     *            The function that increments entities' damage
     * @param lifeIncrementer
     *            The function that increments entities' life
     */
    GameDifficulty(final Function<Integer, Integer> fireRatioIncrementer,
            final Function<Integer, Integer> damageIncrementer, final Function<Integer, Integer> lifeIncrementer) {
        this.fireRatioIncrementer = fireRatioIncrementer;
        this.damageIncrementer = damageIncrementer;
        this.lifeIncrementer = lifeIncrementer;
    }

    /**
     * 
     * @return The function that increments entity's fire ratio
     */
    public Function<Integer, Integer> getFireRatioIncrementer() {
        return this.fireRatioIncrementer;
    }

    /**
     * 
     * @return The function that increments entity's damage
     */
    public Function<Integer, Integer> getDamageIncrementer() {
        return this.damageIncrementer;
    }

    /**
     * 
     * @return The function that increments entity's life
     */
    public Function<Integer, Integer> getLifeIncrementer() {
        return this.lifeIncrementer;
    }

}
