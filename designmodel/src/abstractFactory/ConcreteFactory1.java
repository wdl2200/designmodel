package abstractFactory;

public class ConcreteFactory1 implements AbstractFactory {

	@Override
	public AbstractProductA getProductA() {
		// TODO Auto-generated method stub
		return new ProductA1();
	}

	@Override
	public AbstractProductB getProductB() {
		// TODO Auto-generated method stub
		return new ProductB1();
	}

}
