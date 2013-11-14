package com.example.mathapp;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;

import android.os.Handler;
import android.os.Message;


public class HTTPConnection implements Runnable {
	HashMap map;
	@Override
	public void run() {
		System.out.println("httpcon tread started");
		try{
			URL urlJSON = new URL("http://api.openweathermap.org/data/2.1/find/city?lat=43.823002&lon=-111.785321&cnt=10");
			HttpURLConnection conJSON = (HttpURLConnection)urlJSON.openConnection();
			JSONInputStream inJSON = new JSONInputStream(conJSON.getInputStream());
			try {
				map = (HashMap)inJSON.readObject();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(map.toString());
			ArrayList list = (ArrayList) map.get("list");
			//System.out.println(list.toString());
			HashMap city = (HashMap) list.get(0);
			//System.out.println(city.toString());
			ArrayList weather = (ArrayList) city.get("weather");
			//System.out.println(weather.toString());
			HashMap row = (HashMap) weather.get(0);
			//System.out.println(row.toString());
			String description = (String) row.get("description");
			//System.out.println(description);
			
			Handler hand = MainActivity.hand;
			final Message message = new Message();
			message.what = 2;
			message.obj = description;
			hand.sendMessage(message);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	public static void main(String[] args) throws JSONException {
	//		try{
	//			URL urlJSON = new URL("http://api.openweathermap.org/data/2.1/find/city?lat=43.823002&lon=-111.785321&cnt=10");
	//			HttpURLConnection conJSON = (HttpURLConnection)urlJSON.openConnection();
	//			JSONInputStream inJSON = new JSONInputStream(conJSON.getInputStream());
	//			HashMap map = (HashMap)inJSON.readObject();
	//				System.out.println(map.toString());
	//			ArrayList list = (ArrayList) map.get("list");
	//				System.out.println(list.toString());
	//			HashMap city = (HashMap) list.get(0);
	//				System.out.println(city.toString());
	//			ArrayList weather = (ArrayList) city.get("weather");
	//				System.out.println(weather.toString());
	//				HashMap row = (HashMap) weather.get(0);
	//				System.out.println(row.toString());
	//			String description = (String) row.get("description");
	//				System.out.println(description);
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		finally {
	//			System.out.println("try");
	//		}
	//	}
	//}

}


