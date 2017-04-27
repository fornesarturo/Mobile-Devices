package mx.itesm.activity3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Friends extends AppCompatActivity {

    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        home = (Button) findViewById(R.id.friends_button);

        home.setText("Home");
    }

    public void goHome(View v){
        Intent i = new Intent();
        setResult(Activity.RESULT_OK,i);
        finish();
    }
}
