package resposibility;


public class ConcreteHandler1 extends Handler {

	@Override
	protected void request(int request) {
		// TODO Auto-generated method stub
		if(request >1 && request <10){
			System.out.println("this is 1");	
		}else{
			if(handler != null){
				handler.request(request);
			}
		}
	}

}
