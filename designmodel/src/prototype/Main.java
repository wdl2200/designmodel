package prototype;

public class Main {
	
	
	public static void main(String[] args) {
		Prototype t = new ConcretePrototype("123");
		Prototype a  = t.clone();
		
		System.out.println(t.getId());
		System.out.println(a.getId());
	}
}
