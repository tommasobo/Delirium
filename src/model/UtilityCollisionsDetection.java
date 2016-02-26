package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import utility.Dimension;
import utility.Pair;

public final class UtilityCollisionsDetection {
    
    private UtilityCollisionsDetection() {
        
    }
    public static Directions getOppositeDirection(final Directions direction) {
        switch(direction) {
        case LEFT:
            return Directions.RIGHT;
        case RIGHT:
            return Directions.LEFT;
        default:
            return direction;
        
        }
    }
    
    public static Actions getOppositeAction(final Actions action) {
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
    
    public static Actions realAction(final Entities entity) {
        return entity.getMovementManager().isPresent() ? entity.getMovementManager().get().getAction()
                : Actions.STOP;
    }
    
    public static Rectangle getRectangle(final Position p) {
        return new Rectangle(p.getPoint().getX(), p.getPoint().getY(), p.getDimension().getWidth(), p.getDimension().getHeight());
    }
    
    public static boolean onBounds(final Position pos, final Entities entity) {
        Bounds bounds;
        if(entity.getMovementManager().isPresent()) {
            bounds = entity.getMovementManager().get().getBounds();
        } else {
            return true;
        }
        final Point point = pos.getPoint();
        final Dimension dimension = pos.getDimension();
        
        return point.getX() == bounds.getMinX() || point.getY() == bounds.getMinY() || point.getX() + dimension.getWidth() == bounds.getMaxX() || point.getY() + dimension.getHeight()== bounds.getMaxY();
        
    }
    
    public static Pair<Entities, Rectangle> getFirstCollision(final Rectangle rectangle, final Entities entity, final Collection<? extends Entities> entities, final Collection<? extends Entities> exclusions) {
        
        final Optional<? extends Entities> ret = entities.stream().filter(entityToTest -> entityToTest.getCode() != entity.getCode())
                .filter(t -> !exclusions.contains(t))
                .filter(t -> t.getLifeManager().getLife() > 0)
                .filter(entityToTest -> UtilityCollisionsDetection.getRectangle(entityToTest.getPosition()).intersects(rectangle)).findFirst();

        if (ret.isPresent()) {
            final Entities inCollision = ret.get();
            return new Pair<>(inCollision, (Rectangle) rectangle.createIntersection(UtilityCollisionsDetection.getRectangle(inCollision.getPosition())));
        }

        return null;
    }
    
    public static Pair<Entities, Rectangle> getFirstCollision(final Rectangle rectangle, final Entities entity, final Collection<? extends Entities> entities) {
        return getFirstCollision(rectangle, entity, entities, new ArrayList<>());
    }
}
