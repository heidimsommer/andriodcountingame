


import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;
import org.quickconnectfamily.json.JSONUtilities;

public class NewProblemFruitHandler implements Handler {
	static List<Picture> allPictures;
	
	@Override
	public void handleIt(HashMap<String, Object> comunicationHashMap) {
		JSONOutputStream outToClient = (JSONOutputStream) comunicationHashMap.get("JSONOutputStream");
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
			Query allPicturesQuery = session.createQuery("select u from Picture as u order by u.id");
			allPictures = allPicturesQuery.list();
	        
			HashMap<String,Picture> pictureArray = picPictures(allPictures);
			try {
				System.out.println(JSONUtilities.stringify(pictureArray));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				outToClient.writeObject(pictureArray);
				JSONInputStream inFromClient = (JSONInputStream) comunicationHashMap.get("JSONInputStream");
				String reply = (String) inFromClient.readObject();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        tx.commit();
	}

	public static HashMap<String,Picture> picPictures(List pictureList) {
		int size = pictureList.size();
		int index1 = (int) (Math.random() * size);
		int index2 = (int) (Math.random() * size);
		
		HashMap<String,Picture> pictureArray = new HashMap<String,Picture>();
		
		pictureArray.put("img1",(Picture) pictureList.get(index1));
		pictureArray.put("img2", (Picture) pictureList.get(index2));
		
		//System.out.println(pictureArray.get(0).toString());
		//System.out.println(pictureArray.get(1).toString());
		
		return pictureArray;	
	}



}
