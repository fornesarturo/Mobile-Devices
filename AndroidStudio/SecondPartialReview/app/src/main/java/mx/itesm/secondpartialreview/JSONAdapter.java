package mx.itesm.secondpartialreview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;

/**
 * Created by forne on 27/03/2017.
 */

public class JSONAdapter extends BaseAdapter {
    JSONArray data;
    Activity activity;

    public JSONAdapter(JSONArray data, Activity activity){
        this.data = data;
        this.activity = activity;
    };
    @Override
    public int getCount() {
        return this.data.length();
    }

    @Override
    public Object getItem(int position) {
        Object result = null;
        try{
            result = this.data.get(position);
        }catch(JSONException joe){
            joe.printStackTrace();
        }
        return result;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.row_name);
        TextView hobby = (TextView) convertView.findViewById(R.id.row_hobby);

        try{
            JSONObject person = this.data.getJSONObject(position);
            name.setText(person.getString("name"));
            hobby.setText(person.getString("hobby"));
        }catch(JSONException joe){
            joe.printStackTrace();
        }

        return convertView;
    }
}
