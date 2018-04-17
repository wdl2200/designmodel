package decorator;

public class ConcreteDecorator3 extends Decorator2 {
	
	@Override
	public void operation() {
		// TODO Auto-generated method stub
		getComponent().operation();
		System.out.println("ConcreteDecorator3");
	}

}
