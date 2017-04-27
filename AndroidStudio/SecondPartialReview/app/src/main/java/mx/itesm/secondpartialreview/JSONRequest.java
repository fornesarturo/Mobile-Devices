package mx.itesm.secondpartialreview;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by forne on 27/03/2017.
 */

public class JSONRequest extends AsyncTask<String, Void, JSONArray> {

    JSONRequestCallback listener;

    public JSONRequest(JSONRequestCallback listener){
        this.listener = listener;
    }

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
                String line = "";
                while((line = br.readLine()) != null){
                    Log.d("JSON RECIBIDO",line);
                    sb.append(line);
                }
                result = new JSONArray(sb.toString());
                Log.d("JSONArray",result.toString());
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

    public interface JSONRequestCallback {
        public void done(JSONArray jsonArray);
    }
}
