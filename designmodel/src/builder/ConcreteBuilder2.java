package builder;

public class ConcreteBuilder2 implements Builder {
	
	private Product pro = new Product();
	
	@Override
	public void BuildPartA() {
		// TODO Auto-generated method stub
		pro.add("A2");
	}

	@Override
	public void buildPartB() {
		// TODO Auto-generated method stub
		pro.add("B2");
	}

	@Override
	public Product getResult() {
		// TODO Auto-generated method stub
		return pro;
	}

}
