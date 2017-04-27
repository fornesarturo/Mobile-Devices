package mx.itesm.activity2_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements JSONRequest.JSONRequestCallback, AdapterView.OnItemClickListener {

    ListView list;
    JSONAdapter jsonAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list_view);
    }

    /*
    [{"name":"Miguel","hobby":"Netflix","age":"19","phone":"3311224466","address":"Near-by"},{"name":"Santiago","hobby":"Civilization","age":"20","phone":"3456745300","address":"Far-far-away"},{"name":"Casiel","hobby":"Dogs","age":"20","phone":"3124534623","address":"First floor"},{"name":"Sebastián","hobby":"Racism","age":"9-ish","phone":"6666666666","address":"Next to Hitler"},{"name":"Edgar","hobby":"Driving","age":"95","phone":"1001100110","address":"Around the corner"}]
     */

    public void request(View v){
        JSONRequest r = new JSONRequest(this);
        r.execute("https://api.myjson.com/bins/1b9ml7");
    }

    @Override
    public void done(JSONArray jsonArray) {
        Toast.makeText(this, "DONE JSON-ing", Toast.LENGTH_SHORT).show();
        try{
            for(int i = 0; i < jsonArray.length(); i++){
                Log.d("JSON",i+" "+jsonArray.getJSONObject(i).get("hobby"));
            }
            jsonAdapter = new JSONAdapter(jsonArray, this);
            list.setAdapter(jsonAdapter);
            list.setOnItemClickListener(this);
        }catch(JSONException json){
            json.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show();
        try{
            Intent i = new Intent(this, Friend.class);
            JSONObject json = (JSONObject)parent.getItemAtPosition(position);
            i.putExtra("name",json.getString("name"));
            i.putExtra("hobby",json.getString("hobby"));
            i.putExtra("age",json.getString("age"));
            i.putExtra("phone",json.getString("phone"));
            i.putExtra("address",json.getString("address"));
            Toast.makeText(this, json.getString("name"), Toast.LENGTH_SHORT).show();
            startActivity(i);
        }catch(JSONException joe){
            joe.printStackTrace();
        }
    }
}
