package mx.itesm.class2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = getIntent();
        Toast.makeText(this, i.getStringExtra("test"), Toast.LENGTH_SHORT).show();
    }

    public void getBack(View v){
        Intent i = new Intent();
        i.putExtra("result","everything was super good");
        setResult(Activity.RESULT_OK,i);
        finish();
    }
}
