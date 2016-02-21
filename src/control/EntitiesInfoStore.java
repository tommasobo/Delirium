package control;

import java.util.Optional;

import model.EntitiesInfo;
import model.LifePattern;
import model.MovementInfo;
import model.Position;
import model.ShootInfo;

public class EntitiesInfoStore implements EntitiesInfo {

    private int code;
    private Position position;
    private Optional<MovementInfo> movementInfo; 
    private int life;
    private LifePattern lifePattern;
    private Optional<ShootInfo> shootInfo;
    private Optional<Integer> contactDamage;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Optional<MovementInfo> getMovementInfo() {
        return movementInfo;
    }
    public void setMovementInfo(Optional<MovementInfo> movementInfo) {
        this.movementInfo = movementInfo;
    }
    public int getLife() {
        return life;
    }
    public void setLife(int life) {
        this.life = life;
    }
    public LifePattern getLifePattern() {
        return lifePattern;
    }
    public void setLifePattern(LifePattern lifePattern) {
        this.lifePattern = lifePattern;
    }
    public Optional<ShootInfo> getShootInfo() {
        return shootInfo;
    }
    public void setShootInfo(Optional<ShootInfo> shootInfo) {
        this.shootInfo = shootInfo;
    }
    public Optional<Integer> getContactDamage() {
        return contactDamage;
    }
    public void setContactDamage(Optional<Integer> contactDamage) {
        this.contactDamage = contactDamage;
    }
    
    
}
