package composite;

import java.util.ArrayList;
import java.util.List;

public class Component {
	
	private String name;
	
	private List<Component> components = new ArrayList<Component>();
	
	public void add(Component component){
		components.add(component);
	}
	
	public void remove(Component component){
		components.remove(component);
	}
	
	public void display(){
		System.out.println(name);
		for(Component component : components){
			component.display();
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
