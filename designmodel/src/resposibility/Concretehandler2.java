package resposibility;

public class Concretehandler2  extends Handler{

	@Override
	protected void request(int request) {
		// TODO Auto-generated method stub
		if(request >10 && request<30){
			
			System.out.println("this is 2");
		}else{
			
			if(null != handler){
				
				handler.request(request);
			}
		}
	}

}
