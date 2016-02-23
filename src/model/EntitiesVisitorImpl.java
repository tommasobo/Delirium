package model;

public class EntitiesVisitorImpl implements EntitiesVisitor {
    private Arena arena;
    
    public EntitiesVisitorImpl(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void visit(EntitiesImpl entitiesImpl) {
        arena.add(entitiesImpl);
    }

    @Override
    public void visit(Hero hero) {
        arena.add(hero);
    }

    @Override
    public void visit(Bullet bullet) {
        arena.add(bullet);

    }

}
