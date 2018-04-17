package decorator;

public class Decorator extends Component {
	
	private Component component;
	
	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	@Override
	public void operation() {
		// TODO Auto-generated method stub
		if(component != null){
			component.operation();
		}
	}

}
