package com.uwindsor.elgg.project;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uwindsor.elgg.project.http.AsyncHttpClient;
import com.uwindsor.elgg.project.http.AsyncHttpResponseHandler;
import com.uwindsor.elgg.project.http.RequestParams;

public class LoginActivity extends Activity{
	private static final String baseURL = "http://192.168.2.20/services/api/rest/json/?method=auth.gettoken&api_key=6b189b42c41c3c31cc93f7bb697d8ab99626d532";
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	static JSONObject JSON = new JSONObject();
	static String authToken;
	
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
        		
        		/*
        		 * Wanted to do this in ConnectionUtils but couldn't get the return value
        		 * to work right so I've put it here for now it's a bit sloppy but it works.
        		 */
        		
        		RequestParams params = new RequestParams();
        		params.put("username", uname.getText().toString());
        		params.put("password", pwd.getText().toString());
        			
        		client.post(baseURL, params, new AsyncHttpResponseHandler(){
        			
        			@Override
        			public void onSuccess (String response) {
        				Log.d("json ", response);
        				try {
        					JSON = null;
        					JSON = new JSONObject(response);
        					if(JSON.getInt("status") == 0){
        						authToken = JSON.getString("result");
                				Intent i = new Intent();
                				i.putExtra("auth_token", authToken);
                				setResult(RESULT_OK, i);
        					}
        					else{ 
        						setResult(RESULT_CANCELED);
        					}
        				} catch (JSONException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}

            				finish();
        			}
        	
        			@Override
        			public void onFailure (Throwable t) {
        				Toast toast = Toast.makeText(getApplicationContext(), "Internet not connected... Please connect and try again.",
        						Toast.LENGTH_LONG);
        				toast.show();
        			}
        	
        			@Override
        			public void onFinish () {

        			}
        		});
        	}
        };
    };
}
