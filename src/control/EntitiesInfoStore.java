package control;

import java.util.Optional;

import model.arena.entities.Position;
import model.arena.entities.life.LifePattern;
import model.transfertentities.EntitiesInfo;
import model.transfertentities.MovementInfo;
import model.transfertentities.ShootInfo;

public class EntitiesInfoStore implements EntitiesInfo{

    private int code;
    private Position position;
    private Optional<MovementInfoStore> movementInfo; 
    private int life;
    private LifePattern lifePattern;
    private Optional<ShootInfoStore> shootInfo;
    private Optional<Integer> contactDamage;
    private view.Entities entityType;
    
    public EntitiesInfoStore(final int code, final Position position, final Optional<MovementInfoStore> movementInfo, final int life,
            final LifePattern lifePattern, final Optional<ShootInfoStore> shootInfo, final Optional<Integer> contactDamage, final view.Entities entityType) {
        this.code = code;
        this.position = position;
        this.movementInfo = movementInfo;
        this.life = life;
        this.lifePattern = lifePattern;
        this.shootInfo = shootInfo;
        this.contactDamage = contactDamage;
        this.entityType = entityType;
    }
    
    public EntitiesInfoStore(final EntitiesInfoStore e) {
        this.code = e.getCode();
        this.position = e.getPosition();
        this.movementInfo = e.getMovementInfoStore().isPresent() ? Optional.of(new MovementInfoStore(e.getMovementInfoStore().get())) : Optional.empty();
        this.life = e.getLife();
        this.lifePattern = e.getLifePattern();
        this.shootInfo = e.getShootInfoStore().isPresent() ? Optional.of(new ShootInfoStore(e.getShootInfoStore().get())) : Optional.empty();
        this.contactDamage = e.getContactDamage();
        this.entityType = e.getEntityType();
    }

    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public Position getPosition() {
        return new Position(this.position.getPoint(), this.position.getDirection(), this.position.getDimension());
    }

    public Optional<MovementInfo> getMovementInfo() {
        return this.movementInfo.isPresent() ? Optional.of(new MovementInfoStore(this.movementInfo.get())) : Optional.empty();
    }
    
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
    
    public Optional<ShootInfoStore> getShootInfoStore() {
        return this.shootInfo;
    }

    public Optional<Integer> getContactDamage() {
        return contactDamage;
    }

    public view.Entities getEntityType() {
        return entityType;
    }
    
    public void setPosition(final Position position) {
        this.position = position;
    }

    public void setMovementInfo(final Optional<MovementInfoStore> movementInfo) {
        this.movementInfo = movementInfo;
    }

    public void setLife(final int life) {
        this.life = life;
    }

    public void setLifePattern(final LifePattern lifePattern) {
        this.lifePattern = lifePattern;
    }

    public void setShootInfo(final Optional<ShootInfoStore> shootInfo) {
        this.shootInfo = shootInfo;
    }

    public void setContactDamage(final Optional<Integer> contactDamage) {
        this.contactDamage = contactDamage;
    }

    public void setEntityType(final view.Entities entityType) {
        this.entityType = entityType;
    }
    
}
