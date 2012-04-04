package com.uwindsor.elgg.project;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.uwindsor.elgg.project.adapters.*;

public class WireActivity extends Activity {
	Button submit;
	EditText post;
	ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wire);
		
		submit = (Button) findViewById(R.id.W_button);
		post = (EditText) findViewById(R.id.W_post);
		
		List<JSONObject> result = new ArrayList<JSONObject>();
		
		JSONObject JSON;
		JSONArray names = new JSONArray();
		try {
			JSON = new JSONObject(getIntent().getStringExtra("json"));
			JSONObject results = JSON.getJSONObject("result");
			names = JSON.getJSONObject("result").names();
			Log.d("result",names.get(0).toString());
			for(int i = 0; i<names.length(); i++){
				result.add(results.getJSONObject(names.get(i).toString()));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Adapter a = new SimpleAdapter(this, result, getIntent().getStringExtra("uname"));
		ListView list = (ListView) findViewById(R.id.W_myPosts);
		list.setAdapter((ListAdapter) a);
	}
}
