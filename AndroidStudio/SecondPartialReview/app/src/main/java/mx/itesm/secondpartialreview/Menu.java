package mx.itesm.secondpartialreview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Menu extends AppCompatActivity implements JSONRequest.JSONRequestCallback {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        listView = (ListView) findViewById(R.id.menu_listView);
    }

    //"https://api.myjson.com/bins/1b9ml7"

    public void fetch(View v){
        JSONRequest r = new JSONRequest(this);
        r.execute("https://api.myjson.com/bins/1b9ml7");
    }

    @Override
    public void done(JSONArray jsonArray) {
        Log.d("JSON DONE","I'm done");
        JSONAdapter adapter = new JSONAdapter(jsonArray, this);
        listView.setAdapter(adapter);
    }
}
