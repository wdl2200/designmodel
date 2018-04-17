package facade;

public class Facade {
	
	private SubSystemOne one;
	
	private SubSystemTwo two;
	
	
	public Facade() {
		// TODO Auto-generated constructor stub
		one  =  new SubSystemOne();
		two = new SubSystemTwo();
	}
	
	public void methodA(){
		System.out.println("----this is methodA");
		one.methodOne();
		two.methodTwo();
	}
	
	public void methodB(){
		System.out.println("----this is methodB");
		two.methodTwo();
		one.methodOne();
	}

}
