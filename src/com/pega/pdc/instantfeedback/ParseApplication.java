package com.pega.pdc.instantfeedback;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.pega.pdc.instantfeedback.domain.Event;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		 // Add your initialization code here
		 
		
		 ParseObject.registerSubclass(Event.class);		

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
 
        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);
 
        ParseACL.setDefaultACL(defaultACL, true);
	}
	
	

}
