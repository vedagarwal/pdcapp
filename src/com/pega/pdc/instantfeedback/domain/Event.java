package com.pega.pdc.instantfeedback.domain;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Event")
public class Event extends ParseObject{
	
	public String getSession() {
		return getString("Session");
	}
	
	public String getVenue() {
		return getString("Venue");
	}
	
	public Date getFrom() {
		return getDate("From");
	}
	
	public Date getTo() {
		return getDate("To");
	}
	
	
	
	
	

}
