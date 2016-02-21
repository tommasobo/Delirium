package control;

import model.LifePattern;
import model.Position;

public class EntitiesInfoStore {

    private int code;
    private Position position;
    private MovementInfoStore movementInfo; 
    private int life;
    private LifePattern lifePattern;
    private ShootInfoStore shootInfo;
    private Integer contactDamage;
    
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
    public MovementInfoStore getMovementInfo() {
        return movementInfo;
    }
    public void setMovementInfo(MovementInfoStore movementInfo) {
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
    public ShootInfoStore getShootInfo() {
        return shootInfo;
    }
    public void setShootInfo(ShootInfoStore shootInfo) {
        this.shootInfo = shootInfo;
    }
    public Integer getContactDamage() {
        return contactDamage;
    }
    public void setContactDamage(Integer contactDamage) {
        this.contactDamage = contactDamage;
    }
    
    
    
    
}
