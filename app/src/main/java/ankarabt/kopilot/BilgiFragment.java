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
public class BilgiFragment extends Fragment {

    TextView toplam_km_text_total,gidilen_km_text_total,kalan_km_text_total,gecen_sure_text_total;
    View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView_ = inflater.inflate(R.layout.bilgi_fragment, container, false);
        rootView = rootView_;
        init();
        Log.v("bilgi vreate","ed");
        print();
        return rootView;
    }

    public void init()
    {

        toplam_km_text_total = (TextView) rootView.findViewById(R.id.toplam_km_text_total);
        gidilen_km_text_total = (TextView) rootView.findViewById(R.id.gidilen_km_text_total);
        kalan_km_text_total = (TextView) rootView.findViewById(R.id.kalan_km_text_total);
        gecen_sure_text_total = (TextView) rootView.findViewById(R.id.gecen_sure_text_total);
    }

    void print()
    {
        toplam_km_text_total.setText(Ortak.bilgi_toplam_mesafe);
        gidilen_km_text_total.setText(Ortak.bilgi_gidilen_yol);
        kalan_km_text_total.setText(Ortak.bilgi_kalan_yol);
        gecen_sure_text_total.setText(Ortak.bilgi_gecen_sure);
    }
}
