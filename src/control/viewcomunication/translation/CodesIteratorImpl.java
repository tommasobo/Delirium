package control.viewcomunication.translation;

import java.util.Iterator;

/**
 * Iterator for game's codes.
 * @author Matteo Magnani
 *
 */
public class CodesIteratorImpl implements Iterator<Integer> {

    private Integer number = -1;

    @Override
    public boolean hasNext() {
        return number + 1 != 0;
    }

    @Override
    public Integer next() {
        return ++number;
    }

}
