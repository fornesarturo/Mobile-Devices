package mx.itesm.class4;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private TextView textView;
    private EditText editText;

    private Properties properties;
    private static final String myFile = "properties.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText2);

        //Code for properties.
        File file = new File(getFilesDir(),myFile);
        properties = new Properties();

        try{
            if(file.exists()){
                FileInputStream fis = openFileInput(myFile);
                properties.loadFromXML(fis);
                fis.close();
                Toast.makeText(this, "LOADED FROM STORAGE", Toast.LENGTH_SHORT).show();
            } else{
                FileOutputStream fos = openFileOutput(myFile, Context.MODE_PRIVATE);
                properties.storeToXML(fos, null);
                fos.close();
                Toast.makeText(this, "CREATED THE FILE", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Log.wtf("MAIN","error loading file");
        }
    }

    public void add(View v){
        dbHelper.save(editText.getText().toString());
        Toast.makeText(this, "VALUE SAVED!", Toast.LENGTH_SHORT).show();
    }

    public void find(View v){
        int result = dbHelper.find(editText.getText().toString());
        Toast.makeText(this, "YOUR RESULT IS: " + result, Toast.LENGTH_SHORT).show();
    }

    public void delete(View v){
        int result = dbHelper.delete(Integer.parseInt(editText.getText().toString()));
        Toast.makeText(this, "ROWS AFFECTED: " + result, Toast.LENGTH_SHORT).show();
    }

    public void saveProperty(View v){
        properties.setProperty("demo",editText.getText().toString());
        Log.d("MAIN","saved property");
    }

    public void loadProperty(View v){
        editText.setText(properties.getProperty("demo"));
    }

    public void savePropertyFile(View v){
        try{
            FileOutputStream fos = openFileOutput(myFile, Context.MODE_PRIVATE);
            properties.storeToXML(fos, null);
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void loadRawFile(View v){
        try{
            InputStream is = getResources().openRawResource(R.raw.file1);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            Toast.makeText(this, br.readLine(), Toast.LENGTH_SHORT).show();

            String content;
            while((content = br.readLine()) != null){
                Log.d("RESTO DEL TEXTO", content);
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void loadAssetsFile(View v){
        try{
            InputStream is = getAssets().open("demo_asset");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            Toast.makeText(this, br.readLine(), Toast.LENGTH_SHORT).show();

            String content;
            while((content = br.readLine()) != null){
                Log.d("ASSET",content);
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
