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
public class OrtalamaFragment extends Fragment {

    TextView speed_text_average,varis_suresi_text_ortalama;
    View rootView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView_ = inflater.inflate(R.layout.ortalama_fragment, container, false);
        rootView = rootView_;
        init();
        Log.v("ortalama vreate","ed");
        print();
        return rootView;
    }

    public void init()
    {
        speed_text_average = (TextView) rootView.findViewById(R.id.speed_text_average);
        varis_suresi_text_ortalama= (TextView) rootView.findViewById(R.id.varis_suresi_text_ortalama);
    }

    void print()
    {
        speed_text_average.setText(Ortak.ortalama_speed_text);
        varis_suresi_text_ortalama.setText(Ortak.ortalama_varis_suresi_text);
    }
}
