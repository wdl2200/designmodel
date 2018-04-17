package ModelMethod;

public abstract class AbstractClass {
	
	public abstract void step1();
	
	public abstract void step2();
	
	public void finalStep(){
		step1();
		step2();
	}

}
