package model;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    private Map<Integer,Position> lastPositions;
    
    private ModelImpl() {
        this.entities = new LinkedList<>();
        this.bullets = new LinkedList<>();
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
        this.lastPositions = new HashMap<>();
        
        Stream.concat(this.entities.stream(), this.bullets.stream()).forEach(t -> {
            lastPositions.put(t.getCode(), t.getPosition());
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
    public List<EntitiesInfo> getState() {
        final List<EntitiesInfo> result = new LinkedList<>();
        this.entities.stream().forEach(t -> {
            result.add(new EntitiesInfoImpl(t.getCode(), t.getPosition(), t.getMovementManager().isPresent() ? Optional.of(new MovementInfo(t.getMovementManager().get().getSpeed(), t.getMovementManager().get().getBounds(), t.getAction(), t.getMovementManager().get().isCanFly(), null)) : Optional.of(new MovementInfo(0, null, t.getAction(), false, null)), t.getLifeManager().getLife(), null, null, null));
        });
        this.bullets.stream().forEach(t -> {
            result.add(new EntitiesInfoImpl(t.getCode(), t.getPosition(), t.getMovementManager().isPresent() ? Optional.of(new MovementInfo(t.getMovementManager().get().getSpeed(), t.getMovementManager().get().getBounds(), t.getAction(), t.getMovementManager().get().isCanFly(), null)) : Optional.of(new MovementInfo(0, null, t.getAction(), false, null)), t.getLifeManager().getLife(), null, null, null));
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
        // TODO togli operatore ternario e metti metodo per azione effettiva
        switch (realAction(entity)) {
        case JUMP:
            posToFix.setPoint(new Point(posToFix.getPoint().getX(), collisionRectangle.y - posToFix.getDimension().getHeight()));
            // TODO cosi anche quando l'ero collide sopra attacca a cadere? NO,
            // cambia controllo salto che se è fall riempie il contatore
            verticalLimit = true;
            break;
        case FALL:
            posToFix.setPoint(new Point(posToFix.getPoint().getX(), collisionRectangle.y + collisionRectangle.height));
            verticalLimit = true;
            break;
        case MOVE:
            fixPositionInMoveSwitch(posToFix, collisionRectangle);
            break;
        case MOVEONJUMP:
            if (!new Rectangle(retToFix.x, lastPositions.get(entity.getCode()).getPoint().getY(), retToFix.width,
                    retToFix.height).intersects(collisionRectangle)) {
                posToFix.setPoint(new Point(retToFix.x, collisionRectangle.y - retToFix.height));
            } else {
                fixPositionInMoveSwitch(posToFix, collisionRectangle);
            }
            break;
        case MOVEONFALL:
            if (!new Rectangle(retToFix.x, lastPositions.get(entity.getCode()).getPoint().getY(), retToFix.width,
                    retToFix.height).intersects(collisionRectangle)) {
                posToFix.setPoint(new Point(retToFix.x, collisionRectangle.y + collisionRectangle.height));
                if (entity.getAction() == Actions.MOVEONFALL) {
                    entity.setAction(Actions.MOVE);
                }
                if (entity == this.hero) {
                    hero.setOnPlatform(true);
                }
            } else {
                fixPositionInMoveSwitch(posToFix, collisionRectangle);
            }
            break;
        case SHOOT:
            throw new IllegalAccessError();
        case STOP:
            break;
        default:
            break;
        }

        posToFix = collisionFixerTest(posToFix, entity);
        
        //TODO qui fai cambio direzioni a seconda dei buleani sopra, da fare DOPO la ricorsione per evitare problemi
        if(verticalLimit) {
            if(entity.equals(this.hero)) {
                if(realAction(entity) == Actions.FALL) {
                    hero.setOnPlatform(true);
                    entity.setAction(Actions.STOP);
                } else {
                    //TODO stacosanonfunge, l'hero non cade
                    entity.setAction(Actions.FALL);
                }
            } else {
                if(realAction(entity) == Actions.FALL) {
                    entity.setAction(Actions.JUMP);
                } else {
                    entity.setAction(Actions.FALL);
                }
            }
        }
        
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
