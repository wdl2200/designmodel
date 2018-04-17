package mediator;

public class ConcreteColleague2 extends Colleague {

	public ConcreteColleague2(Mediator mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}
	
	public void send(String message){
		
		mediator.send(message, this);
		
	}
	
	public void notify(String message){
		
		System.out.println("2得到信息;"+message);
	}
	
}
