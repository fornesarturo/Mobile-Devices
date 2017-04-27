package mx.itesm.class6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements JSONRequest.JSONRequestCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void request(View v){
        JSONRequest r = new JSONRequest(this);
        r.execute("https://api.github.com/","Hey","guyzz");
    }

    @Override
    public void done(JSONObject jsonObject) {
        Toast.makeText(this, "DONE!", Toast.LENGTH_SHORT).show();
        try{
            Log.d("JSON",jsonObject.getString("hub_url"));

            String json2 = "[{'name':'Sebastian De La Hoz','grade':'0'}," +
                    "{'name':'Dom','grade':'30'}]";
            JSONArray arreglito = new JSONArray(json2);
            for(int i = 0; i < arreglito.length(); i++){
                JSONObject primero = arreglito.getJSONObject(i);
                Log.d("JSON PERSONALIZADO",primero.getString("name"));
            }
        }catch(JSONException json){
            json.printStackTrace();
        }
    }
}
