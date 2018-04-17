package mediator;

public class ConcreteColleague1 extends Colleague {

	public ConcreteColleague1(Mediator mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}
	
	public void send(String message){
		
		mediator.send(message, this);
		
	}
	
	public void notify(String message){
		
		System.out.println("1得到信息;"+message);
	}
	
}
