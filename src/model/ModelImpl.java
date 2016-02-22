package model;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import control.Dimension;
import control.Pair;
import control.Point;

public class ModelImpl implements Model{
    
    private static final ModelImpl singleton = new ModelImpl();
    private Hero hero;
    private List<Entities> entities;
    private List<Bullet> bullets;
    private final LastPositionsManager lastPositionsMan;
    
    private ModelImpl() {
        this.entities = new LinkedList<>();
        this.bullets = new LinkedList<>();
        this.lastPositionsMan = new LastPositionsManager();
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
        
        size = bullets.size();
        for(int i = 0; i < size; i++) {
            if(bullets.get(i).getLifeManager().getLife() == 0) {
                bullets.remove(i);
                i--;
                size--;
            }
        }
        
        List<EntitiesInfo> bullets = new LinkedList<>();
        //this.lastPositions = new HashMap<>();
        
        Stream.concat(this.entities.stream(), this.bullets.stream()).forEach(t -> {
            //lastPositions.put(t.getCode(), t.getPosition());
            this.lastPositionsMan.putPosition(t, t.getPosition());
            Optional<Position> p = !t.getMovementManager().isPresent() ? Optional.empty() : Optional.of(t.getMovementManager().get().getNextMove());
            if (p.isPresent()) {
                Position pos = collisionFixerTest(p.get(), t);
                t.setPosition(pos.getPoint(), pos.getDirection());
            }
            Optional<EntitiesInfo> bullet = !t.getShootManager().isPresent() ? Optional.empty() : t.getShootManager().get().getBullet(t.getCode(), t.getPosition());
            if(bullet.isPresent()) {
                bullets.add(bullet.get());
            }
        });
        
        return bullets;
        
    }


    //TODO cambia oggetto comunicazione con controller
    @Override
    public List<EntitiesInfoToControl> getState() {
        final List<EntitiesInfoToControl> result = new LinkedList<>();
        
        Stream.concat(this.entities.stream(), this.bullets.stream()).forEach(t -> {
            result.add(new EntitiesInfoToControlImpl(t.getCode(), t.getLifeManager().getLife(), t.getPosition(), t.getAction()));
        });
        
        return result;
    }

    @Override
    public void createArena(List<EntitiesInfo> entitiesInfo) {
        
        entitiesInfo.stream().forEach(t -> {
            
            Pair<Optional<Position>, Optional<MovementManager>> pair = MovementManagerFactory.getMovementManager(t.getPosition(), t.getMovementInfo());
            Optional<ShootManager> shootManager = ShootManagerFactory.getShootManager(t.getShootInfo());
            
            this.entities.add(new Entities.Builder()
                    .code(t.getCode())
                    .lifeManager(new LifeManager(t.getLife(), t.getLifePattern()))
                    .position(pair.getX().isPresent() ? pair.getX().get() : null)
                    .movementManager(pair.getY().isPresent() ? pair.getY().get() : null)
                    .shootManager(shootManager.isPresent() ? shootManager.get() : null)
                    .contactDamage(t.getContactDamage().isPresent() ? t.getContactDamage().get() : null)
                    .build());
        });
        hero = (Hero) this.entities.get(0);
    }

    @Override
    public void putBullet(List<EntitiesInfo> entitiesInfo) {
        entitiesInfo.stream().forEach(t -> {
            this.bullets.add( (Bullet) new Entities.Builder()
                    .code(t.getCode())
                    .movementManager(new LinearDinamicMovementManager(t.getPosition(), t.getMovementInfo().get().getBounds(), t.getMovementInfo().get().getSpeed(), t.getMovementInfo().get().isCanFly(), t.getMovementInfo().get().getMovementTypes()))
                    .contactDamage(t.getContactDamage().get())
                    .build());
            
            //this.entities.add(new Bullet(t.getCode(), new LinearDinamicMovementManager(t.getPosition(), t.getMovementInfo().get().getBounds(), t.getMovementInfo().get().getSpeed(), t.getMovementInfo().get().isCanFly(), t.getMovementInfo().get().getMovementTypes()), t.getContactDamage().get()));
        });
    }
    
    
    private Position collisionFixerTest(Position pos, Entities entity) {
        boolean verticalLimit = false;
        boolean orizzontalLimit = false;
        Position posToFix = new Position(pos.getPoint(), pos.getDirection(), pos.getDimension());
        Rectangle retToFix = getRectangle(posToFix);
        Rectangle collisionRectangle = getFirstCollision(retToFix, entity);
        if (collisionRectangle == null) {
            //elimino i proiettili quando toccano i bounds
            //TODO o metti qui, oppure nell'update arena
            if(bullets.contains(entity)) {
                if(onBounds(posToFix, entity)) {
                    entity.getLifeManager().setLife(10);
                }
            }
            return pos;
        }
        switch (realAction(entity)) {
        case JUMP:
            posToFix.setPoint(new Point(posToFix.getPoint().getX(), collisionRectangle.y - posToFix.getDimension().getHeight()));
            verticalLimit = true;
            break;
        case FALL:
            posToFix.setPoint(new Point(posToFix.getPoint().getX(), collisionRectangle.y + collisionRectangle.height));
            verticalLimit = true;
            break;
        case MOVE:
            fixPositionInMoveSwitch(posToFix, collisionRectangle);
            orizzontalLimit = true;
            break;
        case MOVEONJUMP:
            if (!new Rectangle(retToFix.x, lastPositionsMan.getLastPosition(entity.getCode()).getPoint().getY(), retToFix.width,
                    retToFix.height).intersects(collisionRectangle)) {
                posToFix.setPoint(new Point(retToFix.x, collisionRectangle.y - retToFix.height));
                verticalLimit = true;
            } else {
                fixPositionInMoveSwitch(posToFix, collisionRectangle);
                orizzontalLimit = true;
            }
            break;
        case MOVEONFALL:
            if (!new Rectangle(retToFix.x, lastPositionsMan.getLastPosition(entity.getCode()).getPoint().getY(), retToFix.width,
                    retToFix.height).intersects(collisionRectangle)) {
                posToFix.setPoint(new Point(retToFix.x, collisionRectangle.y + collisionRectangle.height));
                verticalLimit = true;
            } else {
                fixPositionInMoveSwitch(posToFix, collisionRectangle);
                orizzontalLimit = true;
            }
            break;
        default:
            //TODO cambia eccezione
            throw new IllegalAccessError();
        }

        posToFix = collisionFixerTest(posToFix, entity);
        
        //TODO qui fai cambio direzioni a seconda dei buleani sopra, da fare DOPO la ricorsione per evitare problemi
        if(entity.equals(this.hero)) {
            if(verticalLimit) {
                if(realAction(entity) == Actions.FALL) {
                    hero.setOnPlatform(true);
                    entity.setAction(Actions.STOP);
                } else if (entity.getAction() == Actions.MOVEONFALL) {
                    entity.setAction(Actions.MOVE);
                    hero.setOnPlatform(true);
                } else {
                    //TODO stacosanonfunge, l'hero non cade
                    entity.setAction(Actions.FALL);
                }
            }
        } else {
            if(verticalLimit) {
                if(realAction(entity) == Actions.FALL) {
                    entity.setAction(Actions.JUMP);
                } else if (entity.getAction() == Actions.MOVEONFALL) {
                    entity.setAction(Actions.MOVE);
                } else {
                    entity.setAction(Actions.FALL);
                }
            }
            if(orizzontalLimit) {
                if(posToFix.getDirection() == Directions.LEFT) {
                    posToFix.setDirection(Directions.RIGHT);
                } else if(posToFix.getDirection() == Directions.RIGHT) {
                    posToFix.setDirection(Directions.LEFT);
                }
            }
        }
        //TODO devo muovere tutti gli oggetti che ho sopra con un metodo,
        //se questo ritorna false io non devo muovere la piattaforma
        //(ho qualcuno sopra, non posso salire se quello sopra è in 
        //collisione oppure se rischia di superare i bounds)
        return posToFix;
    }
    
    private Actions realAction(Entities entity) {
        return entity.getMovementManager().isPresent() ? entity.getMovementManager().get().getAction()
                : Actions.STOP;
    }
    
    //TODO sposta in utilityMovement
    private static boolean onBounds(Position pos, Entities entity) {
        Point point = pos.getPoint();
        Dimension dimension = pos.getDimension();
        //TODO i proiettili hanno sempre il movement manager, ma per sicurezza controlla se c'è
        Bounds bounds = entity.getMovementManager().get().getBounds();
        if(point.getX() == bounds.getMinX() || point.getY() == bounds.getMinY() || point.getX() + dimension.getWidth() == bounds.getMaxX() || point.getY() + dimension.getHeight()== bounds.getMaxY()) {
            return true;
        }
        return false;
        
    }
    
    private void fixPositionInMoveSwitch(Position posToFix, Rectangle collisionRectangle) {
        switch (posToFix.getDirection()) {
        case LEFT:
            posToFix.setPoint(new Point(collisionRectangle.x + collisionRectangle.width, posToFix.getPoint().getY()));
            break;
        case NONE:
            break;
        case RIGHT:
            posToFix.setPoint(new Point(collisionRectangle.x - posToFix.getDimension().getWidth(), posToFix.getPoint().getY()));
            break;
        default:
            break;
        }
    }
    
    private Rectangle getFirstCollision(Rectangle rectangle, Entities entity) {

        Optional<Entities> ret = Stream.concat(this.entities.stream(), this.bullets.stream())
                .filter(entityToTest -> entityToTest.getCode() != entity.getCode())
                .filter(entityToTest -> getRectangle(entityToTest.getPosition()).intersects(rectangle)).findFirst();

        if (ret.isPresent()) {
            Entities inCollision = ret.get();
            entity.getLifeManager().setLife(inCollision.getContactDamage().orElseGet(() -> 0));
            inCollision.getLifeManager().setLife(entity.getContactDamage().orElseGet(() -> 0));
            return (Rectangle) rectangle.createIntersection(getRectangle(inCollision.getPosition()));
        }

        return null;
    }
    
    private static Rectangle getRectangle(Position p) {
        return new Rectangle(p.getPoint().getX(), p.getPoint().getY(), p.getDimension().getWidth(), p.getDimension().getHeight());
    }

}
