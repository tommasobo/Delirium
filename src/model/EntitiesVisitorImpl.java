package model;

public class EntitiesVisitorImpl implements EntitiesVisitor {
    private final Arena arena;

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
