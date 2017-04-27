package mx.itesm.activity2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    Button checklist,notioli,places,calendar;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        checklist = (Button) findViewById(R.id.button);
        notioli = (Button) findViewById(R.id.button2);
        places = (Button) findViewById(R.id.button3);
        calendar = (Button) findViewById(R.id.button4);
        title = (TextView) findViewById(R.id.textView);

        title.setText("Ravioli Ravioli");
        checklist.setText("Checklist");
        notioli.setText("Notioli");
        places.setText("Places");
        calendar.setText("Calendar");
    }

    public void checklist(View v){
        Intent i = new Intent(this,Checklist.class);
        startActivityForResult(i,0);
    }

    public void notioli(View v){
        Intent i = new Intent(this,Notioli.class);
        startActivityForResult(i,1);
    }

    public void places(View v){
        Intent i = new Intent(this,Places.class);
        startActivityForResult(i,2);
    }

    public void calendar(View v){
        Intent i = new Intent(this,Calendar.class);
        startActivityForResult(i,3);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK){
            Toast.makeText(this,data.getStringExtra("message"), Toast.LENGTH_SHORT).show();
        }
    }
}
