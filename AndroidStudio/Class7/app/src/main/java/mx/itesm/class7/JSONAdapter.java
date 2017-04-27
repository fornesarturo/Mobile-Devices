package mx.itesm.class7;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by forne on 07/03/2017.
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
        }
        catch(JSONException joe){
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
        if(convertView==null){
            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView nameText = (TextView) convertView.findViewById(R.id.row_large);
        TextView gradeText = (TextView) convertView.findViewById(R.id.row_small);

        try{
            JSONObject student = data.getJSONObject(position);
            nameText.setText(student.getString("name"));
            gradeText.setText(student.getDouble("grade")+"");
        }catch(JSONException joe){
            joe.printStackTrace();
        }

        return convertView;
    }
}
