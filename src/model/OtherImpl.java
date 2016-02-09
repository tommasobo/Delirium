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

    /* (non-Javadoc)
     * @see model.Other#getLife()
     */
    @Override
    public int getLife() {
        return life;
    }

    /* (non-Javadoc)
     * @see model.Other#setLife(int)
     */
    @Override
    public void setLife(int life) {
        this.life = life;
    }

    /* (non-Javadoc)
     * @see model.Other#getCode()
     */
    @Override
    public int getCode() {
        return code;
    }

    /* (non-Javadoc)
     * @see model.Other#getLifeManager()
     */
    @Override
    public LifeManager getLifeManager() {
        return lifeManager;
    }

    /* (non-Javadoc)
     * @see model.Other#getMovementManager()
     */
    @Override
    public MovementManager getMovementManager() {
        return movementManager;
    }

    /* (non-Javadoc)
     * @see model.Other#getContactDamage()
     */
    @Override
    public Optional<Integer> getContactDamage() {
        return contactDamage;
    }
    
    

}
