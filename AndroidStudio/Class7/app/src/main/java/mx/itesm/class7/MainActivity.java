package mx.itesm.class7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    ArrayList<Student> studentList = new ArrayList<Student>();
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Data Source
        // -Array
        // -Table on DB
        // -JSON Answer
        String[] students = {"Daniel","Dom","Pablo","El Infame"};

        studentList.add(new Student("Memo",30));
        studentList.add(new Student("Isra",40));
        studentList.add(new Student("Pablo",99));
        studentList.add(new Student("El infame",70));
        studentList.add(new Student("Miguel",98));

        String json = "[{'name':'A','grade':70}," +
                "{'name':'B','grade':86}," +
                "{'name':'C','grade':60}]";
        jsonArray=null;
        try{
            jsonArray = new JSONArray(json);
        }catch(JSONException joe){
            joe.printStackTrace();
        }

        //Adapter
        //-Translates from data source to UI
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, students);
        StudentAdapter studentAdapter = new StudentAdapter(studentList,this);
        JSONAdapter jsonAdapter = new JSONAdapter(jsonArray,this);

        //UI widget
        //-List View
        //-Spinner
        ListView listView = (ListView) findViewById(R.id.main_listview);
        Spinner spinner = (Spinner) findViewById(R.id.main_spinner);

        listView.setAdapter(jsonAdapter);
        spinner.setAdapter(jsonAdapter);

        listView.setOnItemClickListener(this);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, studentList.get(position).getName(), Toast.LENGTH_SHORT).show();
        try{
            Toast.makeText(this, jsonArray.getJSONObject(position).getString("name"), Toast.LENGTH_SHORT).show();
        }catch(JSONException joe){
            joe.printStackTrace();
        }

        Intent i = new Intent(this, TabActivity.class);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, studentList.get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
    }
}
