package model.arena.entities.life;

public class LifeManagerImpl implements LifeManager {

    private int life;
    private final LifePattern lifePattern;

    public LifeManagerImpl(final int life, final LifePattern lifePattern) {
        this.life = life;
        this.lifePattern = lifePattern;
    }

    /* (non-Javadoc)
     * @see model.arena.entities.life.LifeManager#getLife()
     */
    @Override
    public int getLife() {
        return this.life;
    }

    /* (non-Javadoc)
     * @see model.arena.entities.life.LifeManager#setLife(int)
     */
    @Override
    public void setLife(final int damage) {
        this.life = lifePattern.getFunction().apply(this.life, damage);
    }

}
