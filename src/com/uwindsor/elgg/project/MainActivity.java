package com.uwindsor.elgg.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	final int LOGIN_CODE = 1;
	
	Button login;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        
        login = (Button) findViewById(R.id.D_loginbtn);
        login.setOnClickListener(btnListener);
    }
    
	private OnClickListener btnListener = new OnClickListener() {
    	public void onClick(View v) {
    			Intent i = new Intent(getApplicationContext(), LoginActivity.class);
    			startActivityForResult(i, LOGIN_CODE);
    		}
	};
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == LOGIN_CODE && resultCode == RESULT_OK)
			this.getIntent().putExtra("auth_token", data.getStringExtra("auth_token"));
		recreate();
	};
}