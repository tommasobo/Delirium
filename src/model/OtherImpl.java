package model;

import java.util.Optional;

public class OtherImpl implements Other{
    
    private final int code;
    private int life;
    private final LifeManager lifeManager;
    private final MovementManager movementManager;
    private final Optional<Integer> contactDamage;
    
    public OtherImpl(int code, int life, LifeManager lifeManager, MovementManager movementManager, Optional<Integer> contactDamage) {
        this.code = code;
        this.life = life;
        this.lifeManager = lifeManager;
        this.movementManager = movementManager;
        this.contactDamage = contactDamage;
    }

    @Override
    public int getLife() {
        return life;
    }

    
    @Override
    public void setLife(int life) {
        this.life = life;
    }

    
    @Override
    public int getCode() {
        return code;
    }

    
    @Override
    public LifeManager getLifeManager() {
        return lifeManager;
    }

    
    @Override
    public MovementManager getMovementManager() {
        return movementManager;
    }

    
    @Override
    public Optional<Integer> getContactDamage() {
        return contactDamage;
    }
    
    

}
