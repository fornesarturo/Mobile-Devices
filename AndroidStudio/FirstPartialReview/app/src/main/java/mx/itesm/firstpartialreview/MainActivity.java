package mx.itesm.firstpartialreview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    EditText nameEntry;
    Button goMenu, getMessage;
    TextView greetings;
    Properties properties;
    static final String MY_FILE = "properties.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEntry = (EditText) findViewById(R.id.main_editText);
        getMessage = (Button) findViewById(R.id.main_buttonM);
        goMenu = (Button) findViewById(R.id.main_button);
        greetings = (TextView) findViewById(R.id.main_textView);

        properties = new Properties();

        nameEntry.setText("");

        goMenu.setText("MENU");
        goMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                properties.setProperty("value", nameEntry.getText().toString());
                savePropertyToFile();
                Intent i = new Intent(getApplicationContext(), Menu.class);
                i.putExtra("from_parent", nameEntry.getText().toString());
                startActivityForResult(i, 1);
            }
        });

        File file = new File(getFilesDir(), MY_FILE);

        try {
            if (file.exists()){
                FileInputStream fis = openFileInput(MY_FILE);
                properties.loadFromXML(fis);
                fis.close();
                nameEntry.setText(properties.getProperty("value"));
                Toast.makeText(this, "LOADED FROM STORAGE", Toast.LENGTH_SHORT).show();
            }
            else{
                FileOutputStream fos = openFileOutput(MY_FILE, Context.MODE_PRIVATE);
                properties.storeToXML(fos, null);
                fos.close();
                Toast.makeText(this, "CREATED FILE", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Log.wtf("MAIN","failed to load file");
        }

        getMessage.setText("GET MESSAGE");
        getMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m;
                String message = "";
                try{
                    InputStream is = getResources().openRawResource(R.raw.raw_file);
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    message = m = br.readLine();
                    while((m = br.readLine())!= null){
                        Toast.makeText(getApplicationContext(), m, Toast.LENGTH_SHORT).show();
                    }
                    is.close();
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }

                try{
                    InputStream is = getAssets().open("assets_file");
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    message += " "+br.readLine();
                    is.close();
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }
                greetings.setText(message);
            }

        });
    }

    private void savePropertyToFile() {
        try{
            FileOutputStream fos = openFileOutput(MY_FILE, Context.MODE_PRIVATE);
            properties.storeToXML(fos, null);
            fos.close();
            Toast.makeText(this, "Successfully saved to file", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Log.wtf("MAIN", "failed to save file");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK && requestCode == 1){
            Toast.makeText(this, data.getStringExtra("message"),Toast.LENGTH_SHORT).show();
        }
    }
}
