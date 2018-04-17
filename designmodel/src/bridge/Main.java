package bridge;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Abstraction a = new RefinedAbstraction();
		
		a.setImplementor(new ConcreteImplementorA());
		a.option();
		
		a.setImplementor(new ConcreteImplementorB());
		a.option();
		
	}

}
