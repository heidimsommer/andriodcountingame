package old;
import org.quickconnectfamily.json.JSONOutputStream;




public class ApplicationController {
	private MathHashMap map;

	public ApplicationController() {
	}
	
	public void handleRequest(String request, JSONOutputStream outToClient) {
		map = new MathHashMap();
		Handler aHandler = (Handler) map.get(request);
		aHandler.handleIt(request, outToClient);
	}
	
	
}
