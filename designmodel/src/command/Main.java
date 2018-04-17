package command;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Invoker i = new Invoker();
		Receiver r = new Receiver();
		i.setCommand(new ConcreteCommand(r));
		i.excuteCommand();
		
	}

}
