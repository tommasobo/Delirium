package control;

public class CodesIteratorImpl implements CodesIterator {
	
	private Integer number = 0;

	@Override
	public boolean hasNext() {
		return (number+1) != 0;
	}

	@Override
	public Integer next() {
		return ++number;
	}

}
