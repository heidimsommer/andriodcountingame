import java.util.HashMap;




public class ApplicationController {
	private MathHashMap map;

	public ApplicationController() {
	}
	
	public void handleRequest(HashMap<String, Object> comunicationHashMap) {
		String request = (String) comunicationHashMap.get("request");
		map = new MathHashMap();
		Handler aHandler = (Handler) map.get(request);
		aHandler.handleIt(comunicationHashMap);
	}
	
	
}
