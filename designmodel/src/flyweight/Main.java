package flyweight;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebSiteFactory f = new WebSiteFactory();
		WebSite  fx = f.getWebSiteCategory("产品展示");
		fx.User(new User("小菜"));
		
		WebSite  fy = f.getWebSiteCategory("产品展示");
		fy.User(new User("大鸟"));
		
		WebSite  fz = f.getWebSiteCategory("产品展示");
		fz.User(new User("桃谷"));
		
		WebSite  fa = f.getWebSiteCategory("博客");
		fa.User(new User("桃谷1"));
		
		WebSite  fb = f.getWebSiteCategory("博客");
		fb.User(new User("桃谷2"));
		
		System.out.println("得到网站数量 "+f.getWebSiteCount());
	}

}
