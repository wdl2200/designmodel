package observer;

import java.util.ArrayList;
import java.util.List;
import observer.Observer;


public abstract class Subject {
	private List<Observer> observers = new ArrayList<Observer>();
	
	public void attack(Observer observer){
		observers.add(observer);
	}
	
	public void detach(Observer observer){
		observers.remove(observer);
	}
	
	public void Notify(){
		for(Observer o : observers){
			o.update();
		}
	}
	
}
