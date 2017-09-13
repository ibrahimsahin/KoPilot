package ankarabt.kopilot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by isahin on 17.8.2017.
 */
public class AnlikFragment extends Fragment {

    TextView speed_text_instant, varis_suresi_text_instant;
    View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView_ = inflater.inflate(R.layout.anlik_fragment, container, false);
        rootView = rootView_;
        init();
        Log.v("anlik vreate","ed");
        print();
        return rootView;
    }

    public void init()
    {
        speed_text_instant = (TextView) rootView.findViewById(R.id.speed_text_instant);
        varis_suresi_text_instant= (TextView) rootView.findViewById(R.id.varis_suresi_text_instant);
    }

    public void print()
    {
        Log.v("anlik created","print");
        speed_text_instant.setText(Ortak.anlik_speed_text_instant);
        varis_suresi_text_instant.setText(Ortak.anlik_varis_suresi_text_instant);
    }

}
