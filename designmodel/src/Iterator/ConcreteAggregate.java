package Iterator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAggregate implements Aggregate {
	
	private List<Object> list = new ArrayList<Object>();
	
	@Override
	public Iterator createIterate() {
		return new ConcreteIterator(this);
	}
	
	public Object getFirst(){
		if(list.size() == 0){
			return null;
		}
		return list.get(0);
	}
	
	public int getCount(){
		
		return list.size();
	}
	
	public Object getIndex(int index){
		if(list.size() <= index){
			return null;
		}
		return list.get(index);
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
	
	
	
}
