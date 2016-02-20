package model;

import java.awt.Rectangle;
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
        
        //dovrebbe funzionare, ma la view non rimuove gli oggetti stampati
        int size = entities.size();
        for(int i = 0; i < size; i++) {
            if(entities.get(i).getLifeManager().getLife() == 0) {
                entities.remove(i);
                i--;
                size--;
            }
        }
        
        List<EntitiesInfo> bullets = new LinkedList<>();
        this.lastPositions = new HashMap<>();
        for(Entities t : entities) {
           //TODO se non usi piu la mappa cambiala con una variabile temporanea
           lastPositions.put(t.getCode(), t.getPosition());
           Optional<Position> p = !t.getMovementManager().isPresent() ? Optional.empty() : Optional.of(t.getMovementManager().get().getNextMove());
           if (p.isPresent()) {
               //t.setPosition(p.get().getPoint(), p.get().getDirection());
               Position pos = collisionFixer(p.get(), t);
               t.setPosition(pos.getPoint(), pos.getDirection());
               
           }
           Optional<EntitiesInfo> bullet = !t.getShootManager().isPresent() ? Optional.empty() : t.getShootManager().get().getBullet(t.getCode(), t.getPosition());
           if(bullet.isPresent()) {
               bullets.add(bullet.get());
           }
           
        }
        return bullets;
        
    }


    //TODO cambia oggetto comunicazione con controller
    @Override
    public List<EntitiesInfo> getState() {
        final List<EntitiesInfo> result = new LinkedList<>();
        this.entities.stream().forEach(t -> {
            result.add(new EntitiesInfoImpl(t.getCode(), t.getPosition(), t.getMovementManager().isPresent() ? Optional.of(new MovementInfo(t.getMovementManager().get().getSpeed(), t.getMovementManager().get().getBounds(), t.getAction(), t.getMovementManager().get().isCanFly(), null)) : Optional.of(new MovementInfo(0, null, t.getAction(), false, null)), t.getLifeManager().getLife(), null, null, null));
        });
        return result;
    }

    @Override
    public void createArena(List<EntitiesInfo> entitiesInfo) {
        
        entitiesInfo.stream().forEach(t -> {
            if (!t.getMovementInfo().isPresent()) {
                this.entities.add(new EntitiesImpl(t.getCode(), new LifeManager(t.getLife(), t.getLifePattern()), t.getPosition(), (t.getShootInfo().isPresent() ? Optional.of(new ShootManagerImpl(t.getShootInfo().get().getMinTime(), t.getShootInfo().get().getBulletDamage(), t.getShootInfo().get().getMovementType(), t.getShootInfo().get().getRange(), t.getShootInfo().get().getSpeed())) : Optional.empty()), t.getContactDamage()));
            } else {
                
                switch (t.getMovementInfo().get().getMovementTypes()) {
                case HERO: 
                    hero = new Hero(t.getCode(), new LifeManager(t.getLife(), t.getLifePattern()), new HeroMovementManager(t.getPosition(), t.getMovementInfo().get().getBounds(), t.getMovementInfo().get().getActions(), t.getMovementInfo().get().getSpeed(), t.getMovementInfo().get().isCanFly()), new HeroShootManagerImpl(t.getShootInfo().get().getMinTime(), t.getShootInfo().get().getBulletDamage(), t.getShootInfo().get().getMovementType(), t.getShootInfo().get().getRange(), t.getShootInfo().get().getSpeed()), t.getContactDamage().get());
                    this.entities.add(hero);
                    break;
                case REACTIVE : 
                    this.entities.add(new EntitiesImpl(t.getCode(), new LifeManager(t.getLife(), t.getLifePattern()), new ReactiveMovementManager(t.getPosition(), t.getMovementInfo().get().getBounds(), t.getMovementInfo().get().getActions(), t.getMovementInfo().get().getSpeed(), t.getMovementInfo().get().isCanFly()), (t.getShootInfo().isPresent() ? Optional.of(new ShootManagerImpl(t.getShootInfo().get().getMinTime(), t.getShootInfo().get().getBulletDamage(), t.getShootInfo().get().getMovementType(), t.getShootInfo().get().getRange(), t.getShootInfo().get().getSpeed())) : Optional.empty()), t.getContactDamage()));
                    break;
                case RANDOM: 
                    this.entities.add(new EntitiesImpl(t.getCode(), new LifeManager(t.getLife(), t.getLifePattern()), new RandomDinamicMovementManager(t.getPosition(), t.getMovementInfo().get().getBounds(), t.getMovementInfo().get().getSpeed(), t.getMovementInfo().get().isCanFly(), t.getMovementInfo().get().getMovementTypes()), (t.getShootInfo().isPresent() ? Optional.of(new ShootManagerImpl(t.getShootInfo().get().getMinTime(), t.getShootInfo().get().getBulletDamage(), t.getShootInfo().get().getMovementType(), t.getShootInfo().get().getRange(), t.getShootInfo().get().getSpeed())) : Optional.empty()), t.getContactDamage()));
                    break;
                case VERTICAL_LINEAR:
                case HORIZONTAL_LINEAR:
                    this.entities.add(new EntitiesImpl(t.getCode(), new LifeManager(t.getLife(), t.getLifePattern()), new LinearDinamicMovementManager(t.getPosition(), t.getMovementInfo().get().getBounds(), t.getMovementInfo().get().getSpeed(), t.getMovementInfo().get().isCanFly(), t.getMovementInfo().get().getMovementTypes()), (t.getShootInfo().isPresent() ? Optional.of(new ShootManagerImpl(t.getShootInfo().get().getMinTime(), t.getShootInfo().get().getBulletDamage(), t.getShootInfo().get().getMovementType(), t.getShootInfo().get().getRange(), t.getShootInfo().get().getSpeed())) : Optional.empty()), t.getContactDamage()));
                    break;
                }
                
            }
            
            
        });
    }

    @Override
    public void putBullet(List<EntitiesInfo> entitiesInfo) {
        entitiesInfo.stream().forEach(t -> {
            this.entities.add(new Bullet(t.getCode(), new LinearDinamicMovementManager(t.getPosition(), t.getMovementInfo().get().getBounds(), t.getMovementInfo().get().getSpeed(), t.getMovementInfo().get().isCanFly(), t.getMovementInfo().get().getMovementTypes()), t.getContactDamage().get()));
        });
    }
    
    //TODO problemi collisioni
    //se collido a destra o sinistra mentre sono su un pavimento il tutto sfarfalla OK
    //differenzia moveonfall e muveonjumb per raggiungere limiti collisone OK
    //se gioco attorno alla volpe mentre sale questa schizza RISOLTO CON RICORSIONE
    private Position collisionFixer(Position pos, Entities entity) {
        Position posToFix = new Position(pos.getPoint(), pos.getDirection(), pos.getDimension());
        Rectangle retToFix = getRectangle(posToFix);
        List<Rectangle> collisionList = getCollisionList(retToFix, entity);
        
        
        for(Rectangle collisionRectangle: collisionList) {
           //se ho più elementi in lista mi devo ricalcolare il rettangolo della figura dopo il primo fix della posizione, non so
           //se metterlo qui o direttamente quando fixo (dopo il set posToFix), sarebbe uno spreco
           retToFix = getRectangle(posToFix);
           //TODO togli operatore ternario e metti metodo per azione effettiva
           switch(entity.getMovementManager().isPresent() ? entity.getMovementManager().get().getAction() : Actions.STOP) {
            case JUMP:
                posToFix.setPoint(new Point(posToFix.getPoint().getX(), collisionRectangle.y - posToFix.getDimension().getHeight()));
                //TODO cosi anche quando l'ero collide sopra attacca a cadere? NO, cambia controllo salto che se è fall riempie il contatore
                entity.setAction(Actions.FALL);
                break;
            case FALL:
                posToFix.setPoint(new Point(posToFix.getPoint().getX(), collisionRectangle.y + collisionRectangle.height));
                if(entity == this.hero) {
                    hero.setOnPlatform(true);
                    entity.setAction(Actions.STOP);
                } else {
                    entity.setAction(Actions.JUMP);
                }
                break;
            case MOVE:
                switch(posToFix.getDirection()) {
                case LEFT:
                    posToFix.setPoint(new Point(collisionRectangle.x + collisionRectangle.width, posToFix.getPoint().getY()));
                    break;
                case NONE:
                    break;
                case RIGHT:
                    posToFix.setPoint(new Point(collisionRectangle.x - retToFix.width, posToFix.getPoint().getY()));
                    break;
                default:
                    break;
                }
                break;
                //TODO differenzia moveonjump e moveonfall
            case MOVEONJUMP:
                if(!new Rectangle(retToFix.x, lastPositions.get(entity.getCode()).getPoint().getY(), retToFix.width, retToFix.height).intersects(collisionRectangle)/* && 
                        !new Rectangle(retToFix.x, lastPositions.get(entity.getCode()).getPoint().getY(), retToFix.width, retToFix.height).contains(collisionRectangle)*/) {
                    //posToFix.setPoint(new Point(retToFix.x,  lastPositions.get(entity.getCode()).getPoint().getY()));
                    posToFix.setPoint(new Point(retToFix.x,  collisionRectangle.y - retToFix.height));
                } else {
                    switch(posToFix.getDirection()) {
                    case LEFT:
                        posToFix.setPoint(new Point(collisionRectangle.x + collisionRectangle.width, posToFix.getPoint().getY()));
                        break;
                    case NONE:
                        break;
                    case RIGHT:
                    posToFix.setPoint(new Point(collisionRectangle.x - retToFix.width, posToFix.getPoint().getY()));
                    break;
                    default:
                        break;
                    }
                }
                break;
            case MOVEONFALL:
                if(!new Rectangle(retToFix.x, lastPositions.get(entity.getCode()).getPoint().getY(), retToFix.width, retToFix.height).intersects(collisionRectangle)/* && 
                        !new Rectangle(retToFix.x, lastPositions.get(entity.getCode()).getPoint().getY(), retToFix.width, retToFix.height).contains(collisionRectangle)*/) {
                    //posToFix.setPoint(new Point(retToFix.x,  lastPositions.get(entity.getCode()).getPoint().getY()));
                    posToFix.setPoint(new Point(retToFix.x,  collisionRectangle.y + collisionRectangle.height));
                    if(entity.getAction() == Actions.MOVEONFALL) {
                        entity.setAction(Actions.MOVE);
                    }  
                    if(entity == this.hero) {
                        hero.setOnPlatform(true);
                    }
                } else {
                    switch(posToFix.getDirection()) {
                    case LEFT:
                        posToFix.setPoint(new Point(collisionRectangle.x + collisionRectangle.width, posToFix.getPoint().getY()));
                        break;
                    case NONE:
                        break;
                    case RIGHT:
                        posToFix.setPoint(new Point(collisionRectangle.x - retToFix.width, posToFix.getPoint().getY()));
                        //posToFix.setPoint(new Point(lastPositions.get(entity.getCode()).getPoint().getX(),  lastPositions.get(entity.getCode()).getPoint().getY()));
                        break;
                    default:
                        break;
                    }
                }
                break;
            case SHOOT:
                //mentre un'entità sta aparando può cadere a causa della gravità, oppure può star saltando (l'eroe), per destra e sinistra non è un problema,
                //ma, visto che io uso l'ultima posizione di y per risolvere le collisioni, per Jump e Fall lo diventa
                //RISOLTO CON GET AZIONE EFFETTIVA
                throw new IllegalAccessError();
            case STOP:
                break;
            default:
                break;
           }
        }
        
        if(!posToFix.equals(pos)) {
            posToFix = collisionFixer(posToFix, entity);
        }
        
        return posToFix;
    }
    
    private List<Rectangle> getCollisionList(Rectangle rectangle, Entities entity) {
        List<Rectangle> ret = new LinkedList<>();
        for(Entities entityToTest : entities) {
            if(entityToTest.getCode() != entity.getCode()) {
                Rectangle rectangleToTest = getRectangle(entityToTest.getPosition());
                if(rectangle.intersects(rectangleToTest)) {
                    //se noto la collisione setto i danni
                    entity.getLifeManager().setLife(entityToTest.getContactDamage().orElseGet(() -> 0));
                    entityToTest.getLifeManager().setLife(entity.getContactDamage().orElseGet(() -> 0));
                    //TODO questo cast fa cagare, prova ad usare solo rectangle 2D
                    if(entityToTest.getLifeManager().getLife() > 0)
                        ret.add((Rectangle) rectangle.createIntersection(rectangleToTest));
                }
            }
        }
        
        
        return ret;
    }
    
    private static Rectangle getRectangle(Position p) {
        return new Rectangle(p.getPoint().getX(), p.getPoint().getY(), p.getDimension().getWidth(), p.getDimension().getHeight());
    }

}
