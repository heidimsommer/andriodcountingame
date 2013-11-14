import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;



public class Server {
	private static List<Picture> allPictures;
	private static Executor aExecutor;
	//must be running for the app to connect to it.
	public static void main(String[] args) throws UnknownHostException, IOException {

		try {
			ServerSocket aListeningSocket = new ServerSocket(1111);

			try{ 
				while(true){
					System.out.println("waiting for connection...");
					Socket clientSocket = aListeningSocket.accept();
					aExecutor = Executors.newCachedThreadPool();
					ServerRunnable aServerRunnable = new ServerRunnable(clientSocket); 
			        aExecutor.execute(aServerRunnable);
				}

			} catch(Exception e){ 
				e.printStackTrace(); 
				System.out.println("Error: Unable to communicate with server. "+e.getLocalizedMessage()); 
			}
		}

		catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace(); 
			System.exit(1);
		}

	}

	public static void becomeAServer(){
		try {


			//a socket opened on the specified port
			System.out.println("its started");
			ServerSocket aListeningSocket = new ServerSocket(1111);

			try{ 
				//wait for a connection 
				System.out.println("Waiting for client connection request.");
				Socket clientSocket = aListeningSocket.accept();
				System.out.println("Connection made");
				//setup the JSON streams for later use.
				JSONInputStream inFromClient = new JSONInputStream(clientSocket.getInputStream());
				JSONOutputStream outToClient = new JSONOutputStream(clientSocket.getOutputStream());
				//read until the client closes 
				//the connection. 
				while(true){
					System.out.println("Waiting for a message from the server.");
					String aString = (String)inFromClient.readObject();
					System.out.println("Just got:" +aString+" from client");
					String aResponse = "The connection was made";
					outToClient.writeObject(aResponse);
				}

			} catch(Exception e){ 
				e.printStackTrace(); 
				System.out.println("Error: Unable to communicate with server. "+e.getLocalizedMessage()); 
			}
		}

		catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace(); 
			System.exit(1);
		}

	}

	public static void doStuffWithDataBase() throws UnknownHostException, IOException {
		//start
		System.out.println("started");
		//Socket toServer = new Socket("localhost", 8889);
		//final org.apache.log4j.Logger logger = Logger.getLogger(Server.class);
		System.out.println("logger done");


		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		System.out.println("session start");
		Transaction tx = session.beginTransaction();
		System.out.println("Transaction start");
		Picture pic = new Picture("image5", 5);
		System.out.println("picture created");
		session.save(pic);
		System.out.println("session.save(pic); done");
		tx.commit();
		System.out.println("tx.commit(); done");
		session.close();
		System.out.println("session.close(); done");
		//end	


	}

	public static void getStuffFromDataBase() throws UnknownHostException, IOException {
		//start
		System.out.println("started");
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		System.out.println("session start");
		Transaction tx = session.beginTransaction();
		System.out.println("Transaction start");
		Query allPicturesQuery = session.createQuery("select u from Picture as u order by u.id");
		System.out.println("createQuery");
		allPictures = allPicturesQuery.list();
		System.out.println("num pictures: "+allPictures.size());
		/*
		 * iterate over each User instance returned by the query and found in the list.
		 */
		Iterator<Picture> iter = allPictures.iterator();
		while(iter.hasNext()) {
			Picture element = iter.next();
			System.out.println(element.toString());
		}
		tx.commit();
		//end	


	}

	public static void getInAFancyWay() throws UnknownHostException, IOException {
		//start
		System.out.println("started");
		Session session = HibernateUtilSingleton.getSessionFactory().getCurrentSession();
		System.out.println("session start");
		Transaction tx = session.beginTransaction();
		System.out.println("Transaction start");
		Query allPicturesQuery = session.createQuery("select u from Picture as u order by u.id");
		System.out.println("createQuery");
		allPictures = allPicturesQuery.list();

		picPictures(allPictures);

		tx.commit();
		//end	


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