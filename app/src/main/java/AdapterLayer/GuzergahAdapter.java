package AdapterLayer;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import DataLayer.Nokta_Data;
import EntityLayer.Guzergah;
import EntityLayer.Nokta;
import ToolLayer.DefaultException;
import ankarabt.kopilot.HomeActivity;
import ankarabt.kopilot.MainActivity;
import ankarabt.kopilot.MapFragment;
import ankarabt.kopilot.R;
import ankarabt.kopilot.TestFragment;

/**
 * Created by isahin on 3.5.2017.
 */

public class GuzergahAdapter extends ArrayAdapter<Guzergah> {

    Context context;
    public List<Guzergah> tablo_list;
    int layoutResID;
    int NameID;
    private int[] colors = new int[] { 0x23755383, 0x22369620};
    private int[] colors2 = new int[] { 0x93755383, 0x10369620};
    View view;

    public GuzergahAdapter(Context context, int layoutResourceID, List<Guzergah> listItems)
    {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.tablo_list = listItems;
        this.layoutResID = layoutResourceID;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Guzergah dItem = (Guzergah) this.tablo_list.get(position);
        final DetayBilgiOzetItemHolder drawerHolder;
        view = convertView;



        if (view == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DetayBilgiOzetItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.guzergah_item_title = (TextView) view.findViewById(R.id.guzergah_item_title);
            drawerHolder.harita_button = (Button) view.findViewById(R.id.harita_button);
            drawerHolder.test_button = (Button) view.findViewById(R.id.test_button);
            view.setTag(drawerHolder);

        } else
        {
            drawerHolder = (DetayBilgiOzetItemHolder) view.getTag();
        }



        if(dItem.getGuzergah_adi() != null)
            drawerHolder.guzergah_item_title.setText(dItem.getGuzergah_adi());
        else
            drawerHolder.guzergah_item_title.setText("");


        drawerHolder.test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)context).fragmentChange(String.valueOf(dItem.getMesafe()));

            }
        });


        drawerHolder.harita_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("guzergah mid","=>"+dItem.getMid());
                Intent intent = new Intent(getContext() ,MapFragment.class);
                intent.putExtra("guzergah_mid",String.valueOf(dItem.getMid()));
                getContext().startActivity(intent);
            }
        });


        return view;
    }






    private static class DetayBilgiOzetItemHolder
    {
        TextView guzergah_item_title;
        Button harita_button;
        Button test_button;
    }

}
