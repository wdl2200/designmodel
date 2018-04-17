package adapter;

public class Adaptor extends Target {
	
	private Adptee adptee;
	
	@Override
	public void request() {
		// TODO Auto-generated method stub
		if(null == adptee){
			adptee = new Adptee();
		}
		adptee.specificRequest();
	}
}
