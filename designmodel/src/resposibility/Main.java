package resposibility;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Handler h1 = new ConcreteHandler1();
		Handler h2 = new Concretehandler2();
		h1.setHandler(h2);
		
		h1.request(2);
	}

}
