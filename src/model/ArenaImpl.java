package model;

import java.util.LinkedList;
import java.util.List;

public class ArenaImpl implements Arena {
    private Hero hero;
    private Entities goal;
    private List<Entities> entities;
    private List<Bullet> bullets;

    private final EntitiesVisitor addVisitor = new EntitiesVisitorImpl(this);

    public ArenaImpl() {
        this.entities = new LinkedList<>();
        this.bullets = new LinkedList<>();
    }

    @Override
    public void add(Entities entities) {
        entities.accept(addVisitor);
    }

    @Override
    public void add(final EntitiesImpl entitiesImpl) {
        this.entities.add(entitiesImpl);
        if (entitiesImpl.getCode() == -1) {
            this.goal = entitiesImpl;
        }
    }

    @Override
    public void add(final HeroImpl hero) {
        this.hero = hero;
        this.entities.add(hero);
    }

    @Override
    public void add(final Bullet bullet) {
        this.bullets.add(bullet);
    }

    @Override
    public Hero getHero() {
        return this.hero;
    }

    @Override
    public Entities getGoal() {
        return this.goal;
    }

    @Override
    public List<Entities> getEntities() {
        return this.entities;
    }

    @Override
    public List<Bullet> getBullets() {
        return this.bullets;
    }

}
