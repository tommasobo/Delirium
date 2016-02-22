package model;

import java.util.HashMap;
import java.util.Map;

import control.Point;

public class LastPositionsManager {
    private final Map<Integer,Position> lastPositions;
    
    public LastPositionsManager() {
        this.lastPositions = new HashMap<>();
    }
    
    public Position getLastPosition(Entities entity) {
        return getLastPosition(entity.getCode());
    }
    
    public Position getLastPosition(int code) {
        Position pos = this.lastPositions.get(code);
        return new Position(pos.getPoint(), pos.getDirection(), pos.getDimension());
    }
    
    public PointOffset getOffsetFromLastPosition(Entities entity, Position position) {
        return getOffsetFromLastPosition(entity.getCode(), position);
    }
    
    public PointOffset getOffsetFromLastPosition(int code, Position position) {
        Point point = getLastPosition(code).getPoint();
        return new PointOffset(position.getPoint().getX() - point.getX(), position.getPoint().getY() - point.getY());
    }
    
    public void putPosition(Entities entity) {
        this.lastPositions.put(entity.getCode(), entity.getPosition());
    }
    
    public void putPosition(Entities entity, Position position) {
        putPosition(entity.getCode(), position);
    }
    
    public void putPosition(int code, Position position) {
        Position pos = new Position(position.getPoint(), position.getDirection(), position.getDimension());
        this.lastPositions.put(code, pos);
    }
    
    private void clearLastPositions() {
        
    }
    
}
