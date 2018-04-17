package prototype;

public class ConcretePrototype extends Prototype {

	public ConcretePrototype(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Prototype clone() {
		// TODO Auto-generated method stub
		ConcretePrototype t = new ConcretePrototype(this.getId());
		
		return t;
	}

}
