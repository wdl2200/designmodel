package visitor;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjectStructure o = new ObjectStructure();
		Element e1 = new ConcreteElementA();
		Element e2 = new ConcreteElementB();
		o.attach(e1);
		o.attach(e2);
		
		Visitor v1 = new ConcreteVisitor1();
		Visitor v2 = new ConcreteVisitor1();
		
		o.notify(v1);
		o.notify(v2);
		
	}

}
