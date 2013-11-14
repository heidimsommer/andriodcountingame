package old;


import java.util.HashMap;

import org.quickconnectfamily.json.JSONOutputStream;

public interface Handler {

	public void handleIt(String request, JSONOutputStream outToClient);

}
