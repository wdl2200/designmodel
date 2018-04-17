package observer;

public class ConcreteObserver implements Observer {
	
	private String name;
	
	private String observerstate;
	
	private ConvreteSubject subject;
	
	public ConcreteObserver(ConvreteSubject subject ,String name) {
		this.subject = subject;
		this.name = name;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		observerstate = subject.getSubjectState();
		System.out.println("观察者:"+name+",observerstate="+observerstate);
	}

	public ConvreteSubject getSubject() {
		return subject;
	}

	public void setSubject(ConvreteSubject subject) {
		this.subject = subject;
	}
	
	

}
