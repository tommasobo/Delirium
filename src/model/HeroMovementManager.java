package model;

public class HeroMovementManager extends DinamicMovementManager{
    
    private boolean onJump = false;
    private int time = 0;

    public HeroMovementManager(Position position, Bounds bounds, int speed, boolean canFly) {
        super(position, bounds, speed, canFly);
    }

    @Override
    public Position getNextMove() {
        Actions action = determinateAction(this.getDirection());
        //boolean checkUp = this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getPosition().getSpeed()).getY() <= this.getBounds().getMaxY() + this.getPosition().getDimension().getHeight();
        boolean checkDx = action.getFunction().apply(this.getPosition().getPoint(), this.getSpeed()).getX() <= this.getBounds().getMaxX();
        boolean checkSx = action.getFunction().apply(this.getPosition().getPoint(), this.getSpeed()).getX() >= this.getBounds().getMinX();
        //boolean checkDown = this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getPosition().getSpeed()).getY() >= this.getBounds().getMinY() - this.getPosition().getDimension().getHeight();
        
        //MAGNANI PART BEGIN
        
        if (checkDx && checkSx && this.getDirection() != Directions.UP && this.getDirection() != Directions.DOWN) {
            this.setPosition(action.getFunction().apply(this.getPosition().getPoint(), this.getSpeed()), this.getDirection());
        } else if (this.getDirection() == Directions.UP) {
            this.onJump = true;
        }

        Position newPosition;
        
        if(!onJump) {
            newPosition = applyGravity(this.getPosition());
        } else {
            newPosition = this.getPosition();
            
        }
        
        if(newPosition.getPoint().getY() == this.getPosition().getPoint().getY() && !onJump ) {
            time = 0;
            if(this.getDirection() == Directions.UP) {
                this.onJump = true;
            }
        }
        
        if (onJump) {
            if(time < 60) {
                newPosition.setPoint(Actions.UP.getFunction().apply(newPosition.getPoint(), 5));
                /*if (checkUp) {
                    newPosition.setPoint(ModelDirections.UP.getFunction().apply(newPosition.getPoint(), 1));
                } else {
                    time = 60;
                }*/
                time++;
            } else {
                onJump = false;
                newPosition.setDirection(Directions.DOWN);
            }
        }
        // MAGNANI PART FINISH
        
        return newPosition;
    }

    

}
