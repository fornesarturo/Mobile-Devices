package mx.itesm.class2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;

    TextView textView;
    Button button,button2;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.editText);

        textView.setText("This is a textView");
        button.setText("Other Activity");
        button2.setText("Parrot Button");
        editText.setText("This is an editText");

        //Context - A class that keeps info about the device.
        //Activity extends context (so we can use it through polymorphism).
        //getContext() - will return a Context object with the same data.
        Toast.makeText(this, "Hey, guys!", Toast.LENGTH_SHORT).show();

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),editText.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    //using buttonWasClicked to catch a button press.
    public void buttonWasClicked(View v){
        Toast.makeText(this, "Button was clicked",Toast.LENGTH_SHORT).show();

        //In Android, to open up a new activity you don't COMMAND, you ask!
        //Intent - request for activity
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("test",editText.getText().toString());
        startActivityForResult(intent,REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Toast.makeText(this,data.getStringExtra("result"),Toast.LENGTH_SHORT).show();
        }
    }
}
