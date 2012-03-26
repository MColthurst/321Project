package com.uwindsor.elgg.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
	EditText uname;
	EditText pwd;
	Button login;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        uname = (EditText) findViewById(R.id.uName);
        pwd = (EditText) findViewById(R.id.passWD);
        login = (Button) findViewById(R.id.loginBtn);
        
        login.setOnClickListener(btnListener);
        
    }
    
    private OnClickListener btnListener = new OnClickListener() {
        public void onClick(View v) {
        	if(uname.getText().toString().isEmpty())
        		Toast.makeText(getApplicationContext(), "Invalid User Name", Toast.LENGTH_LONG);
        	else if(pwd.getText().toString().isEmpty())
        		Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG);
        	else{
        			Intent i = new Intent(getApplicationContext(), MainActivity.class);
        			i.putExtra("uname", uname.getText().toString());
        			i.putExtra("pwd", pwd.getText().toString());
        			startActivity(i);
        		}
        }
    };
}
