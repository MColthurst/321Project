package com.uwindsor.elgg.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
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
    			startActivity(i);
    		}
	};
}