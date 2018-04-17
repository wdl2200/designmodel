package visitor;

import java.util.ArrayList;
import java.util.List;

public class ObjectStructure {
	
	
	private List<Element>  list = new ArrayList<Element>();
	
	public void attach(Element e){
		
		list.add(e);
	}
	
	public void remove(Element e){
		list.remove(e);
	}
	
	public void notify(Visitor visitor){
		for(Element e : list){
			e.accept(visitor);
		}
	}

}
