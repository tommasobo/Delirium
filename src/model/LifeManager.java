package model;

public class LifeManager {
    
    private int life;

    public LifeManager(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int damage) {
        this.life = life - damage > 0 ? life - damage : 0;
    }
    
    
    
    

}
