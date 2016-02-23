package model;

import java.util.LinkedList;
import java.util.List;

public class Arena {
    private Hero hero;
    private Entities goal;
    private List<Entities> entities;
    private List<Bullet> bullets;
    
    private EntitiesVisitor addVisitor = new EntitiesVisitorImpl(this);
    
    
    
    public Arena() {
        this.entities = new LinkedList<>();
        this.bullets = new LinkedList<>();
    }

    public void add(Entities entities) {
        entities.accept(addVisitor);
    }
    
    public void add(EntitiesImpl entitiesImpl) {
        this.entities.add(entitiesImpl);
        if (entitiesImpl.getCode() == -1) {
            this.goal = entitiesImpl;
        }
    }

    public void add(Hero hero) {
        this.hero = hero;
        this.entities.add(hero);
    }

    public void add(Bullet bullet) {
        this.bullets.add(bullet);
    }

    public Hero getHero() {
        return hero;
    }

    public Entities getGoal() {
        return goal;
    }

    public List<Entities> getEntities() {
        return entities;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
    
    
    
    

    
}
