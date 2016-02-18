package model;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import control.Point;

public class ModelImpl implements Model{
    
    private static final ModelImpl singleton = new ModelImpl();
    private Hero hero;
    private List<Entities> entities;
    private Map<Integer,Position> lastPositions;
    
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
        this.lastPositions = new HashMap<>();
        for(Entities t : entities) {
           //TODO se non usi piu la mappa cambiala con una variabile temporanea
           lastPositions.put(t.getCode(), t.getPosition());
           Optional<Position> p = !t.getMovementManager().isPresent() ? Optional.empty() : Optional.of(t.getMovementManager().get().getNextMove());
           if (p.isPresent()) {
               //t.setPosition(p.get().getPoint(), p.get().getDirection());
               //collisionFixer(p.get(), t);
               Position pos = collisionFixer(p.get(), t);
               t.setPosition(pos.getPoint(), pos.getDirection());
               
           }
           Optional<EntitiesInfo> bullet = !t.getShootManager().isPresent() ? Optional.empty() : t.getShootManager().get().getBullet(t.getPosition());
           if(bullet.isPresent()) {
               bullets.add(bullet.get());
           }
           
        }
        
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
                hero = new Hero(t.getCode(), new LifeManager(t.getLife(), LifePattern.WITH_LIFE), new HeroMovementManager(t.getPosition(), t.getBounds(), t.getAction(), t.getSpeed(), t.isCanFly()), Optional.of(new HeroShootManagerImpl(10000)), Optional.of(t.getContactDamage()));
                this.entities.add(hero);
                break;
            case STATIC : 
                this.entities.add(new EntitiesImpl(t.getCode(), new LifeManager(t.getLife(), LifePattern.WITH_LIFE), t.getPosition(), Optional.empty(), Optional.of(t.getContactDamage())));
                break;
            case RANDOM: 
                this.entities.add(new EntitiesImpl(t.getCode(), new LifeManager(t.getLife(), LifePattern.WITH_LIFE), new RandomDinamicMovementManager(t.getPosition(), t.getBounds(), t.getSpeed(), t.isCanFly(), t.getMovementTypes()), Optional.of(new ShootManagerImpl(100)), Optional.of(t.getContactDamage())));
                break;
            case VERTICAL_LINEAR:
            case HORIZONTAL_LINEAR:
            	this.entities.add(new EntitiesImpl(t.getCode(), new LifeManager(t.getLife(), LifePattern.WITH_LIFE), new LinearDinamicMovementManager(t.getPosition(), t.getBounds(), t.getSpeed(), t.isCanFly(), t.getMovementTypes()), Optional.of(new ShootManagerImpl(100)), Optional.of(t.getContactDamage())));
                break;
            }
            
        });
    }

    @Override
    public void putBullet(List<EntitiesInfo> entitiesInfo) {
        entitiesInfo.stream().forEach(t -> {
            this.entities.add(new EntitiesImpl(t.getCode(), new LifeManager(t.getLife(), LifePattern.WITH_LIFE), new LinearDinamicMovementManager(t.getPosition(), t.getBounds(), t.getSpeed(), t.isCanFly(), t.getMovementTypes()), Optional.empty(), Optional.of(t.getContactDamage())));
        });
    }
    
    private Position collisionFixer(Position pos, Entities entity) {
        Position posToFix = new Position(pos.getPoint(), pos.getDirection(), pos.getDimension());
        Rectangle retToFix = getRectangle(posToFix);
        List<Rectangle> collisionList = getCollisionList(retToFix, entity);
        for(Rectangle r: collisionList) {
           switch(entity.getAction()) {
            case JUMP:
                posToFix.setPoint(new Point(posToFix.getPoint().getX(), r.y - retToFix.height));
                break;
            case FALL:
                posToFix.setPoint(new Point(posToFix.getPoint().getX(), r.y + r.height));
                entity.setAction(Actions.STOP);
                if(entity == this.hero) {
                    hero.setOnPlatform(true);
                }
                break;
            case MOVE:
                switch(posToFix.getDirection()) {
                case LEFT:
                    posToFix.setPoint(new Point(r.x + r.width, posToFix.getPoint().getY()));
                    break;
                case NONE:
                    break;
                case RIGHT:
                    posToFix.setPoint(new Point(r.x - retToFix.width, posToFix.getPoint().getY()));
                    break;
                default:
                    break;
                
                }
                break;
            case MOVEONJUMP:
            case MOVEONFALL:
                if(!new Rectangle(retToFix.x, lastPositions.get(entity.getCode()).getPoint().getY(), retToFix.width, retToFix.height).intersects(r)) {
                    posToFix.setPoint(new Point(retToFix.x,  lastPositions.get(entity.getCode()).getPoint().getY()));
                } else {
                    switch(posToFix.getDirection()) {
                    case LEFT:
                        posToFix.setPoint(new Point(r.x + r.width, posToFix.getPoint().getY()));
                        break;
                    case NONE:
                        break;
                    case RIGHT:
                        posToFix.setPoint(new Point(r.x - retToFix.width, posToFix.getPoint().getY()));
                        break;
                    default:
                        break;
                    
                    }
                }
                break;
            case SHOOT:
                break;
            case STOP:
                break;
            default:
                break;
           
           }
        }
        return posToFix;
    }
    
    private List<Rectangle> getCollisionList(Rectangle rectangle, Entities entity) {
        List<Rectangle> ret = new LinkedList<>();
        for(Entities ent : entities) {
            if(ent.getCode() != entity.getCode()) {
                Rectangle elementToTest = getRectangle(ent.getPosition());
                if(rectangle.intersects(elementToTest)) {
                    //TODO questo cast fa cagare, prova ad usare solo rectangle 2D
                    ret.add(new Rectangle((Rectangle) rectangle.createIntersection(elementToTest)));
                }
            }
        }
        
        return ret;
    }
    
    private static Rectangle getRectangle(Position p) {
        return new Rectangle(p.getPoint().getX(), p.getPoint().getY(), p.getDimension().getWidth(), p.getDimension().getHeight());
    }

}
