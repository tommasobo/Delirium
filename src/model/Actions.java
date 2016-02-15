package model;

import java.util.function.BiFunction;

import control.Point;

public enum Actions {
    MOVE((t, s) -> (new Point(t.getX() + s, t.getY()))),
    JUMP((t, s) -> (new Point(t.getX(), t.getY() + s))),
    FALL((t, s) -> (new Point(t.getX(), t.getY() - s))),
    STOP((t, s) -> (new Point(t.getX(), t.getY())));

    
    private final BiFunction<Point, Integer, Point> function;
    
    Actions(final BiFunction<Point, Integer, Point> function) {
        this.function = function;
    }
    
    public BiFunction<Point, Integer, Point> getFunction() {
        return this.function;
    }
}
