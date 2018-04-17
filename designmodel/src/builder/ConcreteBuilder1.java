package builder;

public class ConcreteBuilder1 implements Builder {
	
	private Product pro = new Product();
	
	@Override
	public void BuildPartA() {
		// TODO Auto-generated method stub
		pro.add("A1");
	}

	@Override
	public void buildPartB() {
		// TODO Auto-generated method stub
		pro.add("B1");
	}

	@Override
	public Product getResult() {
		// TODO Auto-generated method stub
		return pro;
	}

}
