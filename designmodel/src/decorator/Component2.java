package decorator;

public abstract class Component2 {
	
	ConcreteComponent2 concreteComponent;
	
	
	public ConcreteComponent2 getConcreteComponent() {
		return concreteComponent;
	}

	public void setConcreteComponent(ConcreteComponent2 concreteComponent) {
		this.concreteComponent = concreteComponent;
	}



	public void operation(){
		concreteComponent.operation();
	}

}
