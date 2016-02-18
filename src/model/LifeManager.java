package model;

public class LifeManager {
    
    private int life;
    private final LifePattern lifePattern;

    public LifeManager(int life, LifePattern lifePattern) {
        this.life = life;
        this.lifePattern = lifePattern;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int damage) {
        this.life = lifePattern.getFunction().apply(this.life, damage);
    }
    
    
    
    

}
