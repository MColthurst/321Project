package com.uwindsor.elgg.project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends Activity{
	ImageView picture;
	TextView uname;
	TextView name;
	TextView about;
	TextView city;
	TextView email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		uname = (TextView) findViewById(R.id.P_uname);
		uname.setText(getIntent().getCharSequenceExtra("uname") + "'s Profile");
		
		name = (TextView) findViewById(R.id.P_name);
		name.setText("Name: " + getIntent().getCharSequenceExtra("name"));
		
		about = (TextView) findViewById(R.id.P_about);
		about.setText("About Me: " + getIntent().getCharSequenceExtra("about"));
		
		city = (TextView) findViewById(R.id.P_location);
		city.setText("Location: " + getIntent().getCharSequenceExtra("city"));
		
		email = (TextView) findViewById(R.id.P_email);
		email.setText("EMail: " + getIntent().getCharSequenceExtra("email"));
		
	}
}
