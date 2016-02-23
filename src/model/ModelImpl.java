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
    private Arena arena;
    private LastPositionsManager lastPositionsMan;
    private ActiveMovementDatabase platformEntities;
    
    private ModelImpl() {
        this.arena = new Arena();
        this.lastPositionsMan = new LastPositionsManager();
        this.platformEntities = new ActiveMovementDatabase();
    }
    
    public static ModelImpl getModel() {
        return singleton;
    }

    @Override
    public void notifyEvent(Directions direction) {
        if(arena.getHero().getMovementManager().isPresent()) {
            arena.getHero().getMovementManager().get().setPosition(arena.getHero().getPosition().getPoint(), direction);
        }
    }
    
    @Override
    public void notifyEvent(Actions action) {
        arena.getHero().setAction(action);
    }
    
    public List<EntitiesInfo> updateArena() {
        
        //dovrebbe funzionare, ma la view non rimuove gli oggetti stampati
        int size = arena.getEntities().size();
        for(int i = 0; i < size; i++) {
            if(arena.getEntities().get(i).getLifeManager().getLife() == 0) {
                arena.getEntities().remove(i);
                i--;
                size--;
            }
        }
        
        size = arena.getBullets().size();
        for(int i = 0; i < size; i++) {
            if(arena.getBullets().get(i).getLifeManager().getLife() == 0) {
                arena.getBullets().remove(i);
                i--;
                size--;
            }
        }
        
        List<EntitiesInfo> bullets = new LinkedList<>();
        //this.lastPositions = new HashMap<>();
        
        Stream.concat(this.arena.getEntities().stream(), this.arena.getBullets().stream()).forEach(t -> {
            //lastPositions.put(t.getCode(), t.getPosition());
            this.lastPositionsMan.putPosition(t, t.getPosition());
            this.platformEntities.removeEntityFromAllDependences(t);
            Optional<Position> p = !t.getMovementManager().isPresent() ? Optional.empty() : Optional.of(t.getMovementManager().get().getNextMove());
            if (p.isPresent()) {
                Position pos = collisionFixerTest(p.get(), t);
                //TODO metto qui il movimento delle genti? Non conservo l'action effettiva!!!!!!!
                t.setPosition(pos.getPoint(), pos.getDirection());
            }
            Optional<EntitiesInfo> bullet = !t.getShootManager().isPresent() ? Optional.empty() : t.getShootManager().get().getBullet(t.getCode(), t.getPosition());
            if(bullet.isPresent()) {
                bullets.add(bullet.get());
            }
        });
        
        System.out.println(this.platformEntities.getRelativeEntities(1));
        
        return bullets;
        
    }


    //TODO cambia oggetto comunicazione con controller
    @Override
    public List<EntitiesInfoToControl> getState() {
        final List<EntitiesInfoToControl> result = new LinkedList<>();
        
        Stream.concat(this.arena.getEntities().stream(), this.arena.getBullets().stream()).forEach(t -> {
            Optional<Integer> speed = t.getMovementManager().isPresent() ? Optional.of(t.getMovementManager().get().getSpeed()) : Optional.empty();
            result.add(new EntitiesInfoToControlImpl(t.getCode(), t.getLifeManager().getLife(), t.getPosition(), t.getAction(), speed));
        });
        
        return result;
    }

    @Override
    public void createArena(List<EntitiesInfo> entitiesInfo) {
        
        this.arena = new Arena();
        this.lastPositionsMan = new LastPositionsManager();
        this.platformEntities = new ActiveMovementDatabase();
        
        entitiesInfo.stream().forEach(t -> {
            
            Pair<Optional<Position>, Optional<MovementManager>> pair = MovementManagerFactory.getMovementManager(t.getPosition(), t.getMovementInfo());
            Optional<ShootManager> shootManager = ShootManagerFactory.getShootManager(t.getShootInfo());
            
            this.arena.add(new Entities.Builder()
                    .code(t.getCode())
                    .lifeManager(new LifeManager(t.getLife(), t.getLifePattern()))
                    .position(pair.getX().isPresent() ? pair.getX().get() : null)
                    .movementManager(pair.getY().isPresent() ? pair.getY().get() : null)
                    .shootManager(shootManager.isPresent() ? shootManager.get() : null)
                    .contactDamage(t.getContactDamage().isPresent() ? t.getContactDamage().get() : null)
                    .build());
        });
        
        //controllare anche per le altre entities
        /*long nHero = this.entities.stream().filter(t -> t.getCode() == 0).count();
        long nGoal = this.entities.stream().filter(t -> t.getCode() == 0).count();
        
        if (nHero > 1 || nGoal > 1) {
            throw new IllegalStateException();
        }*/
        
        
        
    }

    @Override
    public void putBullet(List<EntitiesInfo> entitiesInfo) {
        entitiesInfo.stream().forEach(t -> {
            this.arena.add(new Entities.Builder()
                    .code(t.getCode())
                    .movementManager(new LinearDinamicMovementManager(t.getPosition(), t.getMovementInfo().get().getBounds(), t.getMovementInfo().get().getSpeed(), t.getMovementInfo().get().isCanFly(), t.getMovementInfo().get().getMovementTypes()))
                    .contactDamage(t.getContactDamage().get())
                    .build());
            
          });
    }
    
    
    private Position collisionFixerTest(Position pos, Entities entity) {
        return collisionFixerTest(pos, entity, realAction(entity), pos.getDirection(), true);
    }
    
    private Position collisionFixerTest(Position pos, Entities entity, Actions action, Directions direction, /*forse inutile, se fixo la position, anche se sono su piattaforma, cambio direction gente*/boolean modifyEntity) {
        boolean verticalLimit = false;
        boolean orizzontalLimit = false;
        Position posToFix = new Position(pos.getPoint(), pos.getDirection(), pos.getDimension());
        Rectangle retToFix = getRectangle(posToFix);
        Pair<Entities, Rectangle> collision = getFirstCollision(retToFix, entity);
        if (collision == null) {
            //elimino i proiettili quando toccano i bounds
            //TODO o metti qui, oppure nell'update arena
            if(arena.getBullets().contains(entity)) {
                if(onBounds(posToFix, entity)) {
                    entity.getLifeManager().setLife(10);
                }
            }
            
            //TODO devo muovere tutti gli oggetti che ho sopra con un metodo,
            //se questo ritorna false io non devo muovere la piattaforma
            //(ho qualcuno sopra, non posso salire se quello sopra è in 
            //collisione oppure se rischia di superare i bounds)
            //Lo metto nell'update arena?
            return pos;
        }
        modifyEntitiesInCollision(entity, collision.getX());
        Rectangle collisionRectangle = collision.getY();
        switch (action) {
        case JUMP:
            posToFix.setPoint(new Point(posToFix.getPoint().getX(), collisionRectangle.y - posToFix.getDimension().getHeight()));
            verticalLimit = true;
            break;
        case FALL:
            posToFix.setPoint(new Point(posToFix.getPoint().getX(), collisionRectangle.y + collisionRectangle.height));
            verticalLimit = true;
            this.platformEntities.putEntity(collision.getX().getCode(), entity);
            break;
        case MOVE:
            fixPositionInMoveSwitch(posToFix, direction, collisionRectangle);
            orizzontalLimit = true;
            break;
        case MOVEONJUMP:
            if (!new Rectangle(retToFix.x, lastPositionsMan.getLastPosition(entity.getCode()).getPoint().getY(), retToFix.width,
                    retToFix.height).intersects(collisionRectangle)) {
                posToFix.setPoint(new Point(retToFix.x, collisionRectangle.y - retToFix.height));
                verticalLimit = true;
            } else {
                fixPositionInMoveSwitch(posToFix, direction, collisionRectangle);
                orizzontalLimit = true;
            }
            break;
        case MOVEONFALL:
            if (!new Rectangle(retToFix.x, lastPositionsMan.getLastPosition(entity.getCode()).getPoint().getY(), retToFix.width,
                    retToFix.height).intersects(collisionRectangle)) {
                posToFix.setPoint(new Point(retToFix.x, collisionRectangle.y + collisionRectangle.height));
                verticalLimit = true;
                this.platformEntities.putEntity(collision.getX().getCode(), entity);
            } else {
                fixPositionInMoveSwitch(posToFix, direction, collisionRectangle);
                orizzontalLimit = true;
            }
            break;
        default:
            //TODO cambia eccezione
            throw new IllegalAccessError();
        }
        
        //TODO ricorsione a metà o alla fine?
        posToFix = collisionFixerTest(posToFix, entity, action, direction, modifyEntity);
        
        //TODO qui fai cambio direzioni a seconda dei buleani sopra, da fare DOPO la ricorsione per evitare problemi
        //TODO metti questo in metodo separato? Non credo, lo uso una volta sola
        if(entity.equals(this.arena.getHero())) {
            if(verticalLimit) {
                if(realAction(entity) == Actions.FALL) {
                    arena.getHero().setOnPlatform(true);
                    entity.setAction(Actions.STOP);
                } else if (entity.getAction() == Actions.MOVEONFALL) {
                    entity.setAction(Actions.MOVE);
                    arena.getHero().setOnPlatform(true);
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
    
    private void fixPositionInMoveSwitch(Position posToFix, Directions direction, Rectangle collisionRectangle) {
        switch (direction) {
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
    
    private Pair<Entities, Rectangle> getFirstCollision(Rectangle rectangle, Entities entity) {

        Optional<Entities> ret = Stream.concat(this.arena.getEntities().stream(), this.arena.getBullets().stream())
                .filter(entityToTest -> entityToTest.getCode() != entity.getCode())
                .filter(entityToTest -> getRectangle(entityToTest.getPosition()).intersects(rectangle)).findFirst();

        if (ret.isPresent()) {
            Entities inCollision = ret.get();
            return new Pair<>(inCollision, (Rectangle) rectangle.createIntersection(getRectangle(inCollision.getPosition())));
        }

        return null;
    }
    
    //TODO metodo breve, lo tolgo?
    private void modifyEntitiesInCollision(Entities entity1, Entities entity2) {
        entity1.getLifeManager().setLife(entity2.getContactDamage().orElseGet(() -> 0));
        entity2.getLifeManager().setLife(entity1.getContactDamage().orElseGet(() -> 0));
    }
    
    private static Rectangle getRectangle(Position p) {
        return new Rectangle(p.getPoint().getX(), p.getPoint().getY(), p.getDimension().getWidth(), p.getDimension().getHeight());
    }
    
    private boolean activeMovementOK(List<Entities> entities, Actions action, PointOffset offset) {
        boolean ret = true;
        if(UtilityMovement.splitActions(action).stream().anyMatch(t -> t == Actions.JUMP)) {
            for(Entities t : entities) {
                Point oldPoint = t.getPosition().getPoint();
                Position newPosition = new Position(new Point(oldPoint.getX() + offset.getOffsetX(), oldPoint.getY() + offset.getOffsetY()), t.getPosition().getDirection(), t.getPosition().getDimension());
                if(UtilityMovement.checkBounds(newPosition, t.getMovementManager().get().getBounds(), action, 0) != UtilityMovement.CheckResult.TRUE) {
                    ret = false;
                }
                if(getFirstCollision(getRectangle(newPosition), t) != null) {
                    ret = false;
                }
            }
        }
        return ret;
    }

}
