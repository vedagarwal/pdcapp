package com.pega.pdc.instantfeedback;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseQueryAdapter.OnQueryLoadListener;
import com.pega.pdc.instantfeedback.R;
import com.pega.pdc.instantfeedback.adapter.EventAdapter;
import com.pega.pdc.instantfeedback.domain.Event;

public class EventListFragment extends Fragment implements OnItemClickListener{
	

	private Callbacks mCallbacks = sDummyCallbacks;
	private ProgressDialog mProgressDialog;
	private EventAdapter adapter;
	private ListView mListView;
	private static final String STATE_ACTIVATED_POSITION = "activated_position";


	private int mActivatedPosition = ListView.INVALID_POSITION;


	public interface Callbacks {

		public void onItemSelected(Event event);
	}


	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(Event event) {
		}
	};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public EventListFragment() {
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_list,container, false);

		mListView = (ListView)rootView.findViewById(R.id.eventListView);
		
		mListView.setOnItemClickListener(this);

		if(isNetworkAvailable()){
			adapter = new EventAdapter(getActivity());
			adapter.setPaginationEnabled(true);
			adapter.setObjectsPerPage(10);
			adapter.addOnQueryLoadListener(new OnQueryLoadListener<Event>() {

				@Override
				public void onLoaded(List<Event> arg0, Exception arg1) {
					mProgressDialog.dismiss();
					
				}

				@Override
				public void onLoading() {					
					mProgressDialog = new ProgressDialog(getActivity());
					mProgressDialog.setMessage("Loading...");
					mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);       
					mProgressDialog.show();
					
				}
			});
			mListView.setAdapter(adapter);
		}else{
			Toast.makeText(getActivity(), "Network is unavailable !!", Toast.LENGTH_LONG).show();
		}

		return rootView;
	}





	private boolean isNetworkAvailable() {
		ConnectivityManager connMgr = (ConnectivityManager) getActivity()
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();	
		boolean isAvailable = false;

		if(networkInfo != null && networkInfo.isConnected()){
			isAvailable = true;
		}
		return isAvailable;
	}



	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException("Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onItemClick(AdapterView<?> gridView, View view, int position, long id) {
		mCallbacks.onItemSelected(adapter.getItem(position));		
	}



	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {			
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}



	public void setActivateOnItemClick(boolean activateOnItemClick) {		
		mListView.setChoiceMode(activateOnItemClick
				? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			mListView.setItemChecked(mActivatedPosition, false);
		} else {
			mListView.setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}
	protected void startLoading() {
		mProgressDialog = new ProgressDialog(getActivity());
		mProgressDialog.setMessage("Loading...");
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
	}

	protected void stopLoading() {
		mProgressDialog.dismiss();		
	}
}