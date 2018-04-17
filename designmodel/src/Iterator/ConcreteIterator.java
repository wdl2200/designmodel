package Iterator;

public class ConcreteIterator implements Iterator {
	
	private ConcreteAggregate aggregate;
	
	private int current  =0;
	
	public ConcreteIterator(ConcreteAggregate aggregate){
		this.aggregate = aggregate;
	}
	
	@Override
	public Object First() {
		// TODO Auto-generated method stub
		return aggregate.getFirst();
	}

	@Override
	public Object Next() {
		current ++;
		return aggregate.getIndex(current);
	}

	@Override
	public Object currentItem() {
		// TODO Auto-generated method stub
		return aggregate.getIndex(current);
	}

	@Override
	public boolean isBool() {
		// TODO Auto-generated method stub
		return current>=aggregate.getCount()?true:false;
	}

}
