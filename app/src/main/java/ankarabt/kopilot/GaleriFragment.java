package ankarabt.kopilot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import AdapterLayer.GridViewAdapter;
import AdapterLayer.ImageItem;
import DataLayer.MediaDoc_Data;
import EntityLayer.MediaDoc;
import ToolLayer.DefaultException;

/**
 * Created by isahin on 30.5.2017.
 */
public class GaleriFragment extends Fragment {
    View rootView;
    Long directory_id;

    private GridView gridView;
    private GridViewAdapter gridAdapter;
  //  ProgressDialog pd2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView_ = inflater.inflate(R.layout.image_grid_layout, container, false);
        rootView = rootView_;

        Bundle args = getArguments();
        try {

            directory_id = args.getLong("directory_id", 0);
            Log.v("level", "=>" + String.valueOf(""));
        }
        catch (Exception ex)
        {
            Log.v("level","exception");
        }

      /*  pd2 = new ProgressDialog(getContext());
        pd2.setMessage(getResources().getString(R.string.progress_message));
        pd2.setTitle(getResources().getString(R.string.progress_message_title));
        pd2.show();
        */

        load_galeri_foto_list();
        gridView = (GridView) rootView.findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(getContext(), R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                //File file = new File("/sdcard/dib/media/image/"+item.getFile_name());
                if(item.getVideo_path() == null) {
                    File file = new File(Environment.getExternalStorageDirectory().getPath(), "dib/media/image/" + item.getFile_name());
                    intent.setDataAndType(Uri.fromFile(file), "image/*");
                    startActivity(intent);
                }
                else
                {
                    File file = new File(Environment.getExternalStorageDirectory().getPath(), "dib/media/video/" + item.getFile_name());
                    intent.setDataAndType(Uri.fromFile(file), "video/mp4");
                    startActivity(intent);
                }

            /*    ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                //Create intent
                Intent intent = new Intent(getContext(), FotoFullPage.class);
                intent.putExtra("title", item.getTitle());
                Log.v("1","1");
                intent.putExtra("image", item.getImage());
                Log.v("1","2");
                //Start details activity
                try {
                    Log.v("1","3");
                    startActivity(intent);
                    Log.v("1","4");
                }
                catch (Exception ex)
                {
                    Log.v("1","5");
                    Log.v("exc",ex.getMessage());
                }
                */
            }
        });
        //pd2.dismiss();
        return rootView;
    }


    public Bitmap scaleDownBitmap(Bitmap photo, int newHeight) {

        final float densityMultiplier = getResources().getDisplayMetrics().density;

        int h= (int) (newHeight*densityMultiplier);
        int w= (int) (h * photo.getWidth()/((double) photo.getHeight()));

        photo= Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }

    private ArrayList<ImageItem> getData() {

        final ArrayList<ImageItem> imageItems = new ArrayList<>();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/dib/media/image";
        File directory = new File(path);

        if(!directory.isDirectory()){
            directory.mkdirs();
        }

        Log.v("Files", "Path: " + path);

        File[] files = directory.listFiles();
        if(files == null)
            return imageItems;

        Log.v("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            if(!foto_list_string.contains(files[i].getName()))
                continue;
            String baslik = "";
            long media_id   = -1L;
            for(MediaDoc item:foto_list)
            {
                if(item.getMedia_adi().equals(files[i].getName()))
                {
                    if(item.getMedia_baslik() != null) {
                        baslik = item.getMedia_baslik();
                        media_id = item.getId();
                        break;
                    }
                }
            }

            Log.d("Files", "FileName:" + files[i].getName());
            String fileNameWithPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/dib/media/image/"+files[i].getName();
            File file = new File(fileNameWithPath);

            try {
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(file));
                imageItems.add(new ImageItem(b, baslik ,files[i].getName() ,media_id , null));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7



        path = Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/dib/media/video";
        directory = new File(path);

        if(!directory.isDirectory()){
            directory.mkdirs();
        }

        Log.v("Files", "Path: " + path);

        files = directory.listFiles();
        if(files == null)
            return imageItems;

        Log.v("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            if(!foto_list_string.contains(files[i].getName()))
                continue;
            String baslik = "";
            long media_id   = -1L;
            for(MediaDoc item:foto_list)
            {
                if(item.getMedia_adi().equals(files[i].getName()))
                {
                    if(item.getMedia_baslik() != null) {
                        baslik = item.getMedia_baslik();
                        media_id = item.getId();
                        break;
                    }
                }
            }

            Log.d("Files", "FileName:" + files[i].getName());
            String fileNameWithPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/dib/media/video/"+files[i].getName();
            imageItems.add(new ImageItem(null, baslik ,files[i].getName() ,media_id , fileNameWithPath));

        }

        return imageItems;
    }



    List<MediaDoc> foto_list;
    List<String> foto_list_string;
    private void load_galeri_foto_list() {

        MediaDoc_Data data = new MediaDoc_Data(getContext());
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append("SELECT * FROM MEDIA WHERE Directory_id=" + directory_id);
        try {
            foto_list = new ArrayList<MediaDoc>();
            foto_list_string = new ArrayList<String>();
            foto_list = data.loadFromQuery(sqlStr.toString());


            for(MediaDoc item :foto_list) {
                Log.v("foto", "=>" + item.getMedia_adi());
                foto_list_string.add(item.getMedia_adi());
            }

        } catch (DefaultException e) {
            e.printStackTrace();
        }
    }
}
