package com.uwindsor.elgg.project.adapters;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uwindsor.elgg.project.R;

public class SimpleAdapter extends BaseAdapter {

	private Activity activity;
	private List<JSONObject> data;
	private String uname;
	private static LayoutInflater inflater = null;

	public SimpleAdapter(Activity a, List<JSONObject> d, String u) {
		activity = a;
		data = d;
		uname = u;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.rows, null);

		try {
			TextView text = (TextView) vi.findViewById(R.id.R_post_name);
			text.setText("Posted By: " + uname);
			
			text = (TextView) vi.findViewById(R.id.R_post_comment);
			text.setText(data.get(position).getString("description"));
			//text.setText(data.getJSONObject(position).getString("description"));
			
			text = (TextView) vi.findViewById(R.id.R_post_time);
			
			Date date = new Date ();
			Long unix_time = Long.parseLong(data.get(position).getString("time_created"));
			date.setTime((long)unix_time*1000);
			
			text.setText("@" + date.toString());
			//text.setText(data.getJSONObject(position).getString("time_created"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vi;
	}
}