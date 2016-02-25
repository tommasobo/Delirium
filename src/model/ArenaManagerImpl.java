package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utility.Dimension;
import utility.Pair;

public class ArenaManagerImpl implements ArenaManager {

    private Arena arena;
    private LastPositionsManager lastPositionsMan;
    private ActiveMovementDatabase platformEntities;
    private boolean gameWon;
    
    public ArenaManagerImpl(Arena arena) {
        this.arena = arena;
        this.lastPositionsMan = new LastPositionsManager();
        this.platformEntities = new ActiveMovementDatabase();
    }
    
    @Override
    public void MoveEntities() {
        Stream.concat(this.arena.getEntities().stream(), this.arena.getBullets().stream()).forEach(t -> {
            this.lastPositionsMan.putPosition(t, t.getPosition());
            this.platformEntities.removeEntityFromAllDependences(t);
            Optional<Position> p = !t.getMovementManager().isPresent() ? Optional.empty()
                    : Optional.of(t.getMovementManager().get().getNextMove());
            if (p.isPresent()) {
                if (t == this.arena.getHero()) {
                    this.gameWon = getRectangle(p.get())
                            .intersects(getRectangle(this.arena.getGoal().getPosition()));
                }
                Position pos = collisionFixerTest(p.get(), t);
                t.setPosition(pos.getPoint(), pos.getDirection());
            }
        });
    }
    
    public boolean isGameWon() {
        return this.gameWon;
    }
    
    //TODO mettere conteggio temporale in database per evitare spostamenti istantanei delle entità?
    
    private Position collisionFixerTest(Position pos, Entities entity) {
        return collisionFixerTest(pos, entity, realAction(entity), pos.getDirection());
    }
    
    private Position collisionFixerTest(Position pos, Entities entity, Actions action, Directions direction) {
        boolean verticalLimit = false;
        boolean orizzontalLimit = false;
        Position posToFix = new Position(pos.getPoint(), pos.getDirection(), pos.getDimension());
        Rectangle retToFix = getRectangle(posToFix);
        Pair<Entities, Rectangle> collision;
        // l'hero non deve ignorare cio che ha sopra
        if (entity != this.arena.getHero()) {
            collision = getFirstCollision(retToFix, entity,
                    Stream.concat(this.arena.getEntities().stream(), this.arena.getBullets().stream())
                            .collect(Collectors.toList()),
                    this.platformEntities.getRelativeEntities(entity.getCode()));
        } else {
            collision = getFirstCollision(retToFix, entity,
                    Stream.concat(this.arena.getEntities().stream(), this.arena.getBullets().stream())
                            .collect(Collectors.toList()));
        }
        if (collision == null) {
            // elimino i proiettili quando toccano i bounds
            // TODO o metti qui, oppure nell'update arena
            if (arena.getBullets().contains(entity)) {
                if (onBounds(posToFix, entity)) {
                    entity.getLifeManager().setLife(10);
                }
            }

            // TODO controllo brutto, posso disattivare l'aggiunta a livello di database? NO, quello deve conservare il funzionamento per estendibilità
            if (entity != this.arena.getHero()) {
                Set<Entities> over = this.platformEntities.getRelativeEntities(entity.getCode());
                if (!over.isEmpty()) {
                    PointOffset offset = this.lastPositionsMan.getOffsetFromLastPosition(entity, pos);
                    if (activeMovementOK(over, action, offset)) {
                        //TODO il controllo di priorità è da testare ed in più varia a seconda della direzione che hanno le entità
                        over.stream().sorted((x, y) -> (new Integer(x.getPosition().getPoint().getX())
                                .compareTo(y.getPosition().getPoint().getX()))).forEach(t -> {
                                    Position post = t.getPosition();
                                    post = this.collisionFixerTest(
                                            new Position(
                                                    new Point(post.getPoint().getX() + offset.getOffsetX(),
                                                            post.getPoint().getY() + offset.getOffsetY()),
                                                    post.getDirection(), post.getDimension()),
                                            t, action, direction);
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
        if(!this.arena.getBullets().contains(collision.getX())) {
            Rectangle collisionRectangle = collision.getY();
            switch (action) {
            case JUMP:
                posToFix.setPoint(new Point(posToFix.getPoint().getX(),
                        collisionRectangle.y - posToFix.getDimension().getHeight()));
                verticalLimit = true;
                break;
            case FALL:
                posToFix.setPoint(
                        new Point(posToFix.getPoint().getX(), collisionRectangle.y + collisionRectangle.height));
                verticalLimit = true;
                this.platformEntities.putEntity(collision.getX().getCode(), entity);
                break;
            case MOVE:
                fixPositionInMoveSwitch(posToFix, direction, collisionRectangle);
                orizzontalLimit = true;
                break;
            case MOVEONJUMP:
                if (!new Rectangle(retToFix.x, lastPositionsMan.getLastPosition(entity.getCode()).getPoint().getY(),
                        retToFix.width, retToFix.height).intersects(collisionRectangle)) {
                    posToFix.setPoint(new Point(retToFix.x, collisionRectangle.y - retToFix.height));
                    verticalLimit = true;
                } else {
                    fixPositionInMoveSwitch(posToFix, direction, collisionRectangle);
                    orizzontalLimit = true;
                }
                break;
            case MOVEONFALL:
                if (!new Rectangle(retToFix.x, lastPositionsMan.getLastPosition(entity.getCode()).getPoint().getY(),
                        retToFix.width, retToFix.height).intersects(collisionRectangle)) {
                    posToFix.setPoint(new Point(retToFix.x, collisionRectangle.y + collisionRectangle.height));
                    verticalLimit = true;
                    this.platformEntities.putEntity(collision.getX().getCode(), entity);
                } else {
                    fixPositionInMoveSwitch(posToFix, direction, collisionRectangle);
                    orizzontalLimit = true;
                }
                break;
            default:
                // TODO cambia eccezione
                throw new IllegalAccessError();
            }
        }

        // TODO ricorsione a metà o alla fine?
        posToFix = collisionFixerTest(posToFix, entity, action, direction);

        // TODO qui fai cambio direzioni a seconda dei buleani sopra, da fare
        // DOPO la ricorsione per evitare problemi
        // TODO metti questo in metodo separato? Non credo, lo uso una volta
        // sola, NO da usare quando muovo gli altri (post controllo)
        if (this.arena.getEntities().contains(entity)) {
            if (verticalLimit) {
                switch (action) {
                case FALL:
                    // TODO servirebbe controllo se puo volare
                    if (entity.getMovementManager().isPresent() && entity.getMovementManager().get().isCanFly()) {
                        entity.setAction(getOppositeAction(action));
                    } else {
                        entity.setAction(Actions.STOP);
                        if (entity.equals(this.arena.getHero())) {
                            arena.getHero().setOnPlatform(true);
                        }
                    }
                    break;
                case JUMP:
                    entity.setAction(getOppositeAction(action));
                    break;
                case MOVEONFALL:
                    // TODO servirebbe controllo se puo volare
                    if (entity.getMovementManager().isPresent() && entity.getMovementManager().get().isCanFly()) {
                        entity.setAction(getOppositeAction(action));
                    } else {
                        entity.setAction(Actions.MOVE);
                        if (entity.equals(this.arena.getHero())) {
                            arena.getHero().setOnPlatform(true);
                        }
                    }
                    break;
                case MOVEONJUMP:
                    entity.setAction(Actions.MOVEONFALL);
                    break;
                default:
                    break;

                }
            }
            if (orizzontalLimit && !entity.equals(this.arena.getHero())) {
                switch(direction) {
                case LEFT:
                    posToFix.setDirection(getOppositeDirection(direction));
                    break;
                case RIGHT:
                    posToFix.setDirection(getOppositeDirection(direction));
                    break;
                default:
                    break;
                
                }
            }
        }

        return posToFix;
    }
    
    private static Directions getOppositeDirection(Directions direction) {
        switch(direction) {
        case LEFT:
            return Directions.RIGHT;
        case RIGHT:
            return Directions.LEFT;
        default:
            return direction;
        
        }
    }
    
    private static Actions getOppositeAction(Actions action) {
        switch(action) {
        case FALL:
            return Actions.JUMP;
        case JUMP:
            return Actions.FALL;
        case MOVEONJUMP:
            return Actions.MOVEONFALL;
        case MOVEONFALL:
            return Actions.MOVEONJUMP;
        default:
            return action;
        }
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
                if(getFirstCollision(getRectangle(newPosition), t, this.arena.getEntities(), this.platformEntities.getRelativeEntities(t.getCode())) != null) {
                    ret = false;
                }
                if(!this.platformEntities.getRelativeEntities(t.getCode()).isEmpty()) {
                    ret = activeMovementOK(this.platformEntities.getRelativeEntities(t.getCode()), action, offset);
                }
            }
        }
        return ret;
    }
    
    private static Pair<Entities, Rectangle> getFirstCollision(Rectangle rectangle, Entities entity, Collection<? extends Entities> entities, Collection<? extends Entities> exclusions) {
        
        Optional<? extends Entities> ret = entities.stream().filter(entityToTest -> entityToTest.getCode() != entity.getCode())
                .filter(t -> !exclusions.contains(t))
                .filter(t -> t.getLifeManager().getLife() > 0)
                .filter(entityToTest -> getRectangle(entityToTest.getPosition()).intersects(rectangle)).findFirst();

        if (ret.isPresent()) {
            Entities inCollision = ret.get();
            return new Pair<>(inCollision, (Rectangle) rectangle.createIntersection(getRectangle(inCollision.getPosition())));
        }

        return null;
    }
    
    private static Pair<Entities, Rectangle> getFirstCollision(Rectangle rectangle, Entities entity, Collection<? extends Entities> entities) {
        return getFirstCollision(rectangle, entity, entities, new ArrayList<>());
    }
}
