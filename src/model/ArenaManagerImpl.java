package model;

import java.awt.Rectangle;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import control.Dimension;
import control.Pair;
import control.Point;

public class ArenaManagerImpl implements ArenaManager {

    private Arena arena;
    private LastPositionsManager lastPositionsMan;
    private ActiveMovementDatabase platformEntities;
    
    public ArenaManagerImpl(Arena arena) {
        this.arena = arena;
        this.lastPositionsMan = new LastPositionsManager();
        this.platformEntities = new ActiveMovementDatabase();
    }
    
    /* (non-Javadoc)
     * @see model.ArenaManager#MoveEntities()
     */
    @Override
    public void MoveEntities() {
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
        Pair<Entities, Rectangle> collision;
        //l'hero non deve ignorare cio che ha sopra
        if(entity != this.arena.getHero()) {
            collision = getFirstCollision(retToFix, entity, this.platformEntities.getRelativeEntities(entity.getCode()), this.arena.getEntities(), this.arena.getBullets());

        } else {
            collision = getFirstCollision(retToFix, entity, new HashSet<>(), this.arena.getEntities(), this.arena.getBullets());

        }
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
            
            //TODO controllo brutto
            if(entity != this.arena.getHero()) {
                Set<Entities> over = this.platformEntities.getRelativeEntities(entity.getCode());
                if(!over.isEmpty()) {
                    PointOffset offset = this.lastPositionsMan.getOffsetFromLastPosition(entity, pos);
                    if(activeMovementOK(over, action, offset)) {
                        //Li muovo tutti
                        over.stream().sorted((x, y) -> (new Integer(x.getPosition().getPoint().getX()).compareTo(y.getPosition().getPoint().getX()))).forEach(t -> {
                            Position post = t.getPosition();
                            post = this.collisionFixerTest(new Position(new Point(post.getPoint().getX() + offset.getOffsetX(), post.getPoint().getY() + offset.getOffsetY()), post.getDirection(), post.getDimension()), t, action, direction, true);
                            t.setPosition(post.getPoint(), post.getDirection());
                        });
                    } else {
                        return this.lastPositionsMan.getLastPosition(entity);
                    }
                }
            }
            
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
    
    //TODO metodo breve, lo tolgo?
    private void modifyEntitiesInCollision(Entities entity1, Entities entity2) {
        entity1.getLifeManager().setLife(entity2.getContactDamage().orElseGet(() -> 0));
        entity2.getLifeManager().setLife(entity1.getContactDamage().orElseGet(() -> 0));
    }
    
    private static Rectangle getRectangle(Position p) {
        return new Rectangle(p.getPoint().getX(), p.getPoint().getY(), p.getDimension().getWidth(), p.getDimension().getHeight());
    }
    
    private boolean activeMovementOK(Set<Entities> entities, Actions action, PointOffset offset) {
        boolean ret = true;
        if(UtilityMovement.splitActions(action).stream().anyMatch(t -> t == Actions.JUMP)) {
            for(Entities t : entities) {
                Point oldPoint = t.getPosition().getPoint();
                Position newPosition = new Position(new Point(oldPoint.getX() + offset.getOffsetX(), oldPoint.getY() + offset.getOffsetY()), t.getPosition().getDirection(), t.getPosition().getDimension());
                if(UtilityMovement.checkBounds(newPosition, t.getMovementManager().get().getBounds(), action, 0) != UtilityMovement.CheckResult.TRUE) {
                    ret = false;
                }
                if(getFirstCollision(getRectangle(newPosition), t, this.platformEntities.getRelativeEntities(t.getCode()), this.arena.getEntities()) != null) {
                    ret = false;
                }
                if(!this.platformEntities.getRelativeEntities(t.getCode()).isEmpty()) {
                    ret = activeMovementOK(this.platformEntities.getRelativeEntities(t.getCode()), action, offset);
                }
            }
        }
        return ret;
    }
    
    //TODO i varargs andrebbero messi negli static, metti static e aggiungi elementi di esclusione
    @SafeVarargs
    private static Pair<Entities, Rectangle> getFirstCollision(Rectangle rectangle, Entities entity, Set<Entities> exclusions, List<? extends Entities>...lists) {
        Stream<Entities> stream = Stream.empty();
        for(List<? extends Entities> ls : lists) {
            stream = Stream.concat(stream, ls.stream());
        }
        
        Optional<Entities> ret = stream.filter(entityToTest -> entityToTest.getCode() != entity.getCode())
                //TODO da escludere sempre quelli che hai sopra?
                .filter(t -> !exclusions.contains(t))
                .filter(entityToTest -> getRectangle(entityToTest.getPosition()).intersects(rectangle)).findFirst();

        if (ret.isPresent()) {
            Entities inCollision = ret.get();
            return new Pair<>(inCollision, (Rectangle) rectangle.createIntersection(getRectangle(inCollision.getPosition())));
        }

        return null;
    }
}
