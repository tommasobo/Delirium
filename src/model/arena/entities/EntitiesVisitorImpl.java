package model.arena.entities;

import model.arena.Arena;

/**
 * This instance call the @Arena with the right method @add.
 * 
 * @author josephgiovanelli
 *
 */
public class EntitiesVisitorImpl implements EntitiesVisitor {
    private final Arena arena;

    /**
     * This constructor has to called by the @Arena.
     * 
     * @param arena
     *            : the arena that have to instance the entity
     */
    public EntitiesVisitorImpl(final Arena arena) {
        this.arena = arena;
    }

    @Override
    public void visit(final EntitiesImpl entitiesImpl) {
        this.arena.add(entitiesImpl);
    }

    @Override
    public void visit(final HeroImpl hero) {
        this.arena.add(hero);
    }

    @Override
    public void visit(final Bullet bullet) {
        this.arena.add(bullet);

    }

}
