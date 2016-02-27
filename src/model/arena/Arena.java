package model.arena;

import java.util.List;

import model.arena.entities.Bullet;
import model.arena.entities.Entities;
import model.arena.entities.EntitiesImpl;
import model.arena.entities.Hero;
import model.arena.entities.HeroImpl;

/**
 * This Interface contains any entities and stores also in another field the
 * hero, the goal and the various bullet in order to manage better them by
 * the @ArenaManager. This interface used the Visitor pattern in order to
 * understand the entity that the model sent to the arena and put it in the
 * right field.
 * 
 * @author josephgiovanelli
 *
 */
public interface Arena {

    /**
     * With this method the Model can add any entity. This method call another
     * object, called @EntitiesVisitor, that recognize the instance of the
     * entity and call the right method @add
     * 
     * @param entities
     *            : a generic entity.
     */
    void add(final Entities entities);

    /**
     * This method is the specific method that @EntitiesVisitor called in order
     * to put an entity that isn't the hero, or a bullet.
     * 
     * @param entitiesImpl : the entity that are passed.
     */
    void add(final EntitiesImpl entitiesImpl);

    /**
     * This method is the specific method that @EntitiesVisitor called in order
     * to put the hero.
     * 
     * @param hero : the hero that are passed.
     */
    void add(final HeroImpl hero);

    /**
     * This method is the specific method that @EntitiesVisitor called in order
     * to put a bullet.
     * 
     * @param bullet : the bullet that are passed.
     */
    void add(final Bullet bullet);

    /**
     * With this method the @Model or the @ArenaManager can get the hero.
     * 
     * @return : the hero that you want.
     */
    Hero getHero();

    /**
     * With this method the @Model or the @ArenaManager can get the goal.
     * 
     * @return : the goal that you want.
     */
    Entities getGoal();

    /**
     * With this method the @Model or the @ArenaManager can get the entities.
     * 
     * @return : the entities that you want.
     */
    List<Entities> getEntities();

    /**
     * With this method the @Model or the @ArenaManager can get the bullets.
     * 
     * @return : the bullets that you want.
     */
    List<Bullet> getBullets();

}