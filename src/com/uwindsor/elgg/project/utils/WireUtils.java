package com.uwindsor.elgg.project.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.uwindsor.elgg.project.WireActivity;
import com.uwindsor.elgg.project.http.AsyncHttpClient;
import com.uwindsor.elgg.project.http.AsyncHttpResponseHandler;
import com.uwindsor.elgg.project.http.RequestParams;

public class WireUtils {

	private static final String baseURL = "http://192.168.56.101/services/api/rest/json/?method=wire.get_posts&api_key=6b189b42c41c3c31cc93f7bb697d8ab99626d532&username=";
	private static final String postURL = "http://192.168.56.101/services/api/rest/json/?method=wire.save_post&api_key=6b189b42c41c3c31cc93f7bb697d8ab99626d532&auth_token=";
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	static JSONObject JSON = new JSONObject();
	static String json;
	private static String name;
	
	public static void getPosts(Context context, String uname, final String auth_token, final Activity a) {
		
		final Context that = (Context) context;
		name = uname;
		String url = baseURL.concat(uname);
		Log.d("URL ", url);
		
		client.get(url, new AsyncHttpResponseHandler(){
			
			@Override
			public void onSuccess (String response) {
				Log.d("json response", response);
				try {
					JSON = new JSONObject(response);
					Log.d("JSON.toString", JSON.toString());
					if(JSON.getInt("status") == 0){
						json = JSON.toString();
					}
					else
						json = "empty";
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Intent i = new Intent(that, WireActivity.class);
				i.putExtra("json", json);
				i.putExtra("uname", name);
				i.putExtra("auth_token", auth_token);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				that.startActivity(i);
				if(a!=null)
					a.finish();
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

	public static void makePost(final Activity a, Context context, final String uname, String text, final String auth_token) {
		final Context that = (Context) context;
		
		RequestParams params = new RequestParams();
		params.put("username", uname);
		params.put("text", text);
		
		String url = postURL.concat(auth_token);
		
		client.post(url, params, new AsyncHttpResponseHandler(){
			
			@Override
			public void onSuccess (String response) {
				Log.d("json ", response);
				getPosts(that, uname, auth_token, a);
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
