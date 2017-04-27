package mx.itesm.activity4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Hobby extends AppCompatActivity {

    EditText hobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby);

        hobby = (EditText) findViewById(R.id.hobby_editText);
        hobby.setText("");
    }

    public void goHome(View v){
        Intent i = new Intent();
        i.putExtra("hobby",hobby.getText().toString());
        setResult(Activity.RESULT_OK,i);
        finish();
    }
}
