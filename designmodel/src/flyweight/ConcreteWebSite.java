package flyweight;

public class ConcreteWebSite extends WebSite {
	
	private String name = "";
	
	public ConcreteWebSite(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	@Override
	public void User(flyweight.User user) {
		// TODO Auto-generated method stub
		System.out.println("网站分类"+name+"用户"+user.getName());
	}

}
