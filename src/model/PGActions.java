package model;

import java.util.Optional;
import java.util.function.BiFunction;

import control.Point;
import control.Position;

public enum PGActions {
    MRIGHT((t, s) -> (new Point(t.getX() + s, t.getY())), Optional.of(Position.Directions.RIGHT)),
    MLEFT((t, s) -> (new Point(t.getX() - s, t.getY())), Optional.of(Position.Directions.LEFT)),
    JUMP((t, s) -> (new Point(t.getX(), t.getY() + s)), Optional.empty()),
    DOWN((t, s) -> (new Point(t.getX(), t.getY() - s)), Optional.empty());

    
    private final BiFunction<Point, Integer, Point> function;
    private final Optional<Position.Directions> direction;
    
    PGActions(final BiFunction<Point, Integer, Point> function, final Optional<Position.Directions> direction) {
        this.function = function;
        this.direction = direction;
    }
    
    public Optional<Position.Directions> getDirection() {
        return direction;
    }

    public BiFunction<Point, Integer, Point> getFunction() {
        return this.function;
    }
}
