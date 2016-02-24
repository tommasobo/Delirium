package model;

import java.util.Optional;

import control.Point;

public abstract class DinamicMovementManager implements MovementManager {
    
    public static final int GRAVITY = 6;

    private final Position position;
    private boolean canFly; 
    private int speed;
    private final Bounds bounds;
    private Actions action;

    public DinamicMovementManager(final Position position, final Bounds bounds, final Actions action, 
            final int speed, final boolean canFly) {
        this.position = position;
        this.canFly = canFly;
        this.bounds = bounds;
        this.speed = speed;
        this.action = action;
    }


    abstract public Position getNextMove();
    
    
    /*protected Position applyGravity(Position position) {
        if(!canFly) {
            position.setPoint(Actions.FALL.getFunction().deterimnateNewPoint(position.getPoint(), AbstractMovementManager.GRAVITY, position.getDirection()));
            if(!UtilityMovement.checkBounds(position, this.bounds, Actions.FALL)) {
            	//System.out.println("ciao");
            	position = UtilityMovement.fixPositionBounds(position, this.bounds, Actions.FALL);
            } else {
            	//TODO aggiungere azione di fall a livello di controllo collisioni
            	this.setAction(Actions.FALL);
            }
           /* if(position.getPoint().getY() < bounds.getMinY()) {
                position.setPoint(new Point(position.getPoint().getX(), bounds.getMinY()));
            }*//*
            //TODO eventualmente metti a NONE la position se hai raggiunto i bounds
        }
        return position;
    }*/
    
    
    /**
     * @author Matteo Magnani
     * @param position
     * @return
     */
    protected Position applyGravity() {
        if(!canFly) {
            Optional<Position> opPos = UtilityMovement.Move(this.getPosition(), this.getBounds(), Actions.FALL, DinamicMovementManager.GRAVITY);
            if(opPos.isPresent()) {
                if(this.getAction() == Actions.MOVE || this.getAction() == Actions.MOVEONJUMP) {
                    this.setAction(Actions.MOVEONFALL);
                } else {
                    this.setAction(Actions.FALL);
                }
                return opPos.get();
            } else {
                return this.getPosition();
            }
        }
        return this.getPosition();
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
    
    public void setSpeed(final int speed) {
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
