package model.arena.utility;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.arena.entities.Point;
import model.arena.entities.Position;

public class UtilityMovement {

    public enum CheckResult {
        TRUE, TRUEBUTFIX, FALSE;
    }

    public static List<Actions> splitActions(final Actions action) {
        final List<Actions> ret = new LinkedList<>();
        switch (action) {
        case FALL:
        case JUMP:
        case MOVE:
        case STOP:
            ret.add(action);
            break;
        case MOVEONFALL:
            ret.add(Actions.MOVE);
            ret.add(Actions.FALL);
            break;
        case MOVEONJUMP:
            ret.add(Actions.MOVE);
            ret.add(Actions.JUMP);
            break;
        default:
            throw new IllegalArgumentException();
        }

        return ret;
    }

    public static Optional<Position> move(final Position position, final Bounds bounds, final Actions action,
            final int speed) {
        Position newPosition = new Position(position.getPoint(), position.getDirection(), position.getDimension());
        switch (checkBounds(newPosition, bounds, action, speed)) {
        case FALSE:
            return Optional.empty();
        case TRUE:
            newPosition.setPoint(action.apply(newPosition.getPoint(), speed, newPosition.getDirection()));
            break;
        case TRUEBUTFIX:
            newPosition = fixPositionBounds(newPosition, bounds, action);
            break;
        default:
            throw new IllegalStateException();
        }
        return Optional.of(newPosition);
    }

    public static CheckResult checkBounds(final Position position, final Bounds bounds, final Actions action,
            final int speed) {
        final Position newPosition = new Position(position.getPoint(), position.getDirection(), position.getDimension());
        newPosition.setPoint(action.apply(newPosition.getPoint(), speed, newPosition.getDirection()));
        final CheckResult checkDown = newPosition.getPoint().getY() >= bounds.getMinY() ? CheckResult.TRUE
                : (newPosition.getPoint().getY() + speed > bounds.getMinY() ? CheckResult.TRUEBUTFIX
                        : CheckResult.FALSE);
        final CheckResult checkUp = newPosition.getPoint().getY() + newPosition.getDimension().getHeight() <= bounds.getMaxY()
                ? CheckResult.TRUE
                : (newPosition.getPoint().getY() + newPosition.getDimension().getHeight() - speed < bounds.getMaxY()
                        ? CheckResult.TRUEBUTFIX : CheckResult.FALSE);
        CheckResult checkMove;
        switch (newPosition.getDirection()) {
        case LEFT:
            checkMove = newPosition.getPoint().getX() >= bounds.getMinX() ? CheckResult.TRUE
                    : (newPosition.getPoint().getX() + speed > bounds.getMinX() ? CheckResult.TRUEBUTFIX
                            : CheckResult.FALSE);
            break;
        case RIGHT:
            checkMove = newPosition.getPoint().getX() + newPosition.getDimension().getWidth() <= bounds.getMaxX()
                    ? CheckResult.TRUE
                    : (newPosition.getPoint().getX() + newPosition.getDimension().getWidth() - speed < bounds.getMaxX()
                            ? CheckResult.TRUEBUTFIX : CheckResult.FALSE);
            break;
        case NONE:
            checkMove = CheckResult.TRUE;
            break;
        default:
            throw new IllegalArgumentException();
        }
        switch (action) {
        case FALL:
            return checkDown;
        case MOVE:
            return checkMove;
        case JUMP:
            return checkUp;
        case STOP:
            return CheckResult.TRUE;
        default:
            throw new IllegalArgumentException();

        }
    }

    private static Position fixPositionBounds(final Position position, final Bounds bounds, final Actions action) {

        switch (action) {
        case FALL:
            position.setPoint(new Point(position.getPoint().getX(), bounds.getMinY()));
            break;
        case MOVE:
            switch (position.getDirection()) {
            case LEFT:
                position.setPoint(new Point(bounds.getMinX(), position.getPoint().getY()));
                break;
            case RIGHT:
                position.setPoint(
                        new Point(bounds.getMaxX() - position.getDimension().getWidth(), position.getPoint().getY()));
                break;
            case NONE:
                break;
            default:
                throw new IllegalArgumentException();
            }
            break;
        case JUMP:
            position.setPoint(
                    new Point(position.getPoint().getX(), bounds.getMaxY() - position.getDimension().getHeight()));
        case MOVEONFALL:
        case MOVEONJUMP:
            break;
        default:
            throw new IllegalArgumentException();
        }

        return position;
    }

}
