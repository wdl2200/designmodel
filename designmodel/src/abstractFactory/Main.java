package abstractFactory;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractFactory factory = new ConcreteFactory1();
		AbstractProductA a = factory.getProductA();
		AbstractProductB b = factory.getProductB();
		
		AbstractFactory factory2 = new ConcreteFactory2();
				
	}

}
