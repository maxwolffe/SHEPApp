package com.maxwolffe.TangRestart;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MyListFragment extends ListFragment {

	  @Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    String[] values = new String[] { "Resources", "Masturbation", "For Him",
	        "For Her", "Safe and Sexy Sex", "STDs", "Positions", "Condoms",
	        "Contraceptives", "Not 'Doing It'" };
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	  }

	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
		  int intId = (int) id;
		  Intent FactIntent = new Intent(this.getActivity(),Connection.class);
		  switch(intId){
		  	case 0:
		  		FactIntent.putExtra(MainActivity.EXTRA_MESSAGE, "file:///android_asset/test.html");
		  	default:
		  		FactIntent.putExtra(MainActivity.EXTRA_MESSAGE, "file:///android_asset/test.html");
		  	startActivity(FactIntent);
		  }

	  }
	} 
