package model.arena.entities;

/**
 * This is the core of the Visitor Pattern that recognize the entity that call
 * the method visit and then communicate with the @Arena.
 * 
 * @author josephgiovanelli
 *
 */
public interface EntitiesVisitor {

    /**
     * This method is called by the @EntitiesImpl instance.
     * 
     * @param entitiesImpl
     *            : the entity that call this method.
     */
    void visit(final EntitiesImpl entitiesImpl);

    /**
     * This method is called by the @HeroImpl instance.
     * 
     * @param hero
     *            : the entity that call this method.
     */
    void visit(final HeroImpl hero);

    /**
     * This method is called by the @Bullet instance.
     * 
     * @param bullet
     *            : the entity that call this method.
     */
    void visit(final Bullet bullet);

}
