package control;

import java.util.function.Function;

public enum GameDifficulty {
    EASY(x -> (int) (x*0.5), x -> (int) (x*0.5),x -> (int) (x*0.5)),
    NORMAL(x -> (int) (x*1), x -> (int) (x*1),x -> (int) (x*1)),
    HARD(x -> (int) (x*1.5), x -> (int) (x*1.5),x -> (int) (x*1.5)),
    DELIRIUM(x -> (int) (x*3), x -> (int) (x*3),x -> (int) (x*3));
    private final Function<Integer, Integer> speedIncrementer;
    private final Function<Integer, Integer> damageIncrementer;
    private final Function<Integer, Integer> lifeIncrementer;
    private GameDifficulty(final Function<Integer, Integer> speedIncrementer, final Function<Integer, Integer> damageIncrementer,
            final Function<Integer, Integer> lifeIncrementer) {
        this.speedIncrementer = speedIncrementer;
        this.damageIncrementer = damageIncrementer;
        this.lifeIncrementer = lifeIncrementer;
    }
    public Function<Integer, Integer> getSpeedIncrementer() {
        return speedIncrementer;
    }
    public Function<Integer, Integer> getDamageIncrementer() {
        return damageIncrementer;
    }
    public Function<Integer, Integer> getLifeIncrementer() {
        return lifeIncrementer;
    }
    
    
    
    
}
