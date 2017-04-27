package mx.itesm.class7;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by forne on 07/03/2017.
 */

public class StudentAdapter extends BaseAdapter {
    private ArrayList<Student> data;
    private Activity activity;

    public StudentAdapter(ArrayList<Student> data, Activity activity){
        this.data = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Create view if it doesn't exits.
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        //Populate with data.
        TextView nameText = (TextView) convertView.findViewById(R.id.row_large);
        TextView gradeText = (TextView) convertView.findViewById(R.id.row_small);

        Student student = data.get(position);

        nameText.setText(student.getName());
        gradeText.setText(String.valueOf(student.getGrade()));

        return convertView;
    }
}
