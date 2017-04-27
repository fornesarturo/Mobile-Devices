package mx.itesm.firstpartialreview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class OneNote extends AppCompatActivity {

    DBHelper dbh;
    EditText entry;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_note);

        dbh = new DBHelper(this);
        entry = (EditText) findViewById(R.id.note_editText_entry);
        result = (TextView) findViewById(R.id.note_textView_result);
    }

    public void addValue(View v){
        String name = entry.getText().toString();
        dbh.add(name);
        result.setText("Value added");
    }

    public void findValue(View v){
        String name = entry.getText().toString();
        int id = dbh.find(name);
        result.setText("Found with ID: " + id);
    }

    public void deleteValue(View v){
        int id = Integer.parseInt(entry.getText().toString());
        int no = dbh.delete(id);
        result.setText("Deleted "+no+" rows");
    }

    public void goHome(View v){
        Intent i = new Intent();
        setResult(Activity.RESULT_OK, i);
        finish();
    }
}
