package abstractFactory;

public class ConcreteFactory2 implements AbstractFactory{

	@Override
	public AbstractProductA getProductA() {
		// TODO Auto-generated method stub
		return new ProductA2();
	}

	@Override
	public AbstractProductB getProductB() {
		// TODO Auto-generated method stub
		return new ProductB2();
	}

}
