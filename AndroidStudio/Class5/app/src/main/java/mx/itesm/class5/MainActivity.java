package mx.itesm.class5;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PizzaFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void printMessage(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void addFragment(View v){
        //Fragment manager
        FragmentManager manager = getFragmentManager();

        //Tansaction
        FragmentTransaction transaction = manager.beginTransaction();

            //Logic:
            //Crear referencia a un nuevo fragmento.
        PizzaFragment fragment = new PizzaFragment();
        //PizzaFragment fragment = PizzaFragment.newInstance("","");

            //Agregar un nuevo fragmento a actividad.
        transaction.add(R.id.pizza_box, fragment, "pizza");


        //Commit
        transaction.commit();
    }

    public void removeFragment(View v){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        //Ya tenemos el fragmento, así que lo buscamos.
        PizzaFragment fragment = (PizzaFragment)manager.findFragmentByTag("pizza");

        if(fragment == null){
            return;
        }
        transaction.remove(fragment);

        transaction.commit();
    }

    public void addExample(View v){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        ExampleFragment fragment = ExampleFragment.newInstance("HELLO","YOU","THERE");

        transaction.add(R.id.pizza_box, fragment, "example");
        transaction.commit();
    }

    public void removeExample(View v){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        //Ya tenemos el fragmento, así que lo buscamos.
        ExampleFragment fragment = (ExampleFragment)manager.findFragmentByTag("example");

        if(fragment == null){
            return;
        }
        transaction.remove(fragment);

        transaction.commit();
    }
}
