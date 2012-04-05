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
import android.widget.ImageView;
import android.widget.TextView;

import com.uwindsor.elgg.project.R;

public class FriendsAdapter extends BaseAdapter {

	private Activity activity;
	private List<JSONObject> data;
	private static LayoutInflater inflater = null;
	private Context that;

	public FriendsAdapter(Activity a, List<JSONObject> d, Context context) {
		activity = a;
		data = d;
		that = (Context) context;
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
			vi = inflater.inflate(R.layout.friendrows, null);

		try {
			TextView text = (TextView) vi.findViewById(R.id.FR_uname);
			text.setText(data.get(position).getString("username"));
			
			text = (TextView) vi.findViewById(R.id.FR_name);
			text.setText(data.get(position).getString("name"));
			
			ImageView picture = (ImageView) vi.findViewById(R.id.FR_picture);
			ImageLoader imageLoader = new ImageLoader(that);
			imageLoader.DisplayImage(data.get(position).getString("avatar_url"), activity, picture);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vi;
	}
}