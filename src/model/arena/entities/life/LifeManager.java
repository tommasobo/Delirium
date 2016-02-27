package model.arena.entities.life;

/**
 * This interface manage the life of the entity.
 * 
 * @author josephgiovanelli
 *
 */
public interface LifeManager {

    /**
     * This method get the life of the entity.
     * 
     * @return : the life that you ask.
     */
    int getLife();

    /**
     * This method set the life of the entity.
     * 
     * @param damage
     *            : the value that you want to subtract at the life.
     */
    void setLife(int damage);

}