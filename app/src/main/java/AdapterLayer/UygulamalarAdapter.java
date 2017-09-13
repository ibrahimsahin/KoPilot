package AdapterLayer;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import EntityLayer.Uygulamalar;
import ankarabt.kopilot.R;

/**
 * Created by isahin on 3.5.2017.
 */

public class UygulamalarAdapter extends ArrayAdapter<Uygulamalar> {

    Context context;
    public List<Uygulamalar> tablo_list;
    int layoutResID;
    int NameID;
    private int[] colors = new int[] { 0x23755383, 0x22369620};
    private int[] colors2 = new int[] { 0x93755383, 0x10369620};
    View view;

    public UygulamalarAdapter(Context context, int layoutResourceID, List<Uygulamalar> listItems)
    {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.tablo_list = listItems;
        this.layoutResID = layoutResourceID;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Uygulamalar dItem = (Uygulamalar) this.tablo_list.get(position);
        final DetayBilgiOzetItemHolder drawerHolder;
        view = convertView;



        if (view == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DetayBilgiOzetItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.uygulama_list_item_title = (TextView) view.findViewById(R.id.uygulama_list_item_title);
            drawerHolder.uygulama_list_image_view = (ImageView) view.findViewById(R.id.uygulama_list_image_view);
            view.setTag(drawerHolder);

        } else
        {
            drawerHolder = (DetayBilgiOzetItemHolder) view.getTag();
        }



        if(dItem.getUygulama_adi() != null)
            drawerHolder.uygulama_list_item_title.setText(dItem.getUygulama_adi());
        else
            drawerHolder.uygulama_list_item_title.setText("");

        if(dItem.getUygulama_logo() != null) {
            File imgFile = new File(Environment.getExternalStorageDirectory().getPath() + "/dib/media/image/"+dItem.getUygulama_logo());
            try {
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    drawerHolder.uygulama_list_image_view.setImageBitmap(myBitmap);
                } else {
                    Log.v("image slide", "belirtilen isimde resim yok");
                    drawerHolder.uygulama_list_image_view.setImageResource(R.drawable.list_item);
                }
            }
            catch (Exception ex)
            {
                Log.v("custom slider","catch.resim adapter calısmadı");
            }
        }
        else
            drawerHolder.uygulama_list_item_title.setText("");

        return view;
    }



    private static class DetayBilgiOzetItemHolder
    {
        TextView uygulama_list_item_title;
        ImageView uygulama_list_image_view;
    }

}
