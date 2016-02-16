package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import control.Point;

public class UtilityMovement {
    
    enum CheckResult {
        TRUE, TRUEBUTFIX, FALSE;
    }
    
    public static List<Actions> splitActions(Actions action) {
        List<Actions> ret = new LinkedList<>();
        switch(action) {
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
    
    public static Optional<Position> Move(Position positionn, Bounds bounds, Actions action, int speed) {
        Position position = new Position(positionn.getPoint(), positionn.getDirection(), positionn.getDimension());
        switch(checkBounds(position, bounds, action, speed)) {
        case FALSE:
            return Optional.empty();
        case TRUE:
            position.setPoint(action.getFunction().deterimnateNewPoint(position.getPoint(), speed, position.getDirection()));
            break;
        case TRUEBUTFIX:
            position = fixPositionBounds(position, bounds, action);
            break;
        }
        return Optional.of(position);
    }
    
    /**
     *  @author Matteo Magnani 
     */
    private static CheckResult checkBounds(Position position, Bounds bounds, Actions action, int speed) {
        position.setPoint(action.getFunction().deterimnateNewPoint(position.getPoint(), speed, position.getDirection()));
        CheckResult checkDown = position.getPoint().getY() >= bounds.getMinY() ? CheckResult.TRUE : (position.getPoint().getY() + AbstractMovementManager.GRAVITY > bounds.getMinY() ? CheckResult.TRUEBUTFIX : CheckResult.FALSE);
        CheckResult checkUp = position.getPoint().getY() + position.getDimension().getHeight() <= bounds.getMaxY() ? CheckResult.TRUE : (position.getPoint().getY() + position.getDimension().getHeight() - speed > bounds.getMinY() ? CheckResult.TRUEBUTFIX : CheckResult.FALSE);
        CheckResult checkMove;
        switch(position.getDirection()) {
        case LEFT: 
            checkMove = position.getPoint().getX() >= bounds.getMinX() ? CheckResult.TRUE : (position.getPoint().getX() + speed > bounds.getMinX() ? CheckResult.TRUEBUTFIX : CheckResult.FALSE);
            break;
        case RIGHT:
            checkMove = position.getPoint().getX() + position.getDimension().getWidth() <= bounds.getMaxX() ? CheckResult.TRUE : (position.getPoint().getX() +  position.getDimension().getWidth() - speed < bounds.getMaxX() ? CheckResult.TRUEBUTFIX : CheckResult.FALSE);
            break;
        case NONE:
            checkMove = CheckResult.TRUE;
            break;
        default:
                throw new IllegalArgumentException();
        }
        //TODO metodo statico, lavorare su copia protetta!!!!
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
    
    /**
     * @author Matteo Magnani
     * @param position
     * @param gravity
     * @return
     */
    private static Position fixPositionBounds(Position position, Bounds bounds, Actions action) {

        //TODO metodo statico, lavorare su copia protetta!!!!
        switch (action) {
                case FALL:
                        position.setPoint(new Point(position.getPoint().getX(), bounds.getMinY()));
                        break;
                case MOVE:
                        switch(position.getDirection()) {
                        case LEFT:
                                position.setPoint(new Point(bounds.getMinX(), position.getPoint().getY()));
                                break;
                        case RIGHT:
                                position.setPoint(new Point(bounds.getMaxX() - position.getDimension().getWidth(), position.getPoint().getY()));
                                break;
                        case NONE:
                                break;
                        default:
                                break;
                        }
                        break;
                case JUMP:
                        position.setPoint(new Point(position.getPoint().getX(), bounds.getMaxY() - position.getDimension().getHeight()));
                default:
                        break;
                }
        
        return position;
    }

}
