package mx.itesm.activity4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Friends extends AppCompatActivity {

    EditText name, hobby;
    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        name = (EditText) findViewById(R.id.friends_name);
        hobby = (EditText) findViewById(R.id.friends_hobby);
        dbh = new DBHelper(this);

        name.setText("");
        hobby.setText("");
    }

    public void save(View v){
        String friend = name.getText().toString();
        String friendHobby = hobby.getText().toString();
        dbh.save(friend,friendHobby);
    }

    public void search(View v){
        String friend = name.getText().toString();
        String friendHobby = dbh.find(friend);
        hobby.setText(friendHobby);
    }

    public void delete(View v){
        String unfriend = name.getText().toString();
        dbh.delete(unfriend);
    }

    public void goHome(View v){
        Intent i = new Intent();
        setResult(Activity.RESULT_OK,i);
        finish();
    }
}
