package model;

public class HeroMovementManager extends DinamicMovementManager{
    
    private boolean onJump = false;
    private boolean onFall = false;
    private int time = 0;

    public HeroMovementManager(ModelPosition position, int speed, Bounds bounds) {
        super(position, speed, bounds);
    }

    @Override
    public ModelPosition getNextMove() {
        boolean checkDx = this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getSpeed()).getX() <= this.getBounds().getMaxX();
        boolean checkSx = this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getSpeed()).getX() >= this.getBounds().getMinX();
        boolean checkUp = this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getSpeed()).getY() <= this.getBounds().getMaxY() + this.getPosition().getDimension().getHeight();
        boolean checkDown = this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getSpeed()).getY() >= this.getBounds().getMinY() - this.getPosition().getDimension().getHeight();
        
        if (checkDx && checkSx && this.getDirection() != ModelDirections.UP) {
            this.setPosition(this.getDirection().getFunction().apply(this.getPosition().getPoint(), this.getSpeed()), this.getDirection());
        } else if (this.getDirection() == ModelDirections.UP) {
            this.onJump = true;
        }
        this.setDirection(ModelDirections.NONE);
        
        if (onJump) {
            if(time < 60) {
                if (checkUp) {
                    this.setPosition(ModelDirections.UP.getFunction().apply(this.getPosition().getPoint(), 1), this.getDirection());
                } else {
                    time = 60;
                }
                time++;
            }
        }
        
        if (time >= 60) {
            onJump = false;
            onFall= true;
        }
        
        if (onFall) {
            if(checkDown) {
                this.setPosition(ModelDirections.DOWN.getFunction().apply(this.getPosition().getPoint(), 1), this.getDirection());
            } else {
                onFall = false;
                time = 0;
            }
        }
        
        return this.getPosition();
    }

}
