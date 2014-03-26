package com.maxwolffe.TangRestart;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class Connection extends Activity {

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connection);

		getActionBar().setDisplayUseLogoEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);
		
		Intent intent = getIntent();
		String URL = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

		webView = (WebView) findViewById(R.id.webview1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new HelloWebViewClient());
		
		if (URL!=""){
			webView.loadUrl(URL);
		}
		else{
			webView.loadUrl("http://m.facebook.com/SHEPsexperts?id=414856300460&refsrc=http%3A%2F%2Fwww.facebook.com%2FSHEPsexperts&_rdr");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Check if the key event was the Back button and if there's history
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
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

	public void showAlert(){
		new AlertDialog.Builder(this)
		.setTitle("Not Connected")
		.setMessage("Connect")
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
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

}


