package com.uwindsor.elgg.project;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;

import com.uwindsor.elgg.project.adapters.FriendsAdapter;
import com.uwindsor.elgg.project.utils.FriendsUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class FriendsActivity extends Activity {
	ListView list;
	List<JSONObject> result = new ArrayList<JSONObject>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friends);
		
		JSONObject JSON;
		JSONArray names = new JSONArray();
		try {
			Log.d("jsonString", getIntent().getStringExtra("json"));
			JSON = new JSONObject(getIntent().getStringExtra("json"));
			JSONObject results = JSON.getJSONObject("result");
			Log.d("results", results.toString());
			names = JSON.getJSONObject("result").names();
			Log.d("names", names.toString());
			for(int i = 0; i<names.length(); i++){
				Log.d("order",names.get(i).toString());
				result.add(results.getJSONObject(names.get(i).toString()));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		Adapter a = new FriendsAdapter(this, result, getApplicationContext());
		ListView list = (ListView) findViewById(R.id.F_list);
		list.setAdapter((ListAdapter) a);
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick (AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				try {
					Log.d("Clicked",result.get(arg2).getString("username"));
					FriendsUtils.getProfile(getApplicationContext(), result.get(arg2).getString("username"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Log.d("clicked", records.get(arg2).getChildNodes().item(0).toString());
				//Element e = records.get(arg2);
				//Log.d("ID: ", e.getChildNodes().item(1).getTextContent());
				//ListUtils.viewDetails(getApplicationContext(), e.getChildNodes().item(1).getTextContent());
			}
		
		});
		
	}
}
