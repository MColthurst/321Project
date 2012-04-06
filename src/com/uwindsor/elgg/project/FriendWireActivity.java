package com.uwindsor.elgg.project;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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

public class FriendWireActivity extends Activity {
	Activity a = new Activity();
	
	ListView list;
	
	private String uname;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friendswire);
		
		a = this;
		uname = getIntent().getStringExtra("uname");
			
		
		List<JSONObject> result = new ArrayList<JSONObject>();
		
		JSONObject JSON;
		JSONArray names = new JSONArray();
		try {
			Log.d("jsonString", getIntent().getStringExtra("json"));
			JSON = new JSONObject(getIntent().getStringExtra("json"));
			JSONObject results = JSON.getJSONObject("result");
			Log.d("results", results.toString());
			names = JSON.getJSONObject("result").names();
			
			List<String> sortlst = new ArrayList<String>();
			for(int i = 0; i<names.length(); i++){
				Log.d("1st order",names.get(i).toString());
				sortlst.add(names.get(i).toString());
			}
			
			Collections.sort(sortlst, new Comparator<String>() {

				@Override
				public int compare(String lhs, String rhs) {

					int one = Integer.parseInt(lhs);
					int two = Integer.parseInt(rhs);
					
					if(one<two) return 1;
					else if(one==two) return 0;
					else return -1;
				}


			});
			
			Log.d("names", sortlst.toString());
			for(int i = 0; i<names.length(); i++){
				Log.d("2nd order",sortlst.get(i));
				result.add(results.getJSONObject(sortlst.get(i)));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Adapter a = new SimpleAdapter(this, result, uname);
		ListView list = (ListView) findViewById(R.id.FW_Posts);
		list.setAdapter((ListAdapter) a);
		
	}
	
}
