package strategy;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Context context;
		
		context = new Context(new ConcreteStrategyA());
		context.contextInterface();
		
		context = new Context(new ConcreteStrategyB());
		context.contextInterface();
		
	}

}
