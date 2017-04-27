package mx.itesm.activity4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    TextView userHobby, greetings;
    Button hobby, friends;
    DBHelper dbh;
    private static final int HOBBY = 1;
    private static final int FRIENDS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        dbh = new DBHelper(this);
        userHobby = (TextView) findViewById(R.id.menu_hobby);
        greetings = (TextView) findViewById(R.id.menu_textViewGreetings);

        greetings.setText("Welcome, "+getIntent().getStringExtra("firstUser"));

        userHobby.setText("");
    }

    public void goFriends(View v){
        Intent i = new Intent(this,Friends.class);
        startActivityForResult(i,FRIENDS);
    }

    public void goHobby(View v){
        Intent i = new Intent(this,Hobby.class);
        startActivityForResult(i,HOBBY);
    }

    public void goHome(View v){
        Intent i = new Intent();
        setResult(Activity.RESULT_OK,i);
        finish();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == HOBBY){
            dbh.save("app_user",data.getStringExtra("hobby"));
            String user = dbh.find("app_user");
            userHobby.setText("Your hobby is: "+user+", that's cool");
        }
    }
}
