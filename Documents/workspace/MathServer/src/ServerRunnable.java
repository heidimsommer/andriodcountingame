import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;


public class ServerRunnable implements Runnable{
	Socket clientSocket;
	ApplicationController controller;

	public ServerRunnable(Socket clientSocket) {
		this.clientSocket = clientSocket;
		controller = new ApplicationController();
	}

	@Override
	public void run(){
		try {
			JSONInputStream inFromClient = new JSONInputStream(clientSocket.getInputStream());
			JSONOutputStream outToClient = new JSONOutputStream(clientSocket.getOutputStream()); 

			HashMap<String, Object> comunicationHashMap = (HashMap<String, Object>)inFromClient.readObject();
			comunicationHashMap.put("JSONOutputStream", outToClient);
			comunicationHashMap.put("JSONInputStream", inFromClient);
			ApplicationController controller = new ApplicationController();
			controller.handleRequest(comunicationHashMap);


		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
