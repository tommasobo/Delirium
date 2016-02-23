package model;

import java.util.Optional;

import control.Point;

public interface Entities {
    
    int getCode();
    
    Position getPosition();
    
    void setPosition(final Point point, final Directions direction);
    
    LifeManager getLifeManager();
    
    Optional<MovementManager> getMovementManager();
    
    Optional<ShootManager> getShootManager();
    
    Optional<Integer> getContactDamage();

    Actions getAction();
    
    void setAction(Actions action);
    
    void accept(EntitiesVisitor visitor);
    
    
    
    public static class Builder {
        private int code;
        private Optional<Position> position = Optional.empty();
        private LifeManager lifeManager;
        private Optional<MovementManager> movementManager = Optional.empty();
        private Optional<ShootManager> shootManager = Optional.empty();
        private Optional<Integer> contactDamage = Optional.empty();
        
        public Builder code (final int code){
            this.code = code;
            return this ;
        }
        
        public Builder position (final Position position){
            this.position = Optional.ofNullable(position);
            return this;
        }
        
        public Builder lifeManager (final LifeManager lifeManager){
            this.lifeManager = lifeManager;
            return this ;
        }
        
        public Builder movementManager (final MovementManager movementManager){
            this.movementManager = Optional.ofNullable(movementManager);
            return this;
        }
        
        public Builder shootManager (final ShootManager shootManager){
            this.shootManager = Optional.ofNullable(shootManager);
            return this;
        }
        
        public Builder contactDamage (final Integer contactDamage){
            this.contactDamage = Optional.ofNullable(contactDamage);
            return this;
        }
        
        
        public Entities build() throws IllegalStateException {
            
            if (this.code >= 0 && this.lifeManager == null && !this.position.isPresent() &&
                    this.movementManager.isPresent() && !this.shootManager.isPresent() && 
                    this.contactDamage.isPresent()) {
                return new Bullet(this.code, this.movementManager.get(), this.contactDamage.get());
            }
            
            if (this.code == 0 && this.lifeManager != null && !this.position.isPresent() &&
                    this.movementManager.isPresent() && this.shootManager.isPresent() && 
                    this.contactDamage.isPresent()) {
                return new Hero(this.code, this.lifeManager, this.movementManager.get(), this.shootManager.get(), this.contactDamage.get());
            }
            
            if (this.code < 0 || this.lifeManager == null || 
                    (!this.position.isPresent() && !this.movementManager.isPresent()) || 
                    (this.position.isPresent() && this.movementManager.isPresent())) {
                throw new IllegalStateException();
            }
            
            if (this.position.isPresent()) {
                return new EntitiesImpl(this.code, this.lifeManager, this.position.get(), this.shootManager, this.contactDamage);
            } else {
                return new EntitiesImpl(this.code, this.lifeManager, this.movementManager.get(), this.shootManager, this.contactDamage);
            }
            
        }
  
    }
    
}
