package model;

import java.awt.Rectangle;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utility.Pair;

public class ArenaManagerImpl implements ArenaManager {

    private final Arena arena;
    private final LastPositionsManager lastPositionsMan;
    private final ActiveMovementDatabase platformEntities;
    private boolean gameWon;

    public ArenaManagerImpl(final Arena arena) {
        this.arena = arena;
        this.lastPositionsMan = new LastPositionsManager();
        this.platformEntities = new ActiveMovementDatabase();
    }

    @Override
    public void moveEntities() {
        Stream.concat(this.arena.getEntities().stream(), this.arena.getBullets().stream()).forEach(t -> {
            this.lastPositionsMan.putPosition(t, t.getPosition());
            this.platformEntities.removeEntityFromAllDependences(t);
            final Optional<Position> p = t.getMovementManager().isPresent()
                    ? Optional.of(t.getMovementManager().get().getNextMove()) : Optional.empty();
            if (p.isPresent()) {
                if (t == this.arena.getHero()) {
                    this.gameWon = UtilityCollisionsDetection.getRectangle(p.get())
                            .intersects(UtilityCollisionsDetection.getRectangle(this.arena.getGoal().getPosition()));
                }
                final Position pos = collisionFixerTest(p.get(), t);
                t.setPosition(pos.getPoint(), pos.getDirection());
            }
        });
    }

    public boolean isGameWon() {
        return this.gameWon;
    }

    private Position collisionFixerTest(final Position pos, final Entities entity) {
        return collisionFixerTest(pos, entity, UtilityCollisionsDetection.realAction(entity), pos.getDirection());
    }

    private Position collisionFixerTest(final Position pos, final Entities entity, final Actions action,
            final Directions direction) {
        boolean verticalLimit = false;
        boolean orizzontalLimit = false;
        Position posToFix = new Position(pos.getPoint(), pos.getDirection(), pos.getDimension());
        final Rectangle retToFix = UtilityCollisionsDetection.getRectangle(posToFix);
        Pair<Entities, Rectangle> collision;
        // l'hero non deve ignorare cio che ha sopra
        if (entity == this.arena.getHero()) {
            collision = UtilityCollisionsDetection.getFirstCollision(retToFix, entity,
                    Stream.concat(this.arena.getEntities().stream(), this.arena.getBullets().stream())
                            .collect(Collectors.toList()));
        } else {
            collision = UtilityCollisionsDetection
                    .getFirstCollision(retToFix,
                            entity, Stream.concat(this.arena.getEntities().stream(), this.arena.getBullets().stream())
                                    .collect(Collectors.toList()),
                            this.platformEntities.getRelativeEntities(entity.getCode()));
        }

        if (collision == null) {
            if (arena.getBullets().contains(entity) && UtilityCollisionsDetection.onBounds(posToFix, entity)) {
                entity.getLifeManager().setLife(1);
            }

            if (entity != this.arena.getHero()) {
                final Set<Entities> over = this.platformEntities.getRelativeEntities(entity.getCode());
                if (!over.isEmpty()) {
                    final PointOffset offset = this.lastPositionsMan.getOffsetFromLastPosition(entity, pos);
                    if (activeMovementOK(over, action, offset)) {
                        // TODO il controllo di priorità è da testare ed in più
                        // varia a seconda della direzione che hanno le entità
                        Comparator<Entities> comparator = null;
                        if (direction == Directions.RIGHT) {
                            comparator = (x, y) -> Integer.valueOf(x.getPosition().getPoint().getX())
                                    .compareTo(y.getPosition().getPoint().getX());
                        }
                        if (direction == Directions.LEFT) {
                            comparator = (x, y) -> Integer.valueOf(x.getPosition().getPoint().getX())
                                    .compareTo(y.getPosition().getPoint().getX());
                        }
                        over.stream().sorted(comparator).forEach(t -> {
                            Position post = t.getPosition();
                            post = this.collisionFixerTest(new Position(
                                    new Point(post.getPoint().getX() + offset.getOffsetX(),
                                            post.getPoint().getY() + offset.getOffsetY()),
                                    post.getDirection(), post.getDimension()), t, action, direction);
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
        if (!this.arena.getBullets().contains(collision.getX())) {
            final Rectangle collisionRectangle = collision.getY();
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
                if(!collision.getX().getContactDamage().isPresent()) {
                    this.platformEntities.putEntity(collision.getX().getCode(), entity);
                }
                break;
            case MOVE:
                fixPositionInMoveSwitch(posToFix, direction, collisionRectangle);
                orizzontalLimit = true;
                break;
            case MOVEONJUMP:
                if (new Rectangle(retToFix.x, lastPositionsMan.getLastPosition(entity.getCode()).getPoint().getY(),
                        retToFix.width, retToFix.height).intersects(collisionRectangle)) {
                    fixPositionInMoveSwitch(posToFix, direction, collisionRectangle);
                    orizzontalLimit = true;
                } else {
                    posToFix.setPoint(new Point(retToFix.x, collisionRectangle.y - retToFix.height));
                    verticalLimit = true;
                }
                break;
            case MOVEONFALL:
                if (new Rectangle(retToFix.x, lastPositionsMan.getLastPosition(entity.getCode()).getPoint().getY(),
                        retToFix.width, retToFix.height).intersects(collisionRectangle)) {
                    fixPositionInMoveSwitch(posToFix, direction, collisionRectangle);
                    orizzontalLimit = true;
                } else {
                    posToFix.setPoint(new Point(retToFix.x, collisionRectangle.y + collisionRectangle.height));
                    verticalLimit = true;
                    if(!collision.getX().getContactDamage().isPresent()) {
                        this.platformEntities.putEntity(collision.getX().getCode(), entity);
                    }
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
                    if (entity.getMovementManager().isPresent() && entity.getMovementManager().get().isCanFly()) {
                        entity.setAction(UtilityCollisionsDetection.getOppositeAction(action));
                    } else {
                        entity.setAction(Actions.STOP);
                        if (entity.equals(this.arena.getHero())) {
                            arena.getHero().setOnPlatform(true);
                        }
                    }
                    break;
                case JUMP:
                    entity.setAction(UtilityCollisionsDetection.getOppositeAction(action));
                    break;
                case MOVEONFALL:
                    if (entity.getMovementManager().isPresent() && entity.getMovementManager().get().isCanFly()) {
                        entity.setAction(UtilityCollisionsDetection.getOppositeAction(action));
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
                switch (direction) {
                case LEFT:
                    posToFix.setDirection(UtilityCollisionsDetection.getOppositeDirection(direction));
                    break;
                case RIGHT:
                    posToFix.setDirection(UtilityCollisionsDetection.getOppositeDirection(direction));
                    break;
                default:
                    break;

                }
            }
        }

        return posToFix;
    }

    // TODO statico?
    private void fixPositionInMoveSwitch(final Position posToFix, final Directions direction,
            final Rectangle collisionRectangle) {
        switch (direction) {
        case LEFT:
            posToFix.setPoint(new Point(collisionRectangle.x + collisionRectangle.width, posToFix.getPoint().getY()));
            break;
        case NONE:
            break;
        case RIGHT:
            posToFix.setPoint(
                    new Point(collisionRectangle.x - posToFix.getDimension().getWidth(), posToFix.getPoint().getY()));
            break;
        default:
            break;
        }
    }

    // TODO statico?
    private void modifyEntitiesInCollision(final Entities entity1, final Entities entity2) {
        entity1.getLifeManager().setLife(entity2.getContactDamage().orElseGet(() -> 0));
        entity2.getLifeManager().setLife(entity1.getContactDamage().orElseGet(() -> 0));
    }

    private boolean activeMovementOK(final Set<Entities> entities, final Actions action, final PointOffset offset) {
        boolean ret = true;
        if (UtilityMovement.splitActions(action).stream().anyMatch(t -> t == Actions.JUMP)) {
            for (final Entities t : entities) {
                final Point oldPoint = t.getPosition().getPoint();
                final Position newPosition = new Position(
                        new Point(oldPoint.getX() + offset.getOffsetX(), oldPoint.getY() + offset.getOffsetY()),
                        t.getPosition().getDirection(), t.getPosition().getDimension());
                if (UtilityMovement.checkBounds(newPosition, t.getMovementManager().get().getBounds(), action,
                        0) != UtilityMovement.CheckResult.TRUE) {
                    ret = false;
                }
                if (UtilityCollisionsDetection.getFirstCollision(UtilityCollisionsDetection.getRectangle(newPosition),
                        t, this.arena.getEntities(), this.platformEntities.getRelativeEntities(t.getCode())) != null) {
                    ret = false;
                }
                if (!this.platformEntities.getRelativeEntities(t.getCode()).isEmpty()) {
                    ret = activeMovementOK(this.platformEntities.getRelativeEntities(t.getCode()), action, offset);
                }
            }
        }
        return ret;
    }

}
