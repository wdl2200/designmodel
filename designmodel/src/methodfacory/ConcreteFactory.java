package methodfacory;

public class ConcreteFactory implements IFactory {
	;

	@Override
	public Product getProduct() {
		// TODO Auto-generated method stub
		return new ConcreteProduct();
	}

}
