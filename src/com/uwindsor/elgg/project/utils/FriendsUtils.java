package com.uwindsor.elgg.project.utils;

import org.json.JSONException;
import org.json.JSONObject;

import com.uwindsor.elgg.project.FriendsActivity;
import com.uwindsor.elgg.project.FriendsProfileActivity;
import com.uwindsor.elgg.project.ProfileActivity;
import com.uwindsor.elgg.project.http.AsyncHttpClient;
import com.uwindsor.elgg.project.http.AsyncHttpResponseHandler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class FriendsUtils {
	private static final String baseURL = "http://192.168.56.101/services/api/rest/json/?method=user.friend.get_friends&api_key=6b189b42c41c3c31cc93f7bb697d8ab99626d532&username=";
	private static final String profileURL = "http://192.168.56.101/services/api/rest/json/?method=user.get_profile&api_key=6b189b42c41c3c31cc93f7bb697d8ab99626d532&username=";
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	static JSONObject JSON = new JSONObject();
	static String json;
	
	static String name;
	static String usrname;
	static String about;
	static String city;
	static String email;
	static String avatarURL;

	public static void getFriends(Context context, String uname) {
		final Context that = (Context) context;
		String url = baseURL.concat(uname);
		
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
						json = null;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Intent i = new Intent(that, FriendsActivity.class);
				i.putExtra("json", json);
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

	public static void getProfile(Context context, String uname) {
		final Context that = (Context) context;
		String url = profileURL.concat(uname);
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
						usrname = core.getString("username");
						name = core.getString("name");
						
						JSONObject profile_fields = result.getJSONObject("profile_fields");
						
						JSONObject description = profile_fields.getJSONObject("description");
						about = description.getString("value");
						
						JSONObject location = profile_fields.getJSONObject("location");
						city = location.getString("value");
						
						JSONObject contactemail = profile_fields.getJSONObject("contactemail");
						email = contactemail.getString("value");
						
						avatarURL = result.getString("avatar_url");
						
					}
					else 
						name = "-1";
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Intent i = new Intent(that, FriendsProfileActivity.class);
				i.putExtra("name", name);
				i.putExtra("uname", usrname);
				i.putExtra("about", about);
				i.putExtra("city", city);
				i.putExtra("email", email);
				i.putExtra("avatarURL", avatarURL);
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
	
	//Remove Friend

}
