package composite;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Component c1 = new Composite();
			c1.setName("研发部门");
			Leaf l = new Leaf();
			l.setName("研发1部");
			c1.add(l);
			Component c2 = new Component();
			c2.setName("行政部门");
			Leaf l1 = new Leaf();
			l1.setName("行政1部");
			c2.add(l1);
			c1.add(c2);
			c1.display();
			
	}

}
