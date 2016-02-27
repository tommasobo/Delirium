package model.arena.entities;

/**
 * This interface added only one method because the hero implements both
 * interfaces and this is only for him.
 * 
 * @author josephgiovanelli
 *
 */
public interface Hero extends Entities {

    /**
     * This method allow you to set if the hero is on a platform or not.
     * 
     * @param bool
     *            : if is on platform.
     */
    void setOnPlatform(final boolean bool);

}