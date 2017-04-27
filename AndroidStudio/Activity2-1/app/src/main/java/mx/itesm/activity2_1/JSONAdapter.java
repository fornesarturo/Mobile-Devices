package mx.itesm.activity2_1;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by forne on 18/03/2017.
 */

public class JSONAdapter extends BaseAdapter {
    private JSONArray data;
    private Activity activity;

    public JSONAdapter(JSONArray data, Activity activity){
        this.data = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public Object getItem(int position) {
        try{
            return data.getJSONObject(position);
        }catch(JSONException joe){
            joe.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.row,null);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView hobby = (TextView) convertView.findViewById(R.id.hobby);

        try{
            JSONObject friend = data.getJSONObject(position);
            name.setText(friend.getString("name"));
            hobby.setText(friend.getString("hobby"));
        }catch(JSONException joe){
            joe.printStackTrace();
        }
        return convertView;
    }
}
