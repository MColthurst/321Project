package com.uwindsor.elgg.project;

import com.uwindsor.elgg.project.utils.WireUtils;
import com.uwindsor.elgg.project.utils.profileUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	final int LOGIN_CODE = 1;
	
	ImageButton login;
	ImageButton profile;
	ImageButton wire;
	ImageButton friends;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        
        login = (ImageButton) findViewById(R.id.D_loginbtn);
        login.setOnClickListener(lgnListener);
        
        profile = (ImageButton) findViewById(R.id.D_profilebtn);
        profile.setOnClickListener(prflListener);
        
        wire = (ImageButton) findViewById(R.id.D_wirebtn);
        wire.setOnClickListener(wireListener);
    }
    
    
	private OnClickListener lgnListener = new OnClickListener() {
    	public void onClick(View v) {
    			Intent i = new Intent(getApplicationContext(), LoginActivity.class);
    			startActivityForResult(i, LOGIN_CODE);
    		}
	};
	
	private OnClickListener prflListener = new OnClickListener() {
    	public void onClick(View v) {
    			if(getIntent().hasExtra("auth_token"))
    				profileUtils.getProfile(getApplicationContext(), getIntent().getStringExtra("auth_token"));
    			else
    				Toast.makeText(getApplicationContext(), "Not Logged In", Toast.LENGTH_LONG);
    		}
	};
	
	private OnClickListener wireListener = new OnClickListener() {
    	public void onClick(View v) {
    			if(getIntent().hasExtra("uname"))
    				WireUtils.getPosts(getApplicationContext(), getIntent().getStringExtra("uname"));
    			else
    				Toast.makeText(getApplicationContext(), "Not Logged In", Toast.LENGTH_LONG);
    		}
	};
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == LOGIN_CODE && resultCode == RESULT_OK)
			this.getIntent().putExtra("uname", data.getStringExtra("uname"));
			this.getIntent().putExtra("pwd", data.getStringExtra("pwd"));
			this.getIntent().putExtra("auth_token", data.getStringExtra("auth_token"));
	};
}