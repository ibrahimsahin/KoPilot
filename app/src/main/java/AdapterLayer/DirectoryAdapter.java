package AdapterLayer;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.List;

import EntityLayer.Directory;
import ankarabt.kopilot.R;

/**
 * Created by isahin on 3.5.2017.
 */

public class DirectoryAdapter extends ArrayAdapter<Directory> {

    Context context;
    public List<Directory> tablo_list;
    int layoutResID;
    int NameID;
    private int[] colors = new int[] { 0x23755383, 0x22369620};
    private int[] colors2 = new int[] { 0x93755383, 0x10369620};
    View view;
    private Filter planetFilter;
    private List<Directory> origDirectoryList;

    public DirectoryAdapter(Context context, int layoutResourceID, List<Directory> listItems)
    {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.tablo_list = listItems;
        this.layoutResID = layoutResourceID;
        this.origDirectoryList = listItems;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


            final Directory dItem = (Directory) this.tablo_list.get(position);
            final DetayBilgiOzetItemHolder drawerHolder;
            view = convertView;


            if (view == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                drawerHolder = new DetayBilgiOzetItemHolder();

                view = inflater.inflate(layoutResID, parent, false);
                drawerHolder.directory_adi_item_text = (TextView) view.findViewById(R.id.directory_list_item_title);
                view.setTag(drawerHolder);

            } else {
                drawerHolder = (DetayBilgiOzetItemHolder) view.getTag();
            }


            if (dItem.getDirectory_adi() != null)
                drawerHolder.directory_adi_item_text.setText(dItem.getDirectory_adi());
            else
                drawerHolder.directory_adi_item_text.setText("");

        return view;
    }



    private static class DetayBilgiOzetItemHolder
    {
        TextView directory_adi_item_text;
    }




    //custom filterlar
 /*   @Override
    public Filter getFilter() {
        if (planetFilter == null)
            planetFilter = new PlanetFilter();

        return planetFilter;
    }



    private class PlanetFilter extends Filter {



        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = origDirectoryList;
                results.count = origDirectoryList.size();
            }
            else {
                // We perform filtering operation
                List<Directory> nPlanetList = new ArrayList<Directory>();

                for (Directory p : tablo_list) {
                    if (p.getDirectory_adi().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        nPlanetList.add(p);
                }

                results.values = nPlanetList;
                results.count = nPlanetList.size();

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                tablo_list = (List<Directory>) results.values;
                notifyDataSetChanged();
            }

        }

    }
    */

}
