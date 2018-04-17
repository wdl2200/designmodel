package mediator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConcreteMediator m = new ConcreteMediator();
		ConcreteColleague1 c1 = new ConcreteColleague1(m);
		ConcreteColleague2 c2 = new ConcreteColleague2(m);
		m.setColleague1(c1);
		m.setColleague2(c2);
		
		c1.send("在哪里呢");
		c2.send("不知道啊");
	}

}
