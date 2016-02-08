package model;

import java.util.Optional;
import java.util.function.Function;

import control.Point;
import control.Position;

public enum PGActions {
    MRIGHT(t -> new Point(t.getX() + 1, t.getY()), Optional.of(Position.Directions.RIGHT)),
    MLEFT(t -> new Point(t.getX() - 1, t.getY()), Optional.of(Position.Directions.LEFT)),
    JUMP(t -> new Point(t.getX(), t.getY() + 1), Optional.empty());

    
    private final Function<Point, Point> function;
    private final Optional<Position.Directions> direction;
    
    PGActions(final Function<Point, Point> function, final Optional<Position.Directions> direction) {
        this.function = function;
        this.direction = direction;
    }
    
    public Optional<Position.Directions> getDirection() {
        return direction;
    }

    public Function<Point, Point> getFunction() {
        return this.function;
    }
}
