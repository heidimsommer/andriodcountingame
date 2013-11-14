package com.example.mathapp;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends Activity {
	private ImageButton fruitButton;
	private ImageButton caterpillarButton;
	private ImageButton countButton;
	private Executor aExecutor;
	static Handler hand;
	private TextView tv;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		doWeather();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fruitButton = (ImageButton) findViewById(R.id.imageButton1);
		fruitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				startFruit(view);
			}

		});

		caterpillarButton = (ImageButton) findViewById(R.id.imageButton2);
		caterpillarButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
			}

		});

		countButton = (ImageButton) findViewById(R.id.imageButton3);
		countButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void startFruit(View view){
		Intent intent = new Intent(this, FruitActivity.class);
		startActivity(intent);
	}

	public void doWeather() {
		aExecutor = Executors.newCachedThreadPool();
		HTTPConnection aRunnable = new HTTPConnection(); 
		aExecutor.execute(aRunnable);

		hand = new Handler(){
			@Override
			public void handleMessage(Message message) {
				if (message.what == 2) {
					CharSequence weatherDescription = "Weather in Rexburg: " + (CharSequence) message.obj;
					tv = (TextView) findViewById(R.id.weather);
					tv.setText(weatherDescription);
				}
			}
		};
	}

}
