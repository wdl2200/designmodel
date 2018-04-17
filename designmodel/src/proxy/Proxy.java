package proxy;

public class Proxy implements Subject {
	
	private RealSubject subject;

	@Override
	public void request() {
		// TODO Auto-generated method stub
		if(subject == null){
			subject = new RealSubject();
		}
		subject.request();
	}
	
	
	

}
