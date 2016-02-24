package model;

public interface EntitiesVisitor {

    void visit(EntitiesImpl entitiesImpl);
    
    void visit(HeroImpl hero);
    
    void visit(Bullet bullet);
    
}
