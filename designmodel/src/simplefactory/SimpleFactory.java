package simplefactory;

public class SimpleFactory {
	
	public static String getSimplefactory(char key){
		switch (key) {
		case '1':
			return "1";
		case '2':
			return "2";
		default:
			return "0";
		}
	}

}
