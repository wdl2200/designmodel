package observer;

public class Main {
	public static void main(String[] args) {
		ConvreteSubject s = new ConvreteSubject();
		s.attack(new ConcreteObserver(s, "X"));
		s.attack(new ConcreteObserver(s, "Y"));
		s.attack(new ConcreteObserver(s, "Z"));
		s.setSubjectState("abc");
		s.Notify();
	}
}
