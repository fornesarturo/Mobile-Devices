package mx.itesm.activity3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    TextView title;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.main_editText);
        title = (TextView) findViewById(R.id.main_textView);
        button = (Button) findViewById(R.id.main_button);

        title.setText("Welcome!");
        name.setText("Enter your name here");
        button.setText("Click me!");
    }

    public void goMenu(View v){
        Intent i = new Intent(this,Menu.class);
        i.putExtra("name",name.getText().toString());
        startActivityForResult(i,0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) {
            Toast.makeText(this, "You had fun?", Toast.LENGTH_SHORT).show();
        }
    }
}
