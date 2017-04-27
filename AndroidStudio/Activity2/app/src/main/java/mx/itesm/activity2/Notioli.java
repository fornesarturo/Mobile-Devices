package mx.itesm.activity2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Notioli extends AppCompatActivity {

    TextView title;
    EditText note1,note2;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notioli);

        title = (TextView) findViewById(R.id.notioli_textView);
        note1 = (EditText) findViewById(R.id.notioli_editText);
        note2 = (EditText) findViewById(R.id.notioli_editText2);
        home = (Button) findViewById(R.id.notioli_button);

        title.setText("Notioli");
        home.setText("Home");
        note1.setText("Note 1");
        note2.setText("Note 2");
    }

    public void goHome(View v){
        Intent i = new Intent();
        i.putExtra("message","You visited Notioli");
        setResult(Activity.RESULT_OK,i);
        finish();
    }
}
