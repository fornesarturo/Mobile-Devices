package mx.itesm.activity3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Message extends AppCompatActivity {

    Button send;
    TextView title;
    EditText message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        title = (TextView) findViewById(R.id.message_textView);
        message = (EditText) findViewById(R.id.message_editText);
        send = (Button) findViewById(R.id.message_button);

        title.setText("Send a message");
        message.setText("Write here!");
        send.setText("Send");
    }

    public void sendText(View v){
        Intent i = new Intent();
        Toast.makeText(this, "Message sent!", Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK,i);
        finish();
    }
}
