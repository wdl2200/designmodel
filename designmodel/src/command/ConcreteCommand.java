package command;

public class ConcreteCommand extends Command{
	
	public ConcreteCommand(Receiver receiver) {
		super(receiver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void excute() {
		// TODO Auto-generated method stub
		receiver.action();
	}
	
	
	
	

}
