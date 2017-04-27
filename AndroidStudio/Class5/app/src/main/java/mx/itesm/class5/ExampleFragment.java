package mx.itesm.class5;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExampleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExampleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_1 = "one";
    private static final String ARG_2 = "two";
    private static final String ARG_3 = "three";

    // TODO: Rename and change types of parameters
    private String one, two, three;


    public ExampleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param p1 Parameter 1.
     * @param p2 Parameter 2.
     * @param p3 Parameter 3.
     * @return A new instance of fragment ExampleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExampleFragment newInstance(String p1, String p2, String p3) {
        ExampleFragment fragment = new ExampleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_1, p1);
        args.putString(ARG_2, p2);
        args.putString(ARG_3, p3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            one = getArguments().getString(ARG_1);
            two = getArguments().getString(ARG_2);
            three = getArguments().getString(ARG_3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_example, container, false);
        TextView t1 = (TextView) view.findViewById(R.id.example_fragment_textView1);
        TextView t2 = (TextView) view.findViewById(R.id.example_fragment_textView2);
        TextView t3 = (TextView) view.findViewById(R.id.example_fragment_textView3);

        t1.setText(one);
        t2.setText(two);
        t3.setText(three);

        return view;
    }

}
