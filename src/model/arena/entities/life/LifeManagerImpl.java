package model.arena.entities.life;

/**
 * This implemtation of the interface @LifeManager use a @LifePattern that
 * indicates the Strategy of the subtract damage.
 * 
 * @author josephgiovanelli
 *
 */
public class LifeManagerImpl implements LifeManager {

    private int life;
    private final LifePattern lifePattern;

    /**
     * This constructor want the initial life and the pattern.
     * @param life : the initial life.
     * @param lifePattern : the Strategy that you want to use.
     */
    public LifeManagerImpl(final int life, final LifePattern lifePattern) {
        this.life = life;
        this.lifePattern = lifePattern;
    }

    
    @Override
    public int getLife() {
        return this.life;
    }

    
    @Override
    public void setLife(final int damage) {
        this.life = lifePattern.getFunction().apply(this.life, damage);
    }

}
