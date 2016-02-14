package model;

public class HeroMovementManager extends DinamicMovementManager{
    
    private boolean onJump = false;
    private int time = 0;

    public HeroMovementManager(Position position, Bounds bounds, int speed, boolean canFly) {
        super(position, bounds, speed, canFly);
    }

    @Override
    public Position getNextMove() {
    	Position newPosition = this.getPosition();
    	Actions action = determinateAction(newPosition.getDirection());
    	
        //boolean checkUp = this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getPosition().getSpeed()).getY() <= this.getBounds().getMaxY() + this.getPosition().getDimension().getHeight();
        boolean checkDx = action.getFunction().apply(this.getPosition().getPoint(), this.getSpeed()).getX() <= this.getBounds().getMaxX();
        boolean checkSx = action.getFunction().apply(this.getPosition().getPoint(), this.getSpeed()).getX() >= this.getBounds().getMinX();
        //boolean checkDown = this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getPosition().getSpeed()).getY() >= this.getBounds().getMinY() - this.getPosition().getDimension().getHeight();
        
        //MAGNANI PART BEGIN

        
        //TODO eventualmente aumetare il salto ed applicare comunque la gravità, a livello astratto èpiù logico forse
        if(!onJump) {
            newPosition = applyGravity(newPosition);
        }
        
        if(newPosition.getPoint().getY() == this.getPosition().getPoint().getY() && !onJump) {
        	newPosition.setDirection(Directions.NONE);
        	if(this.getDirection() == Directions.UP) {
        		time = 0;
            	this.onJump = true;
        	}
        } else if (newPosition.getPoint().getY() < this.getPosition().getPoint().getY()) {
        	newPosition.setDirection(Directions.DOWN);
        }
        
        if (onJump) {
            if(time < 60) {
                newPosition.setPoint(Actions.UP.getFunction().apply(newPosition.getPoint(), 5));
                newPosition.setDirection(Directions.UP);
                //TODO metti controllo bounds salto
                /*if (checkUp) {
                    newPosition.setPoint(ModelDirections.UP.getFunction().apply(newPosition.getPoint(), 1));
                } else {
                    time = 60;
                }*/
                time++;
            } else {
                onJump = false;
            }
        }
        
        //applico le funzioni dopo il calcolo dell'eventuale salto o caduta in modo da poter settare la reale direzione del personaggio 
        if (checkDx && checkSx && (action == Actions.LEFT || action == Actions.RIGHT)) {
        	newPosition.setPoint(action.getFunction().apply(newPosition.getPoint(), this.getSpeed()));
        	newPosition.setDirection(this.getDirection());
        }
        
        // MAGNANI PART FINISH
        
        return newPosition;
    }

    

}
