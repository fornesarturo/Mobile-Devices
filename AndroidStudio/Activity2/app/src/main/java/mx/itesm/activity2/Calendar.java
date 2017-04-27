package mx.itesm.activity2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {

    Button home;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        title = (TextView) findViewById(R.id.calendar_textView);
        home = (Button) findViewById(R.id.calendar_button);

        title.setText("Calendar");
        home.setText("Home");
    }

    public void goHome(View v){
        Intent i = new Intent();
        i.putExtra("message","You visited Calendar");
        setResult(Activity.RESULT_OK,i);
        finish();
    }
}
