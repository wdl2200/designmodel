package composite;

public class Leaf extends Component {

	@Override
	public void add(Component component) {
		// TODO Auto-generated method stub
		System.out.println("叶子节点不能进行该操作！");
	}

	@Override
	public void remove(Component component) {
		// TODO Auto-generated method stub
		System.out.println("叶子节点不能进行该操作！");
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println(this.getName());
	}

}
