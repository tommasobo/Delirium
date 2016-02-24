package model;

import java.util.Optional;

public class EntitiesInfoToControlImpl implements EntitiesInfoToControl {

    private final int code;
    private final int life;
    private final Position position;
    private final Actions action;
    private final Optional<Integer> speed;

    public EntitiesInfoToControlImpl(final int code, final int life, final Position position, final Actions action,
            final Optional<Integer> speed) {
        this.code = code;
        this.life = life;
        this.position = position;
        this.action = action;
        this.speed = speed;
    }

    public int getCode() {
        return this.code;
    }

    public int getLife() {
        return this.life;
    }

    public Position getPosition() {
        return this.position;
    }

    public Actions getAction() {
        return this.action;
    }

    public Optional<Integer> getSpeed() {
        return this.speed;
    }

}
