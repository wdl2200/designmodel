package command;

public class Invoker {
	public Command command;

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
	
	
	public void excuteCommand(){
		command.excute();
	}
	
}
