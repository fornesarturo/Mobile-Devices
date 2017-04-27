package mx.itesm.secondpartialreview;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentWithButton.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goMenu(View v){
        Intent i = new Intent(this, Menu.class);
        startActivityForResult(i, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == 0){
            Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show();
        }
    }

    public void createFragment(View v){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        FragmentWithButton fragment = FragmentWithButton.newInstance("Hey","you");
        transaction.add(R.id.fragment_space, fragment, "button");
        transaction.commit();
    }

    public void removeFragment(View v){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        FragmentWithButton fragment = (FragmentWithButton) manager.findFragmentByTag("button");
        if(fragment == null){
            return;
        }
        transaction.remove(fragment);
        transaction.commit();
    }

    @Override
    public void toastText(String text, String text2) {
        Toast.makeText(this, text+" "+text2, Toast.LENGTH_SHORT).show();
    }
}
