package model;

public interface EntitiesVisitor {

    void visit(EntitiesImpl entitiesImpl);
    
    void visit(Hero hero);
    
    void visit(Bullet bullet);
    
}
