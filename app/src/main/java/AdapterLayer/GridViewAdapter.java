package AdapterLayer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ankarabt.kopilot.R;

/**
 * Created by isahin on 30.5.2017.
 */
public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();

            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            holder.play_icon_image = (ImageView) row.findViewById(R.id.play_icon_image);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        ImageItem item = (ImageItem) data.get(position);

        if(item.getVideo_path() == null) {
            holder.play_icon_image.setVisibility(View.INVISIBLE);
            holder.imageTitle.setText(item.getTitle());
            holder.image.setImageBitmap(item.getImage());

        }
        else
        {
            holder.play_icon_image.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse(item.getVideo_path());
            Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(uri.getPath(),
                    MediaStore.Images.Thumbnails.MINI_KIND);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(thumbnail);
            holder.image.setImageBitmap(thumbnail);

            /*holder.image.setVisibility(View.INVISIBLE);
            MediaController mediaController= new MediaController(getContext());
            mediaController.setAnchorView(holder.video);
            Uri uri = Uri.parse(item.getVideo_path());

            holder.video.setMediaController(mediaController);
            holder.video.setVideoURI(uri);
            holder.video.setZOrderOnTop(true);
            holder.video.requestFocus();

            Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(uri.getPath(),
                    MediaStore.Images.Thumbnails.MINI_KIND);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(thumbnail);
            holder.video.setBackgroundDrawable(bitmapDrawable);*/
        }

        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
        ImageView play_icon_image;
    }
}