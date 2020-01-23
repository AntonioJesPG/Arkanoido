package Version_06;

import java.net.URL;
import java.util.HashMap;

public abstract class ResourceCache {

	protected HashMap resources;
	
	public ResourceCache() {
		
		this.resources = new HashMap();
		
	}
	
	//Carga de recursos
	protected Object loadResource(String name) {
		
		URL url = null;
		url = getClass().getResource(name);
		return loadResource(url);
		
	}
	
	protected Object getResource(String name) {
		
		Object res = resources.get(name);
		
		if( res == null) {
			
			res = loadResource("../res/" + name);
			resources.put(name,res);
			
		}
		
		return res;
		
	}
	
	protected abstract Object loadResource(URL url);
	
}
