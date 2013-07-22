package com.maxwolffe.TangRestart;



import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.TangSHEP.MESSAGE";
	Tab tab;
	Activity act = this;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("First","1");
		//setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		tab = actionBar.newTab().setTabListener(new Tab1());
		tab.setText("NewsFeed");
		actionBar.addTab(tab);
		
		tab = actionBar.newTab().setTabListener(new Tab3());
		tab.setText("Facts");
		actionBar.addTab(tab);
		
		tab = actionBar.newTab().setTabListener(new Tab4());
		tab.setText("Questions");
		actionBar.addTab(tab);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void showAlert(){
		new AlertDialog.Builder(this)
		.setTitle("Not Connected")
		.setMessage("Connect?")
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				Intent intent = new Intent(Settings.ACTION_SETTINGS);
				startActivity(intent);
			}
		})
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				// do nothing
			}
		})
		.show();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		WebView webView = (WebView) findViewById(R.id.webview1);
		if (webView!=null && (keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		else if (isTaskRoot() && (keyCode != KeyEvent.KEYCODE_DEL)) {
			//Ask the user if they want to quit
			new AlertDialog.Builder(this)
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setTitle("Quit?")
			.setMessage("Are you sure you want to quit?")
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finish();    
				}
			})
			.setNegativeButton("No", null)
			.show();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


	public void clickHandler(View view){

		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo!= null && networkInfo.isConnected()){
			Intent connIntent = new Intent(this, Connection.class);
			startActivity(connIntent);
		}
		else{
			showAlert();
		}
	}

	public void clickHandler(){

		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo!= null && networkInfo.isConnected()){
			Intent connIntent = new Intent(this, Connection.class);
			startActivity(connIntent);
		}
		else{
			showAlert();
		}
	}

	
	public void sendEmail(View view){
		
		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle("Ask Away!")
		.setMessage("All Questions sent to Tang SHEP are confidential. We will ask for permission before posting any questions on our Frequently Asked Questions site. ")
		.setPositiveButton("Understood", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"max.alan.wolffe@gmail.com"});
				i.putExtra(Intent.EXTRA_SUBJECT, "Tang SHEP App Question");
				i.putExtra(Intent.EXTRA_TEXT   , "Temporary Message Text");
				try {
					startActivity(Intent.createChooser(i, "Send mail..."));
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(act, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
				} 
			}
		})
		.show();
	}
	
	public void FAQConnect(View view){
		Intent FAQIntent = new Intent(this,Connection.class);
		FAQIntent.putExtra(EXTRA_MESSAGE, "http://sheptalk.wordpress.com/faqs/");
		startActivity(FAQIntent);
	}

}

