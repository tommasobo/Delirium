package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ModelImpl implements Model{
    
    private static final ModelImpl singleton = new ModelImpl();
    private List<Entities> entities;
    
    private ModelImpl() {
        this.entities = new LinkedList<>();
    }
    
    public static ModelImpl getModel() {
        return singleton;
    }

    @Override
    public void notifyEvent(Directions direction) {
        this.entities.stream().filter(t -> t.getCode() == 0).forEach(t -> {
            t.setDirection(direction);
        });
    }
    
    @Override
    public void notifyEvent(Actions action) {
        this.entities.stream().filter(t -> t.getCode() == 0).forEach(t -> {
            t.setAction(action);
        });
    }
    
    public List<EntitiesInfo> updateArena() {
        
        List<EntitiesInfo> bullets = new LinkedList<>();
        
        this.entities.stream().forEach(t -> {
           Position p = t.getNextMove();
           t.setPosition(p.getPoint(), p.getDirection());
           Optional<EntitiesInfo> bullet = t.shoot();
           if(bullet.isPresent()) {
               bullets.add(bullet.get());
           }
           
        });
        
        return bullets;
        
        //MAGNI PART BEGIN
        
        //GESTION COLLISIONI
        
        //MAGNANI PART FINISH
        
    }


    //TODO cambia oggetto comunicazione con controller
    @Override
    public List<EntitiesInfo> getState() {
        final List<EntitiesInfo> result = new LinkedList<>();
        this.entities.stream().forEach(t -> {
            result.add(new EntitiesInfoImpl(t.getCode(), t.getLife(), t.getLifeManager(), null, t.getPosition(), t.getBounds(), t.getAction(), t.getSpeed(), t.isCanFly(), t.getContactDamage()));
        });
        return result;
    }

    @Override
    public void createArena(List<EntitiesInfo> entitiesInfo) {
        
        entitiesInfo.stream().forEach(t -> {
            switch (t.getMovementTypes()) {
            case HERO: 
                this.entities.add(new Hero(t.getCode(), t.getLife(), t.getLifemanager(), new HeroMovementManager(t.getPosition(), t.getBounds(), t.getAction(), t.getSpeed(), t.isCanFly()), new HeroShootManagerImpl(10), t.getContactDamage()));
                break;
            case STATIC : 
                this.entities.add(new EntitiesImpl(t.getCode(), t.getLife(), t.getLifemanager(), new ReactiveMovementManager(t.getPosition(), t.getBounds(), t.getAction(), t.getSpeed(), t.isCanFly()), new ShootManagerImpl(20), t.getContactDamage()));
                break;
            case RANDOM: 
                this.entities.add(new EntitiesImpl(t.getCode(), t.getLife(), t.getLifemanager(), new RandomDinamicMovementManager(t.getPosition(), t.getBounds(), t.getSpeed(), t.isCanFly(), t.getMovementTypes()), new ShootManagerImpl(20), t.getContactDamage()));
                break;
            case VERTICAL_LINEAR:
            case HORIZONTAL_LINEAR:
            	this.entities.add(new EntitiesImpl(t.getCode(), t.getLife(), t.getLifemanager(), new LinearDinamicMovementManager(t.getPosition(), t.getBounds(), t.getSpeed(), t.isCanFly(), t.getMovementTypes()), new ShootManagerImpl(20), t.getContactDamage()));
                break;
            }
            
        });
    }

    @Override
    public void putBullet(List<EntitiesInfo> entitiesInfo) {
        entitiesInfo.stream().forEach(t -> {
            this.entities.add(new EntitiesImpl(t.getCode(), t.getLife(), t.getLifemanager(), new LinearDinamicMovementManager(t.getPosition(), t.getBounds(), t.getSpeed(), t.isCanFly(), t.getMovementTypes()), new ShootManagerImpl(200000), t.getContactDamage()));
        });
        }
    
    

}
