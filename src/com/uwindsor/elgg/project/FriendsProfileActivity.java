package com.uwindsor.elgg.project;

import com.uwindsor.elgg.project.adapters.ImageLoader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendsProfileActivity extends Activity{
	Activity a = new Activity();
	ImageView picture;
	TextView uname;
	TextView name;
	TextView about;
	TextView city;
	TextView email;
	ImageButton remove;
	ImageButton wire;
	
	public ImageLoader imageLoader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friendsprofile);
		
		a=this;
		
		uname = (TextView) findViewById(R.id.FP_uname);
		uname.setText(getIntent().getCharSequenceExtra("uname") + "'s Profile");
		
		name = (TextView) findViewById(R.id.FP_name);
		name.setText("Name: " + getIntent().getCharSequenceExtra("name"));
		
		about = (TextView) findViewById(R.id.FP_about);
		about.setText("About Me: " + getIntent().getCharSequenceExtra("about"));
		
		city = (TextView) findViewById(R.id.FP_location);
		city.setText("Location: " + getIntent().getCharSequenceExtra("city"));
		
		email = (TextView) findViewById(R.id.FP_email);
		email.setText("EMail: " + getIntent().getCharSequenceExtra("email"));
		
		picture = (ImageView) findViewById(R.id.FP_picture);
		imageLoader = new ImageLoader(this.getApplicationContext());
		imageLoader.DisplayImage(getIntent().getStringExtra("avatarURL"), a, picture);
		
	}
}
