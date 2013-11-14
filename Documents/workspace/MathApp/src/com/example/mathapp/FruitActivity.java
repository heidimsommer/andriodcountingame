package com.example.mathapp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FruitActivity extends Activity {
    private Button optionButton1;
    private Button optionButton2;
    private Button optionButton3;
    private Button optionButton4;
    private ImageButton homeButton;
    private ImageView pictureView1;
    private ImageView pictureView2;
    private static ArrayList<Button> allButtons;
    private static Executor aExecutor;
    static Handler hand;
    private static FruitProblem problem;
    private MediaPlayer correctSoundEffect;
    private MediaPlayer wrongSoundEffect;
    private ImageView iv2;
    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        correctSoundEffect = MediaPlayer.create(this, R.raw.cheer);
        wrongSoundEffect = MediaPlayer.create(this, R.raw.wrong);

        optionButton1 = (Button) findViewById(R.id.optionButton1);
        optionButton2 = (Button) findViewById(R.id.optionButton2);
        optionButton3 = (Button) findViewById(R.id.optionButton3);
        optionButton4 = (Button) findViewById(R.id.optionButton4);
        homeButton = (ImageButton) findViewById(R.id.home);
        homeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startHome(view);
            }

        });
        //pictureView1 = (ImageView) findViewById(R.id.picture1);
        //pictureView2 = (ImageView) findViewById(R.id.picture2);

        allButtons = new ArrayList<Button>();

        allButtons.add(optionButton1);
        allButtons.add(optionButton2);
        allButtons.add(optionButton3);
        allButtons.add(optionButton4);

        OnClickListener optionListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                int intID = view.getId();
                  Button button = (Button) findViewById(intID);
                  String value = button.getText().toString();
                  checkValue(value);
            }
        };
        
        
        for (int i = 0; i < allButtons.size(); i++){
            Button tempButton = allButtons.get(i);
            tempButton.setOnClickListener(optionListener);
        }
        
        newProblem();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fruit, menu);
        return true;
    }
    public boolean onCreateOptionsMenu1(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void newProblem() {
        aExecutor = Executors.newCachedThreadPool();
        FruitRunnable aRunnable = new FruitRunnable(); 
        aExecutor.execute(aRunnable);
        
        hand = new Handler(){
            @Override
            public void handleMessage(Message message) {
                if (message.what == 1) {
                	System.out.println("msg: "+message.obj);
                	HashMap<String,HashMap> chosenPictures = (HashMap<String,HashMap>) message.obj;
                	HashMap<String,String> picture1HashMap = (HashMap<String,String>)chosenPictures.get("img1");
					HashMap<String,String> picture2HashMap = (HashMap<String,String>)chosenPictures.get("img2");
					
					System.out.println(picture1HashMap);
					System.out.println(picture2HashMap);
					
					String src1 = String.valueOf(picture1HashMap.get("src"));
					Integer number1 = Integer.parseInt(String.valueOf(picture1HashMap.get("number")));
					
					Picture picture1 = new Picture(src1, number1);
					Picture picture2 = new Picture(String.valueOf(picture2HashMap.get("src")), Integer.parseInt(String.valueOf(picture2HashMap.get("number"))));
					problem = new FruitProblem();
					problem.setPictures(picture1, picture2);
					
					FruitActivity.this.displayPictures(picture1.getSrc(), picture2.getSrc());
					
					
					HashMap<Integer, Integer> numbers = new HashMap<Integer, Integer>();
					numbers.put( 1, R.string.number1);
					numbers.put( 2, R.string.number2);
					numbers.put( 3, R.string.number3);
					numbers.put( 4, R.string.number4);
					numbers.put( 5, R.string.number5);
					numbers.put( 6, R.string.number6);
					numbers.put( 7, R.string.number7);
					numbers.put( 8, R.string.number8);
					numbers.put( 9, R.string.number9);
					numbers.put( 10, R.string.number10);
					
					
					
					int[] allOptions = problem.getAllOptions();
					for(int i = 0; i < allOptions.length; i++){
						Button button = allButtons.get(i);
						button.setText(numbers.get(allOptions[i]));
					}
                    
                    
                }
            }
        }; //end of handler
    }
    
    public void checkValue(String value){
        int answer = problem.getAnswer();
        int intValue = Integer.parseInt(value);
        if (answer == intValue){
            correctSoundEffect.start();
            newProblem();
        }
        else {
            wrongSoundEffect.start();
        }
    }
    
    public void startHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    
    public void displayPictures(String src1, String src2){
    	
    	HashMap<String, Integer> images = new HashMap<String, Integer>();
        images.put( "apple1d", R.drawable.apple1);
        images.put( "apple2d", R.drawable.apple2);
        images.put( "apple3d", R.drawable.apple3);
        images.put( "apple4d", R.drawable.apple4);
        images.put( "apple5d", R.drawable.apple5);
    	
	
    	iv2 = new ImageView(this);
    	iv1 = new ImageView(this);
    	
    	
	      //iv2.setImageResource(R.drawable.apple5d);
    	iv2.setImageResource(images.get(src1));
	      RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
		          RelativeLayout.LayoutParams.WRAP_CONTENT,
		          RelativeLayout.LayoutParams.WRAP_CONTENT);
	      lp2.setMargins(140, 220, 0, 0);
		      //lp2.addRule(RelativeLayout.ABOVE, iv1.getId());
	       
		      //iv1.setImageResource(R.drawable.apple5d);
	      iv1.setImageResource(images.get(src2));
	      RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
	          RelativeLayout.LayoutParams.WRAP_CONTENT,
	          RelativeLayout.LayoutParams.WRAP_CONTENT);
	      lp1.setMargins(145, 0, 0, 0);
	      //lp1.addRule(RelativeLayout.ABOVE, R.id.plusSign);
	      //lp1.addRule(RelativeLayout.CENTER_HORIZONTAL);
	      //lp1.addRule(RelativeLayout.CENTER_HORIZONTAL);
	      
	      
	      RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayoutFruit);
	      rl.addView(iv1, lp1);
	      rl.addView(iv2, lp2);
	}

	      



}	