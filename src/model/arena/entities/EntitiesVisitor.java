package model.arena.entities;

public interface EntitiesVisitor {

    void visit(final EntitiesImpl entitiesImpl);

    void visit(final HeroImpl hero);

    void visit(final Bullet bullet);

}
