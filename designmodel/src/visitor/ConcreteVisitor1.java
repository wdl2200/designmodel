package visitor;

public class ConcreteVisitor1 implements Visitor {

	@Override
	public void visitConcreteElementA(ConcreteElementA a) {
		// TODO Auto-generated method stub
		System.out.println("ConcreteVisitor1 visitConcreteElementA");
	}

	@Override
	public void visitConcreteElementB(ConcreteElementB b) {
		// TODO Auto-generated method stub
		System.out.println("ConcreteVisitor1 visitConcreteElementB");

	}

}
