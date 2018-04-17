package methodfacory;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IFactory factory = new ConcreteFactory();
		Product product = factory.getProduct();
		product.doSomething();
	}

}
