package model.arena.manager;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import model.arena.entities.Entities;
import model.arena.entities.Point;
import model.arena.entities.Position;
import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.Directions;
import utility.Dimension;
import utility.Pair;

final class UtilityCollisionsDetection {

    private UtilityCollisionsDetection() {
    }

    /**
     * 
     * @param direction
     *            A direction
     * @return the opposite direction
     */
    public static Directions getOppositeDirection(final Directions direction) {
        switch (direction) {
        case LEFT:
            return Directions.RIGHT;
        case RIGHT:
            return Directions.LEFT;
        default:
            return direction;

        }
    }

    /**
     * 
     * @param direction
     *            An action
     * @return the opposite action
     */
    public static Actions getOppositeAction(final Actions action) {
        switch (action) {
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

    /**
     * 
     * @param entity
     *            The Entity
     * @return The entity's movement real action
     */
    public static Actions realAction(final Entities entity) {
        return entity.getMovementManager().isPresent() ? entity.getMovementManager().get().getAction() : Actions.STOP;
    }

    /**
     * 
     * @param p
     *            A position
     * @return The Rectangle that represents the input position
     */
    public static Rectangle getRectangle(final Position p) {
        return new Rectangle(p.getPoint().getX(), p.getPoint().getY(), p.getDimension().getWidth(),
                p.getDimension().getHeight());
    }

    /**
     * Check id an entity is on him bounds
     * 
     * @param pos
     * @param entity
     * @return
     */
    public static boolean onBounds(final Position pos, final Entities entity) {
        Bounds bounds;
        if (entity.getMovementManager().isPresent()) {
            bounds = entity.getMovementManager().get().getBounds();
        } else {
            return true;
        }
        final Point point = pos.getPoint();
        final Dimension dimension = pos.getDimension();

        return point.getX() == bounds.getMinX() || point.getY() == bounds.getMinY()
                || point.getX() + dimension.getWidth() == bounds.getMaxX()
                || point.getY() + dimension.getHeight() == bounds.getMaxY();

    }

    /**
     * This methods returns the first collision between the input entity's
     * position rectangle and the input list of entities;
     * 
     * @param rectangle
     *            Entity's position rectangle
     * @param entity
     *            The entity
     * @param entities
     *            Entities to check
     * @param exclusions
     *            Entities to exclude from checking
     * @return A pair that contains the collision entity and the rectangle that
     *         represents the collision
     */
    public static Pair<Entities, Rectangle> getFirstCollision(final Rectangle rectangle, final Entities entity,
            final Collection<? extends Entities> entities, final Collection<? extends Entities> exclusions) {

        final Optional<? extends Entities> ret = entities.stream()
                .filter(entityToTest -> entityToTest.getCode() != entity.getCode()).filter(t -> !exclusions.contains(t))
                .filter(t -> t.getLifeManager().getLife() > 0).filter(entityToTest -> UtilityCollisionsDetection
                        .getRectangle(entityToTest.getPosition()).intersects(rectangle))
                .findFirst();

        if (ret.isPresent()) {
            final Entities inCollision = ret.get();
            return new Pair<>(inCollision, (Rectangle) rectangle
                    .createIntersection(UtilityCollisionsDetection.getRectangle(inCollision.getPosition())));
        }

        return null;
    }

    /**
     * This methods returns the first collision between the input entity's
     * position rectangle and the input list of entities;
     * 
     * @param rectangle
     *            Entity's position rectangle
     * @param entity
     *            The entity
     * @param entities
     *            Entities to check
     * @return A pair that contains the collision entity and the rectangle that
     *         represents the collision
     */
    public static Pair<Entities, Rectangle> getFirstCollision(final Rectangle rectangle, final Entities entity,
            final Collection<? extends Entities> entities) {
        return getFirstCollision(rectangle, entity, entities, new ArrayList<>());
    }
}
