package model;

public class HeroMovementManager extends DinamicMovementManager{
    
    private boolean onJump = false;
    private int time = 0;

    public HeroMovementManager(ModelPosition position, Bounds bounds, boolean canFly) {
        super(position, bounds, canFly);
    }

    @Override
    public ModelPosition getNextMove() {
        //boolean checkUp = this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getPosition().getSpeed()).getY() <= this.getBounds().getMaxY() + this.getPosition().getDimension().getHeight();
        boolean checkDx = this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getPosition().getSpeed()).getX() <= this.getBounds().getMaxX();
        boolean checkSx = this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getPosition().getSpeed()).getX() >= this.getBounds().getMinX();
        //boolean checkDown = this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getPosition().getSpeed()).getY() >= this.getBounds().getMinY() - this.getPosition().getDimension().getHeight();
        
        //MAGNANI PART BEGIN
        
        if (checkDx && checkSx && this.getDirection() != ModelDirections.UP) {
            this.setPosition(this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getPosition().getSpeed()), this.getDirection());
        } else if (this.getDirection() == ModelDirections.UP) {
            this.onJump = true;
        }

        ModelPosition newPosition;
        
        if(!onJump) {
            newPosition = applyGravity(this.getPosition());
        } else {
            newPosition = this.getPosition();
            
        }
        
        if(newPosition.getPoint().getY() == this.getPosition().getPoint().getY() && !onJump ) {
            time = 0;
            if(this.getDirection() == ModelDirections.UP) {
                this.onJump = true;
            }
        }
        
        if (onJump) {
            if(time < 20) {
                newPosition.setPoint(ModelDirections.UP.getFunction().apply(newPosition.getPoint(), 5));
                /*if (checkUp) {
                    newPosition.setPoint(ModelDirections.UP.getFunction().apply(newPosition.getPoint(), 1));
                } else {
                    time = 60;
                }*/
                time++;
            } else {
                onJump = false;
                newPosition.setDirection(ModelDirections.STOP);
            }
        }
        // MAGNANI PART FINISH
        
        return newPosition;
    }

}
