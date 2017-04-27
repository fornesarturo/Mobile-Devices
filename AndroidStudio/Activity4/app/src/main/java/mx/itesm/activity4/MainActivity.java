package mx.itesm.activity4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    Button menu;
    EditText name;

    private Properties properties;
    private static final String MY_FILE = "properties.xml";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.main_editText);

        File file = new File(getFilesDir(),MY_FILE);
        properties = new Properties();

        try{
            if(file.exists()){
                FileInputStream fis = openFileInput(MY_FILE);
                properties.loadFromXML(fis);
                fis.close();
                name.setText(properties.getProperty("name"));
                Toast.makeText(this,"Loaded from storage",Toast.LENGTH_SHORT).show();
            }
            else{
                FileOutputStream fos = openFileOutput(MY_FILE, Context.MODE_PRIVATE);
                properties.storeToXML(fos,null);
                fos.close();
                name.setText("");
                Toast.makeText(this,"Created file",Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Log.wtf("MAIN","error loading file");
        }
        String mensajeRaw = "";
        try{
            InputStream is = getResources().openRawResource(R.raw.raw);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            mensajeRaw = br.readLine();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        String mensajeAssets = "";
        try{
            InputStream is = getAssets().open("asset_file");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            mensajeAssets = br.readLine();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        Toast.makeText(this, mensajeRaw+" "+mensajeAssets,Toast.LENGTH_SHORT).show();

    }

    public void goMenu(View v){
        properties.setProperty("name",name.getText().toString());
        try{
            FileOutputStream fos = openFileOutput(MY_FILE,Context.MODE_PRIVATE);
            properties.storeToXML(fos,null);
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        Intent i = new Intent(this,Menu.class);
        i.putExtra("firstUser",name.getText().toString());
        startActivityForResult(i,0);
    }
}
