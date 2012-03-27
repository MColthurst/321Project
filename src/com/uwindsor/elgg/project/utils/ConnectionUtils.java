package com.uwindsor.elgg.project.utils;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import com.uwindsor.elgg.project.MainActivity;
import com.uwindsor.elgg.project.http.AsyncHttpClient;
import com.uwindsor.elgg.project.http.AsyncHttpResponseHandler;
import com.uwindsor.elgg.project.http.RequestParams;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.SlidingDrawer;
import android.widget.Toast;


public class ConnectionUtils {

	private static final String baseURL = "http://192.168.2.20/services/api/rest/json/?method=auth.gettoken&api_key=6b189b42c41c3c31cc93f7bb697d8ab99626d532";
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	static JSONObject JSON = new JSONObject();
	static String authToken;
	
	public static String Connect(Context context, String uname, String pwd){
		
		final Context that = (Context) context;
		RequestParams params = new RequestParams();
		params.put("username", uname);
		params.put("password", pwd);
		
		client.post(baseURL, params, new AsyncHttpResponseHandler(){
		
			@Override
			public void onSuccess (String response) {
				Log.d("json ", response);
				try {
					JSON = null;
					JSON = new JSONObject(response);
					if(JSON.getInt("status") == 0){
						authToken = JSON.getString("result");
					}
					else 
						authToken = "-1";
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Thread.sleep(1000);
					} catch(InterruptedException e) {
					} 
			}
	
			@Override
			public void onFailure (Throwable t) {
				Toast toast = Toast.makeText(that, "Internet not connected... Please connect and try again.",
						Toast.LENGTH_LONG);
				toast.show();
			}
	
			@Override
			public void onFinish () {

			}
		});
			
		return authToken;
	}
	
}