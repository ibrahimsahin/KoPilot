package ankarabt.kopilot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import AdapterLayer.GuzergahAdapter;
import DataLayer.Guzergah_Data;
import EntityLayer.Guzergah;
import ToolLayer.DefaultException;

/**
 * Created by isahin on 18.8.2017.
 */
public class GuzergahFragment extends Fragment {

    View rootView;
    ListView guzergah_listview;
    GuzergahAdapter guzergahAdapter;
    List<Guzergah> guzergahList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView_ = inflater.inflate(R.layout.guzergah_fragment, container, false);
        rootView = rootView_;
        init();
        getGuzergahList();
        bundle();
        return rootView;

    }

    void init() {
        guzergah_listview = (ListView)rootView.findViewById(R.id.guzergah_listview);
    }

    void getGuzergahList()
    {
        Guzergah_Data guz_data = new Guzergah_Data(getContext());
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("SELECT * FROM GUZERGAH");
        try {
            guzergahList = new ArrayList<Guzergah>();
            guzergahList = guz_data.loadFromQuery(strBuild.toString());
        } catch (DefaultException e) {
            e.printStackTrace();
        }
    }

    void bundle()
    {
        guzergahAdapter = new GuzergahAdapter(getContext(),R.layout.guzergahlar_list_item,guzergahList);
        guzergah_listview.setAdapter(guzergahAdapter);
    }
}
