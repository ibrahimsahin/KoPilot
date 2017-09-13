package AdapterLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.util.List;

import EntityLayer.MediaDoc;
import ankarabt.kopilot.R;

/**
 * Created by isahin on 4.5.2017.
 */


public class CustomSlideAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<MediaDoc> image_list;


    public CustomSlideAdapter(Context context , List<MediaDoc> image_list_) {

        image_list = image_list_;
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return image_list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.slide_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);


        Log.v("image geldi adapter","=>"+image_list.get(position).getMedia_adi());
        File imgFile = new File(Environment.getExternalStorageDirectory().getPath() + "/dib/media/image/"+image_list.get(position).getMedia_adi());

        try {
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
            } else {
                Log.v("image slide", "belirtilen isimde resim yok");
            }
        }
        catch (Exception ex)
        {
            Log.v("custom slider","catch.resim adapter calısmadı");
        }


        //imageView.setImageResource(mResources[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}