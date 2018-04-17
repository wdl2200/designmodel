package decorator;

public class Decorator2 extends Component2 {
	
	private Decorator2 component;
	
	public Decorator2 getComponent() {
		return component;
	}

	public void setComponent(Decorator2 component) {
		this.component = component;
	}

	public void operation() {
		// TODO Auto-generated method stub
		if(concreteComponent != null){
			concreteComponent.operation();
		}
		if(component != null){
			component.operation();
		}
	}

}
