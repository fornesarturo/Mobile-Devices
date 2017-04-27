package mx.itesm.activity2_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Friend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        Intent i = getIntent();
        TextView name = (TextView) findViewById(R.id.textView_name);
        TextView hobby = (TextView) findViewById(R.id.textView_hobby);
        TextView age = (TextView) findViewById(R.id.textView_age);
        TextView phone = (TextView) findViewById(R.id.textView_phone);
        TextView address = (TextView) findViewById(R.id.textView_address);

        name.setText("Name: "+i.getStringExtra("name"));
        hobby.setText("Hobby: "+i.getStringExtra("hobby"));
        age.setText("Age: "+i.getStringExtra("age"));
        phone.setText("Phone: "+i.getStringExtra("phone"));
        address.setText("Address: "+i.getStringExtra("address"));

    }
}
