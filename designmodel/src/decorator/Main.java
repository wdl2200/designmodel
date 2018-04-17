package decorator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Component component = new ConcreteComponent();
		ConcreteDecorator decorator = new ConcreteDecorator();
		decorator.setComponent(component);
		decorator.operation();
		
		ConcreteComponent2 component2 = new ConcreteComponent2();
		ConcreteDecorator2 decorator2 = new ConcreteDecorator2();
		ConcreteDecorator3 decorator3 = new ConcreteDecorator3();
		decorator2.setConcreteComponent(component2);
//		decorator2.operation();
		decorator3.setComponent(decorator2);
		decorator3.operation();
		
	}

}
