package model;

import java.util.function.BiFunction;

import control.Point;

public enum Actions {
    RIGHT((t, s) -> (new Point(t.getX() + s, t.getY()))),
    LEFT((t, s) -> (new Point(t.getX() - s, t.getY()))),
    UP((t, s) -> (new Point(t.getX(), t.getY() + s))),
    DOWN((t, s) -> (new Point(t.getX(), t.getY() - s))),
    STOP((t, s) -> (new Point(t.getX(), t.getY())));

    
    private final BiFunction<Point, Integer, Point> function;
    
    Actions(final BiFunction<Point, Integer, Point> function) {
        this.function = function;
    }
    
    public BiFunction<Point, Integer, Point> getFunction() {
        return this.function;
    }
}
