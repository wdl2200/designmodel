package Iterator;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConcreteAggregate a = new ConcreteAggregate();
		List<Object> list = new ArrayList<Object>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		a.setList(list);
		
		Iterator i = a.createIterate();
		Object item = i.First();
		System.out.println(item);
		
		while(!i.isBool()){
			System.out.println(i.currentItem());
			i.Next();
		}
		
	}

}
