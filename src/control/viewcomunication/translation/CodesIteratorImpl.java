package control.viewcomunication.translation;

import java.util.Iterator;

public class CodesIteratorImpl implements Iterator<Integer> {
	
	private Integer number = -1;

	@Override
	public boolean hasNext() {
		return number+1 != 0;
	}

	@Override
	public Integer next() {
	    //TODO lancia eccezioni dopo un po
		return ++number;
	}

}