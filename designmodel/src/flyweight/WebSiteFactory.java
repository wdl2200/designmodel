package flyweight;

import java.util.HashMap;
import java.util.Map;

public class WebSiteFactory {
	
	private Map<String,WebSite> maps = new HashMap<String,WebSite>();
	
	public WebSite getWebSiteCategory(String key){
		if(!maps.containsKey(key)){
			maps.put(key, new ConcreteWebSite(key));
		}
		return maps.get(key);
	}
	
	public int getWebSiteCount(){
		
		return maps.size();
		
	}

}
