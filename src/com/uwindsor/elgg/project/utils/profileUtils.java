package com.uwindsor.elgg.project.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.Toast;

import com.uwindsor.elgg.project.LoginActivity;
import com.uwindsor.elgg.project.ProfileActivity;
import com.uwindsor.elgg.project.http.AsyncHttpClient;
import com.uwindsor.elgg.project.http.AsyncHttpResponseHandler;

public class profileUtils {
	
	private static final String baseURL = "http://192.168.56.101/services/api/rest/json/?method=user.get_profile&api_key=6b189b42c41c3c31cc93f7bb697d8ab99626d532&auth_token=";
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	static JSONObject JSON = new JSONObject();
	static String name;
	static String uname;

	public static void getProfile(Context context, String auth_token) {
		
		final Context that = (Context) context;
		String url = baseURL.concat(auth_token);
		Log.d("URL ", url);
		
		client.get(url, new AsyncHttpResponseHandler(){
			
			@Override
			public void onSuccess (String response) {
				Log.d("json ", response);
				try {
					JSON = null;
					JSON = new JSONObject(response);
					if(JSON.getInt("status") == 0){
						JSONObject result = JSON.getJSONObject("result");
						JSONObject core = result.getJSONObject("core");
						uname = core.getString("username");
						name = core.getString("name");
					}
					else 
						name = "-1";
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Intent i = new Intent(that, ProfileActivity.class);
				i.putExtra("name", name);
				i.putExtra("uname", uname);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				that.startActivity(i);
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
		
	}

}
