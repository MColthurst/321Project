package com.uwindsor.elgg.project;


import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.uwindsor.elgg.project.adapters.*;
import com.uwindsor.elgg.project.utils.WireUtils;
import com.uwindsor.elgg.project.utils.profileUtils;

public class WireActivity extends Activity {
	Activity a = new Activity();
	
	Button submit;
	EditText post;
	ListView list;
	
	private String uname;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wire);
		
		a = this;
		uname = getIntent().getStringExtra("uname");
				
		submit = (Button) findViewById(R.id.W_button);
		post = (EditText) findViewById(R.id.W_post);
		
		List<JSONObject> result = new ArrayList<JSONObject>();
		
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
		
		Adapter a = new SimpleAdapter(this, result, uname);
		ListView list = (ListView) findViewById(R.id.W_myPosts);
		list.setAdapter((ListAdapter) a);
		
		submit.setOnClickListener(sListener);	
	}
	
	private OnClickListener sListener = new OnClickListener() {
    	public void onClick(View v) {
    			WireUtils.makePost(a, getApplicationContext(), uname, post.getText().toString(), getIntent().getStringExtra("auth_token"));
    		}
	};
}
