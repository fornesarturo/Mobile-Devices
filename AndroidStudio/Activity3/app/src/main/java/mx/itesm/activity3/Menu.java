package mx.itesm.activity3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    TextView name,hobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        name = (TextView) findViewById(R.id.menu_textView);
        hobby = (TextView) findViewById(R.id.menu_textView2);

        Intent i = getIntent();
        name.setText("Hi "+i.getStringExtra("name"));
        hobby.setText("");
    }

    public void hobbies(View v){
        Intent i = new Intent(this, Hobby.class);
        startActivityForResult(i,1);
    }

    public void friends(View v){
        Intent i = new Intent(this, Friends.class);
        startActivityForResult(i,2);
    }

    public void message(View v){
        Intent i = new Intent(this, Message.class);
        startActivityForResult(i,3);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 1) {
                hobby.setText("Your hobby is: "+data.getStringExtra("hobby")+". That's cool");
            }
            Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show();
        }
    }
}
