package visitor;

public class ConcreteVisitor2 implements Visitor {

	@Override
	public void visitConcreteElementA(ConcreteElementA a) {
		// TODO Auto-generated method stub
		System.out.println("ConcreteVisitor2 visitConcreteElementA");
	}

	@Override
	public void visitConcreteElementB(ConcreteElementB b) {
		// TODO Auto-generated method stub
		System.out.println("ConcreteVisitor2 visitConcreteElementB");

	}

}
