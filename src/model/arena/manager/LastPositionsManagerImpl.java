package model.arena.manager;

import java.util.HashMap;
import java.util.Map;

import model.arena.entities.Entities;
import model.arena.entities.Point;
import model.arena.entities.Position;

/**
 * Database that contains last position of entities
 * @author Matteo Magnani
 *
 */
class LastPositionsManagerImpl implements LastPositionsManager {
    private final Map<Integer,Position> lastPositions;
    
    public LastPositionsManagerImpl() {
        this.lastPositions = new HashMap<>();
    }
    
    
    @Override
    public Position getLastPosition(Entities entity) {
        return getLastPosition(entity.getCode());
    }
    
    
    @Override
    public Position getLastPosition(int code) {
        Position pos = this.lastPositions.get(code);
        return new Position(pos.getPoint(), pos.getDirection(), pos.getDimension());
    }
    
    
    @Override
    public PointOffset getOffsetFromLastPosition(Entities entity, Position position) {
        return getOffsetFromLastPosition(entity.getCode(), position);
    }
    
    
    @Override
    public PointOffset getOffsetFromLastPosition(int code, Position position) {
        Point point = getLastPosition(code).getPoint();
        return new PointOffset(position.getPoint().getX() - point.getX(), position.getPoint().getY() - point.getY());
    }
    
    
    @Override
    public void putPosition(Entities entity) {
        this.lastPositions.put(entity.getCode(), entity.getPosition());
    }
    
    
    @Override
    public void putPosition(Entities entity, Position position) {
        putPosition(entity.getCode(), position);
    }
    
    
    @Override
    public void putPosition(int code, Position position) {
        Position pos = new Position(position.getPoint(), position.getDirection(), position.getDimension());
        this.lastPositions.put(code, pos);
    }
    
}
