package resposibility;

public abstract class Handler {
	
	protected Handler handler;

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	protected abstract void request(int request);
	
	

}
