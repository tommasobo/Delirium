package control;

import java.util.Optional;

import model.EntitiesInfo;
import model.LifePattern;
import model.MovementInfo;
import model.MovementInfoImpl;
import model.Position;
import model.ShootInfo;
import model.ShootInfoImpl;

public class EntitiesInfoStore implements EntitiesInfo{

    private int code;
    private final Position position;
    private final Optional<MovementInfoImpl> movementInfo; 
    private final int life;
    private final LifePattern lifePattern;
    private final Optional<ShootInfoImpl> shootInfo;
    private final Optional<Integer> contactDamage;
    private final view.Entities entityType;
    
    public EntitiesInfoStore(int code, Position position, Optional<MovementInfoImpl> movementInfo, int life,
            LifePattern lifePattern, Optional<ShootInfoImpl> shootInfo, Optional<Integer> contactDamage, view.Entities entityType) {
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
        return position;
    }

    public Optional<MovementInfo> getMovementInfo() {
        return Optional.ofNullable(this.movementInfo.orElse(null));
    }

    public int getLife() {
        return life;
    }

    public LifePattern getLifePattern() {
        return lifePattern;
    }

    public Optional<ShootInfo> getShootInfo() {
        return Optional.ofNullable(this.shootInfo.orElse(null));
    }

    public Optional<Integer> getContactDamage() {
        return contactDamage;
    }

    public view.Entities getEntityType() {
        return entityType;
    }
}
