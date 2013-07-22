package com.maxwolffe.TangRestart;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Tab4 extends Fragment implements ActionBar.TabListener{

	private Fragment mFragment;


	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getActivity().setContentView(R.layout.activity_questions);


	}

	
	public void onTabSelected(Tab tab, FragmentTransaction ft) {

		mFragment = new Tab4();
		ft.add(android.R.id.content, mFragment);
		ft.attach(mFragment);


	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// Remove fragment1.xml layout
		ft.remove(mFragment);	
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_questions, container, false);
		
		return v;
	}
	
	

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}
