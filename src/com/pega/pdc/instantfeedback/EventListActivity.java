package com.pega.pdc.instantfeedback;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.pega.pdc.instantfeedback.adapter.HomePagerAdapter;
import com.pega.pdc.instantfeedback.domain.Event;


public class EventListActivity extends ActionBarActivity implements ActionBar.TabListener,EventListFragment.Callbacks {

	private boolean mTwoPane;
	private ViewPager mViewPager;
	private HomePagerAdapter mSectionsPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);

		/*SpannableString s = new SpannableString("POOL & SNOOKER RULES");
		s.setSpan(new TypefaceSpan(this, "Gotham.otf"), 0, s.length(),
		        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/

		// Update the action bar title with the TypefaceSpan instance
		final ActionBar actionBar = getSupportActionBar();

		//actionBar.setTitle(s);

		if (findViewById(R.id.event_detail_container) != null) {			
			mTwoPane = true;

			/*((EventListFragment) getSupportFragmentManager().findFragmentById(
					R.id.event_list)).setActivateOnItemClick(true);*/
		}


		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new HomePagerAdapter(getSupportFragmentManager(),this);

		// Set up the ViewPager with the sections adapter. 
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {	            

			actionBar.addTab(
					actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}


	}


	/*@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {

			Bundle arguments = new Bundle();
			arguments.putString(EventDetailFragment.ARG_ITEM_ID, id);
			EventDetailFragment fragment = new EventDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.event_detail_container, fragment).commit();

		} else {

			Intent detailIntent = new Intent(this, EventDetailActivity.class);
			detailIntent.putExtra(EventDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}*/



	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		 mViewPager.setCurrentItem(tab.getPosition());
		
	}


	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onItemSelected(Event event) {
		Log.v("Test",event.getVenue());
		
	}
}
