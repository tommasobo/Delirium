package control.fileloading.levels.storestructures;

import java.util.Optional;

import model.arena.entities.Position;
import model.arena.entities.life.LifePattern;
import model.transfertentities.MovementInfo;
import model.transfertentities.ShootInfo;

/**
 * Implementation of EntitiesInfoStore with protected getters and a clone
 * constructor
 * 
 * @author Matteo Magnani
 *
 */
public class EntitiesInfoStoreImpl implements EntitiesInfoStore {

    private int code;
    private Position position;
    private Optional<MovementInfoStore> movementInfo;
    private int life;
    private LifePattern lifePattern;
    private Optional<ShootInfoStore> shootInfo;
    private Optional<Integer> contactDamage;
    private view.configs.Entities entityType;

    /**
     * 
     * @param code
     *            Entity's code
     * @param position
     *            Entity's position
     * @param movementInfo
     *            Entity's movement informations, optional empty if the entity
     *            cannot move
     * @param life
     *            Entity's life amount
     * @param lifePattern
     *            Entity's life pattern
     * @param shootInfo
     *            Entity's shoot informations, optional empty if the entity
     *            cannot fire
     * @param contactDamage
     *            Entity's contact damage amount, optional empty if the entity
     *            not have contact damage
     * @param entityType
     *            The view representation of the entity
     */
    public EntitiesInfoStoreImpl(final int code, final Position position,
            final Optional<MovementInfoStore> movementInfo, final int life, final LifePattern lifePattern,
            final Optional<ShootInfoStore> shootInfo, final Optional<Integer> contactDamage,
            final view.configs.Entities entityType) {
        this.code = code;
        this.position = position;
        this.movementInfo = movementInfo;
        this.life = life;
        this.lifePattern = lifePattern;
        this.shootInfo = shootInfo;
        this.contactDamage = contactDamage;
        this.entityType = entityType;
    }

    /**
     * 
     * @param e
     *            EntitiesInfoStore to copy
     */
    public EntitiesInfoStoreImpl(final EntitiesInfoStore e) {
        this.code = e.getCode();
        this.position = e.getPosition();
        this.movementInfo = e.getMovementInfoStore().isPresent()
                ? Optional.of(new MovementInfoStore(e.getMovementInfoStore().get())) : Optional.empty();
        this.life = e.getLife();
        this.lifePattern = e.getLifePattern();
        this.shootInfo = e.getShootInfoStore().isPresent()
                ? Optional.of(new ShootInfoStore(e.getShootInfoStore().get())) : Optional.empty();
        this.contactDamage = e.getContactDamage();
        this.entityType = e.getEntityType();
    }

    public int getCode() {
        return code;
    }

    @Override
    public void setCode(final int code) {
        this.code = code;
    }

    public Position getPosition() {
        return new Position(this.position.getPoint(), this.position.getDirection(), this.position.getDimension());
    }

    public Optional<MovementInfo> getMovementInfo() {
        return this.movementInfo.isPresent() ? Optional.of(new MovementInfoStore(this.movementInfo.get()))
                : Optional.empty();
    }

    @Override
    public Optional<MovementInfoStore> getMovementInfoStore() {
        return this.movementInfo;
    }

    public int getLife() {
        return life;
    }

    public LifePattern getLifePattern() {
        return lifePattern;
    }

    public Optional<ShootInfo> getShootInfo() {
        return this.shootInfo.isPresent() ? Optional.of(new ShootInfoStore(this.shootInfo.get())) : Optional.empty();
    }

    @Override
    public Optional<ShootInfoStore> getShootInfoStore() {
        return this.shootInfo;
    }

    public Optional<Integer> getContactDamage() {
        return contactDamage;
    }

    public view.configs.Entities getEntityType() {
        return entityType;
    }

    @Override
    public void setPosition(final Position position) {
        this.position = position;
    }

    /*
     * (non-Javadoc)
     * 
     * @see control.EntitiesInfoStore#setMovementInfo(java.util.Optional)
     */
    @Override
    public void setMovementInfo(final Optional<MovementInfoStore> movementInfo) {
        this.movementInfo = movementInfo;
    }

    @Override
    public void setLife(final int life) {
        this.life = life;
    }

    @Override
    public void setLifePattern(final LifePattern lifePattern) {
        this.lifePattern = lifePattern;
    }

    @Override
    public void setShootInfo(final Optional<ShootInfoStore> shootInfo) {
        this.shootInfo = shootInfo;
    }

    @Override
    public void setContactDamage(final Optional<Integer> contactDamage) {
        this.contactDamage = contactDamage;
    }

    @Override
    public void setEntityType(final view.configs.Entities entityType) {
        this.entityType = entityType;
    }

}
