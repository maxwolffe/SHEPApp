package com.maxwolffe.TangRestart;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.webkit.WebView;




public class Tab1 extends Fragment implements ActionBar.TabListener{

	private Fragment mFragment;
	private WebView webView;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setContentView(R.layout.activity_connection);
		webView = (WebView) getActivity().findViewById(R.id.webview1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new HelloWebViewClient());
		webView.loadUrl("http://m.facebook.com/SHEPsexperts?id" +
				"=414856300460&refsrc=http%3A%2F%2Fwww.facebook.com" +
				"%2FSHEPsexperts&_rdr");
		clickHandler();
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mFragment = new Tab1();
		ft.add(android.R.id.content, mFragment);
		ft.attach(mFragment);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.remove(mFragment);	
	}

	

	
	public void showAlert(){
		new AlertDialog.Builder(getActivity())
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


	public void clickHandler(){

		ConnectivityManager connMgr = (ConnectivityManager)
				(getActivity().getSystemService(Context.CONNECTIVITY_SERVICE));
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo!= null && networkInfo.isConnected()){
			//DO NOTHING
		}
		else{
			showAlert();
		}
	}
	

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}
}