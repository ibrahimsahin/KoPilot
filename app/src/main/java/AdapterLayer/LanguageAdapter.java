package AdapterLayer;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import EntityLayer.Language;
import ankarabt.kopilot.R;

/**
 * Created by isahin on 3.5.2017.
 */

public class LanguageAdapter extends ArrayAdapter<Language> {

    Context context;
    public List<Language> tablo_list;
    int layoutResID;
    int NameID;
    private int[] colors = new int[] { 0x23755383, 0x22369620};
    private int[] colors2 = new int[] { 0x93755383, 0x10369620};
    View view;

    public LanguageAdapter(Context context, int layoutResourceID, List<Language> listItems)
    {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.tablo_list = listItems;
        this.layoutResID = layoutResourceID;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Language dItem = (Language) this.tablo_list.get(position);
        final DetayBilgiOzetItemHolder drawerHolder;
        view = convertView;



        if (view == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DetayBilgiOzetItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.language_adi_item_text = (TextView) view.findViewById(R.id.directory_list_item_title);
            view.setTag(drawerHolder);

        } else
        {
            drawerHolder = (DetayBilgiOzetItemHolder) view.getTag();
        }



        if(dItem.getLanguage_adi() != null)
            drawerHolder.language_adi_item_text.setText(dItem.getLanguage_adi());
        else
            drawerHolder.language_adi_item_text.setText("");



        return view;
    }



    private static class DetayBilgiOzetItemHolder
    {
        TextView language_adi_item_text;
    }

}
