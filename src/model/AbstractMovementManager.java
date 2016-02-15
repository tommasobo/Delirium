package model;

import control.Dimension;
import control.Point;

public abstract class AbstractMovementManager implements MovementManager {
    
    public static final int GRAVITY = 6;

    private final Position position;
    private boolean canFly; 
    private int speed;
    private final Bounds bounds;
    private Actions action;

    public AbstractMovementManager(Position position, Bounds bounds, Actions action, int speed, boolean canFly) {
        this.position = position;
        this.canFly = canFly;
        this.bounds = bounds;
        this.speed = speed;
        this.action = action;
    }


    abstract public Position getNextMove();
    
    /**
     * @author Matteo Magnani
     * @param position
     * @return
     */
    protected Position applyGravity(Position position) {
        if(!canFly) {
            position.setPoint(Actions.FALL.getFunction().deterimnateNewPoint(position.getPoint(), AbstractMovementManager.GRAVITY, position.getDirection()));
            if(!checkBounds(position, this.bounds, Actions.FALL)) {
            	//System.out.println("ciao");
            	position = fixPositionBounds(position, this.bounds, Actions.FALL);
            } else {
            	//TODO aggiungere azione di fall a livello di controllo collisioni
            	this.setAction(Actions.FALL);
            }
           /* if(position.getPoint().getY() < bounds.getMinY()) {
                position.setPoint(new Point(position.getPoint().getX(), bounds.getMinY()));
            }*/
            //TODO eventualmente metti a NONE la position se hai raggiunto i bounds
        }
        return position;
    }
    
    
    /**
     * 	@author Matteo Magnani 
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
			case DOWN:
				return position.getPoint().getY() >= bounds.getMinY();
			case NONE:
				return true;
			case UP:
				return (position.getPoint().getY() + position.getDimension().getHeight()) <= bounds.getMaxY();
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
			case DOWN:
				position.setPoint(new Point(position.getPoint().getX(), bounds.getMinY()));
				break;
			case NONE:
				break;
			case UP:
				position.setPoint(new Point(position.getPoint().getX(), bounds.getMaxY() - position.getDimension().getHeight()));
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
    
    
    public Position getPosition() {
        return new Position(this.position.getPoint(), this.position.getDirection(), this.position.getDimension());
    }
    
    public void setPosition(final Point point, final Directions direction) {
        this.position.setPoint(point);
        this.position.setDirection(direction);
    }
    
    protected void setDirection(final Directions direction) {
        this.position.setDirection(direction);
    }
    
    protected Directions getDirection() {
        return position.getDirection();
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void setSpeed( final int speed) {
        this.speed = speed;
    }
    
    public Bounds getBounds() {
        return this.bounds;
    }
    
    public boolean isCanFly() {
        return this.canFly;
    }
    
    public void setAction(final Actions action) {
        this.action = action;
    }
    
    public Actions getAction() {
        return this.action;
    }
    
}
