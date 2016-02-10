package model;


public class StaticMovementManager extends AbstractMovementManager {

    public StaticMovementManager(ModelPosition position) {
        super(position);
    }

    @Override
    public ModelPosition getNextMove() {
        return this.getPosition();
    }
    
    
  

}
