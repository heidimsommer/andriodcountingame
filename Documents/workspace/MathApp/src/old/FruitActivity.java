//package old;
//
//import java.util.ArrayList;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//
//public class FruitActivity extends Activity {
//	private Button optionButton1;
//	private Button optionButton2;
//	private Button optionButton3;
//	private Button optionButton4;
//	private ImageButton homeButton;
//	private ImageView pictureView1;
//	private ImageView pictureView2;
//	private static ArrayList<Button> allButtons;
//	private static Executor aExecutor;
//	static Handler hand;
//	private static FruitProblem problem;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_fruit);
//
//		//Intent intent = getIntent();
//
//		optionButton1 = (Button) findViewById(R.id.optionButton1);
//		optionButton2 = (Button) findViewById(R.id.optionButton2);
//		optionButton3 = (Button) findViewById(R.id.optionButton3);
//		optionButton4 = (Button) findViewById(R.id.optionButton4);
//		homeButton = (ImageButton) findViewById(R.id.home);
//		pictureView1 = (ImageView) findViewById(R.id.picture1);
//		pictureView2 = (ImageView) findViewById(R.id.picture2);
//
//		allButtons = new ArrayList<Button>();
//
//		allButtons.add(optionButton1);
//		allButtons.add(optionButton2);
//		allButtons.add(optionButton3);
//		allButtons.add(optionButton4);
//
//		OnClickListener optionListener = new OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				int intID = view.getId();
//			      Button button = (Button) findViewById(intID);
//			      String value = button.getText().toString();
//			}
//		};
//		
//		for (int i = 0; i < allButtons.size(); i++){
//			Button tempButton = allButtons.get(i);
//			tempButton.setOnClickListener(optionListener);
//		}
//		
//		newProblem();
//
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.fruit, menu);
//		return true;
//	}
//	
//	public static void newProblem() {
//		aExecutor = Executors.newCachedThreadPool();
//		FruitRunnable aRunnable = new FruitRunnable(); 
//		aExecutor.execute(aRunnable);
//		
//		hand = new Handler(){
//			@Override
//			public void handleMessage(Message message) {
//				if (message.what == 1) {
//					ArrayList<Picture> chosenPictures = (ArrayList<Picture>) message.obj;
//					Picture picture1 = chosenPictures.get(0);
//					Picture picture2 = chosenPictures.get(1);
//					problem = new FruitProblem();
//					problem.setPictures(picture1, picture2);
//					
////					Resources res = getResources();
////					int resourceId = res.getIdentifier(
////					   generatedString, "drawable", getPackageName() );
////					imageView.setImageResource( resourceId );
//					
//					
//					//int id = getResources().getIdentifier("yourpackagename:drawable/" + picture1.getSrc(), null, null);
//					//pictureView1.setImageDrawable(picture1.getSrc());
//					
//					problem.setAllButtonCombos(allButtons);
//				}
//			}
//		}; //end of handler
//	}
//
//}
