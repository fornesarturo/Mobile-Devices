package mx.itesm.activity2_1;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by forne on 18/03/2017.
 */

public class JSONRequest extends AsyncTask<String, Void, JSONArray> {

    private JSONRequestCallback listener;

    public JSONRequest(JSONRequestCallback listener){this.listener = listener;}

    @Override
    protected JSONArray doInBackground(String... params) {
        JSONArray result = null;
        try{
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int code = connection.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK){
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                StringBuilder sb = new StringBuilder();
                String currentLine = "";

                while((currentLine = br.readLine()) != null){
                    Log.d("JSON RECEIVED",currentLine);
                    sb.append(currentLine);
                }

                result = new JSONArray(sb.toString());

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }


    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        listener.done(jsonArray);
    }

    public interface JSONRequestCallback{
        void done(JSONArray jsonArray);
    }
}
