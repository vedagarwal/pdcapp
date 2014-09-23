package com.pega.pdc.instantfeedback.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.pega.pdc.instantfeedback.R;
import com.pega.pdc.instantfeedback.domain.Event;

public class EventAdapter extends ParseQueryAdapter<Event> {

	public EventAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<Event>() {
			public ParseQuery<Event> create() {				
				ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
				query.addDescendingOrder("createdAt");
				return query;
			}
		});
	}

	@Override
	public View getItemView(Event event, View v, ViewGroup parent) {

		if (v == null) {
			v = View.inflate(getContext(),R.layout.event_item, null);
		}

		super.getItemView(event, v, parent);		

		TextView sessionName = (TextView) v.findViewById(R.id.sessionName);
		sessionName.setText(event.getSession());		
		return v;
	}
	
	@Override
	public View getNextPageView(View v, ViewGroup parent) {
	  if (v == null) {
	    // R.layout.adapter_next_page contains an ImageView with a custom graphic
	    // and a TextView.
	    v = View.inflate(getContext(), R.layout.event_item, null);
	  }	 
	  return v;
	}
}