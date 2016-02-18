package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ModelImpl implements Model{
    
    private static final ModelImpl singleton = new ModelImpl();
    private Hero hero;
    private List<Entities> entities;
    
    private ModelImpl() {
        this.entities = new LinkedList<>();
    }
    
    public static ModelImpl getModel() {
        return singleton;
    }

    @Override
    public void notifyEvent(Directions direction) {
        if(hero.getMovementManager().isPresent()) {
            hero.getMovementManager().get().setPosition(hero.getPosition().getPoint(), direction);
        }
    }
    
    @Override
    public void notifyEvent(Actions action) {
        hero.setAction(action);
    }
    
    public List<EntitiesInfo> updateArena() {
        
        List<EntitiesInfo> bullets = new LinkedList<>();
        
        this.entities.stream().forEach(t -> {
           Optional<Position> p = !t.getMovementManager().isPresent() ? Optional.empty() : Optional.of(t.getMovementManager().get().getNextMove());
           if (p.isPresent()) {
               t.setPosition(p.get().getPoint(), p.get().getDirection());
           }
           Optional<EntitiesInfo> bullet = !t.getShootManager().isPresent() ? Optional.empty() : t.getShootManager().get().getBullet(t.getPosition());
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
            result.add(new EntitiesInfoImpl(t.getCode(), 10, null, null, t.getPosition(), null, t.getAction(), 10, false, 5));
        });
        return result;
    }

    @Override
    public void createArena(List<EntitiesInfo> entitiesInfo) {
        
        entitiesInfo.stream().forEach(t -> {
            switch (t.getMovementTypes()) {
            case HERO: 
                hero = new Hero(t.getCode(), Optional.of(new LifeManager(t.getLife())), new HeroMovementManager(t.getPosition(), t.getBounds(), t.getAction(), t.getSpeed(), t.isCanFly()), Optional.of(new HeroShootManagerImpl(10000)), Optional.of(t.getContactDamage()));
                this.entities.add(hero);
                break;
            case STATIC : 
                this.entities.add(new EntitiesImpl(t.getCode(), Optional.of(new LifeManager(t.getLife())), t.getPosition(), Optional.empty(), Optional.of(t.getContactDamage())));
                break;
            case RANDOM: 
                this.entities.add(new EntitiesImpl(t.getCode(), Optional.of(new LifeManager(t.getLife())), new RandomDinamicMovementManager(t.getPosition(), t.getBounds(), t.getSpeed(), t.isCanFly(), t.getMovementTypes()), Optional.of(new ShootManagerImpl(100)), Optional.of(t.getContactDamage())));
                break;
            case VERTICAL_LINEAR:
            case HORIZONTAL_LINEAR:
            	this.entities.add(new EntitiesImpl(t.getCode(), Optional.of(new LifeManager(t.getLife())), new LinearDinamicMovementManager(t.getPosition(), t.getBounds(), t.getSpeed(), t.isCanFly(), t.getMovementTypes()), Optional.of(new ShootManagerImpl(100)), Optional.of(t.getContactDamage())));
                break;
            }
            
        });
    }

    @Override
    public void putBullet(List<EntitiesInfo> entitiesInfo) {
        entitiesInfo.stream().forEach(t -> {
            this.entities.add(new EntitiesImpl(t.getCode(), Optional.of(new LifeManager(t.getLife())), new LinearDinamicMovementManager(t.getPosition(), t.getBounds(), t.getSpeed(), t.isCanFly(), t.getMovementTypes()), Optional.empty(), Optional.of(t.getContactDamage())));
        });
    }
    
    

}
