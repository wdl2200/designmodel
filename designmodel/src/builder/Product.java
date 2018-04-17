package builder;

import java.util.ArrayList;
import java.util.List;

public class Product {
	
	List<String> parts = new ArrayList<String>();
	
	public void add(String part){
		parts.add(part);
	}
	
	public void show(){
		for(String value : parts){
			System.out.println(value);
		}
	}

}
