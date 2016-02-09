package model;

import control.Position;

public class StaticMovementManager extends AbstractMovementManager {

    public StaticMovementManager(Position position) {
        super(position);
    }
    
    //Si mantiene comunque setter e getter per un motivo diestendibilità se, utopicamente, un giorno si voglia modificare
    //il gioco in modo che anche le casse e tutti gli oggetti statici possano essere spostati dall'eroe

    @Override
    public Position getNextMove() {
        return this.getPosition();
    }
  

}
