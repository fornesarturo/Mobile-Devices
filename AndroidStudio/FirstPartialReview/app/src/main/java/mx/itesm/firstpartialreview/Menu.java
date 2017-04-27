package mx.itesm.firstpartialreview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    Button homeButton, noteButton;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        homeButton = (Button) findViewById(R.id.menu_buttonHome);
        homeButton.setText("HOME");
        noteButton = (Button) findViewById(R.id.menu_buttonNote);
        noteButton.setText("NOTE");
        name = (TextView) findViewById(R.id.menu_textViewName);

        Intent i = getIntent();
        name.setText(i.getStringExtra("from_parent"));

        noteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), OneNote.class);
                startActivityForResult(i, 2);
            }
        });
    }

    public void goHome(View v){
        Intent i = new Intent();
        i.putExtra("message", "Hope you had fun!");
        setResult(Activity.RESULT_OK, i);
        finish();
    }
}
