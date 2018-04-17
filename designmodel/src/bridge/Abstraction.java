package bridge;

public class Abstraction {
	
	public Implementor imp;
	
	public void setImplementor(Implementor imp){
		
		this.imp = imp;
	}
	
	public void option(){
		
		imp.OperationImp();
	}

}
