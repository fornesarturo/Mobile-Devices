package mx.itesm.activity3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Hobby extends AppCompatActivity {

    EditText hobby;
    TextView title,question;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby);

        title = (TextView) findViewById(R.id.hobby_textView);
        question = (TextView) findViewById(R.id.hobby_textView2);
        hobby = (EditText) findViewById(R.id.hobby_editText);
        home = (Button) findViewById(R.id.hobby_button);

        title.setText("My hobby: Juggling");
        home.setText("Home");
        question.setText("What's your hobby?");
        hobby.setText("Enter your hobby here");

    }

    public void goHome(View v){
        Intent i = new Intent();
        i.putExtra("hobby",hobby.getText().toString());
        setResult(Activity.RESULT_OK,i);
        finish();
    }
}
