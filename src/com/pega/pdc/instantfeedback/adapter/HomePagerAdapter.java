package com.pega.pdc.instantfeedback.adapter;

import java.util.Locale;

import com.pega.pdc.instantfeedback.EventListFragment;
import com.pega.pdc.instantfeedback.R;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class HomePagerAdapter extends FragmentStatePagerAdapter {

	private Context mContext;

	public HomePagerAdapter(FragmentManager fm,Context context) {
		super(fm);
		mContext = context;
	}

	@Override
	public Fragment getItem(int position) {
		//return HomeListFragment.newInstance(position);	
		return new EventListFragment();
	}

	@Override
	public int getCount() {		
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return mContext.getString(R.string.tab_agenda).toUpperCase(l);
		case 1:
			return mContext.getString(R.string.tab_feedback).toUpperCase(l); 		
		}
		return null;
	}

}
