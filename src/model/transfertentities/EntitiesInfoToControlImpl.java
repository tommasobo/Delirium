package model.transfertentities;

import java.util.Optional;

import model.arena.entities.Position;
import model.arena.utility.Actions;
/**
 * This class is the implementation of the the @EntitiesInfoToControl.
 * 
 * @author josephgiovanelli
 *
 */
public class EntitiesInfoToControlImpl implements EntitiesInfoToControl {

    private final int code;
    private final int life;
    private final Position position;
    private final Actions action;
    private final Optional<Integer> speed;

    /**
     * This constructor initialize each field of the object.
     * @param code : the code of the entity.
     * @param position : the position of the entity.
     * @param life : the life of the entity.
     * @param action : the action of the entity.
     * @param speed : the speed of the entity.
     */
    public EntitiesInfoToControlImpl(final int code, final int life, final Position position, final Actions action,
            final Optional<Integer> speed) {
        this.code = code;
        this.life = life;
        this.position = position;
        this.action = action;
        this.speed = speed;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Actions getAction() {
        return this.action;
    }

    @Override
    public Optional<Integer> getSpeed() {
        return this.speed;
    }

}
