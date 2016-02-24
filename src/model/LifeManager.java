package model;

public class LifeManager {

    private int life;
    private final LifePattern lifePattern;

    public LifeManager(final int life, final LifePattern lifePattern) {
        this.life = life;
        this.lifePattern = lifePattern;
    }

    public int getLife() {
        return this.life;
    }

    public void setLife(final int damage) {
        this.life = lifePattern.getFunction().apply(this.life, damage);
    }

}
