package model;

import control.Point;

public class UtilityMovement {
    
    /**
     *  @author Matteo Magnani 
     */
    protected static boolean checkBounds(Position position, Bounds bounds, Actions action) {

        //TODO metodo statico, lavorare su copia protetta!!!!
        switch (action) {
                case FALL:
                        //System.out.println(position.getPoint().getY() +" "+ bounds.getMinY());
                        return position.getPoint().getY() >= bounds.getMinY();
                case MOVE:
                        switch(position.getDirection()) {
                        case LEFT:
                                        return position.getPoint().getX() >= bounds.getMinX();
                        case RIGHT:
                                        return (position.getPoint().getX() + position.getDimension().getWidth()) <= bounds.getMaxX();
                        /*case DOWN:
                                return position.getPoint().getY() >= bounds.getMinY();*/
                        case NONE:
                                return true;
                        /*case UP:
                                return (position.getPoint().getY() + position.getDimension().getHeight()) <= bounds.getMaxY();*/
                        default:
                                return false;
                        }
                case JUMP:
                        return (position.getPoint().getY() + position.getDimension().getHeight()) <= bounds.getMaxY();
                case STOP:
                        return true;
                default:
                        return false;
                }
    }
    
    /**
     * @author Matteo Magnani
     * @param position
     * @param gravity
     * @return
     */
    protected static Position fixPositionBounds(Position position, Bounds bounds, Actions action) {

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
                        /*case DOWN:
                                position.setPoint(new Point(position.getPoint().getX(), bounds.getMinY()));
                                break;*/
                        case NONE:
                                break;
                        /*case UP:
                                position.setPoint(new Point(position.getPoint().getX(), bounds.getMaxY() - position.getDimension().getHeight()));
                                break;*/
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
