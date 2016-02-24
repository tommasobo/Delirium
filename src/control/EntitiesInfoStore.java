package control;

import java.util.Optional;

import model.EntitiesInfo;
import model.LifePattern;
import model.MovementInfo;
import model.Position;
import model.ShootInfo;

public class EntitiesInfoStore implements EntitiesInfo{

    private int code;
    private Position position;
    private Optional<MovementInfoStore> movementInfo; 
    private int life;
    private LifePattern lifePattern;
    private Optional<ShootInfoStore> shootInfo;
    private Optional<Integer> contactDamage;
    private view.Entities entityType;
    
    public EntitiesInfoStore(int code, Position position, Optional<MovementInfoStore> movementInfo, int life,
            LifePattern lifePattern, Optional<ShootInfoStore> shootInfo, Optional<Integer> contactDamage, view.Entities entityType) {
        this.code = code;
        this.position = position;
        this.movementInfo = movementInfo;
        this.life = life;
        this.lifePattern = lifePattern;
        this.shootInfo = shootInfo;
        this.contactDamage = contactDamage;
        this.entityType = entityType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Position getPosition() {
        return new Position(this.position.getPoint(), this.position.getDirection(), this.position.getDimension());
    }

    public Optional<MovementInfo> getMovementInfo() {
        return this.movementInfo.isPresent() ? Optional.of(this.movementInfo.get().getCopy()) : Optional.empty();
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
        return this.shootInfo.isPresent() ? Optional.of(this.shootInfo.get().getCopy()) : Optional.empty();
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
    
    public void setPosition(Position position) {
        this.position = position;
    }

    public void setMovementInfo(Optional<MovementInfoStore> movementInfo) {
        this.movementInfo = movementInfo;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setLifePattern(LifePattern lifePattern) {
        this.lifePattern = lifePattern;
    }

    public void setShootInfo(Optional<ShootInfoStore> shootInfo) {
        this.shootInfo = shootInfo;
    }

    public void setContactDamage(Optional<Integer> contactDamage) {
        this.contactDamage = contactDamage;
    }

    public void setEntityType(view.Entities entityType) {
        this.entityType = entityType;
    }

    public EntitiesInfoStore getCopy() {
        return new EntitiesInfoStore(this.code, this.getPosition(),
                this.movementInfo.isPresent() ? Optional.of(this.movementInfo.get().getCopy()) : Optional.empty(),
                this.life, this.lifePattern,
                this.shootInfo.isPresent() ? Optional.of(this.shootInfo.get().getCopy()) : Optional.empty(),
                this.contactDamage, this.entityType);
    }
}
