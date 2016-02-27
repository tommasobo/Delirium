package model.transfertentities;

import java.util.Optional;

import model.arena.entities.Position;
import model.arena.entities.life.LifePattern;

/**
 * This class is the implementation of the the @EntitiesInfo.
 * 
 * @author josephgiovanelli
 *
 */
public class EntitiesInfoImpl implements EntitiesInfo {

    private final int code;
    private final Position position;
    private final Optional<MovementInfo> movementInfo;
    private final int life;
    private final LifePattern lifePattern;
    private final Optional<ShootInfo> shootInfo;
    private final Optional<Integer> contactDamage;

    /**
     * This constructor initialize each field of the object.
     * @param code : the code of the entity.
     * @param position : the position of the entity.
     * @param movementInfo : the information of the movement of the entity.
     * @param life : the life of the entity.
     * @param lifePattern : the pattern of life of the entity.
     * @param shootInfo : the shoot information of the entity.
     * @param contactDamage : how much hurt the entity at the contact.
     */
    public EntitiesInfoImpl(final int code, final Position position, final Optional<MovementInfo> movementInfo,
            final int life, final LifePattern lifePattern, final Optional<ShootInfo> shootInfo,
            final Optional<Integer> contactDamage) {
        this.code = code;
        this.position = position;
        this.movementInfo = movementInfo;
        this.life = life;
        this.lifePattern = lifePattern;
        this.shootInfo = shootInfo;
        this.contactDamage = contactDamage;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public Optional<MovementInfo> getMovementInfo() {
        return this.movementInfo;
    }

    @Override
    public Position getPosition() {
        return new Position(this.position.getPoint(), this.position.getDirection(), this.position.getDimension());
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public LifePattern getLifePattern() {
        return this.lifePattern;
    }

    @Override
    public Optional<ShootInfo> getShootInfo() {
        return this.shootInfo;
    }

    @Override
    public Optional<Integer> getContactDamage() {
        return this.contactDamage;
    }

}
