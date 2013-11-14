package old;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONOutputStream;

public class NewProblemFruitHandler implements Handler {
	static List<Picture> allPictures;
	
	@Override
	public void handleIt(String request, JSONOutputStream outToClient) {
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
			Query allPicturesQuery = session.createQuery("select u from Picture as u order by u.id");
			allPictures = allPicturesQuery.list();
	        
			ArrayList<Picture> pictureArray = picPictures(allPictures);
			
			try {
				outToClient.writeObject(pictureArray);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        tx.commit();
		
	}

	public static ArrayList<Picture> picPictures(List pictureList) {
		int size = pictureList.size();
		int index1 = (int) (Math.random() * size);
		int index2 = (int) (Math.random() * size);
		
		ArrayList<Picture> pictureArray = new ArrayList<Picture>();
		
		pictureArray.add((Picture) pictureList.get(index1));
		pictureArray.add((Picture) pictureList.get(index2));
		
		System.out.println(pictureArray.get(0).toString());
		System.out.println(pictureArray.get(1).toString());
		
		return pictureArray;	
	}



}
