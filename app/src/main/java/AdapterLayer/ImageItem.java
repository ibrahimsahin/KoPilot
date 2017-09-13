package AdapterLayer;

import android.graphics.Bitmap;

/**
 * Created by isahin on 30.5.2017.
 */
public class ImageItem {
    private Bitmap image;
    private String title;
    private String file_name;
    private Long media_id;
    private String video_path;

    public ImageItem(Bitmap image, String title , String file_name , Long media_id , String video_path ) {
        super();
        this.image = image;
        this.title = title;
        this.file_name = file_name;
        this.media_id = media_id;
        this.video_path = video_path;
    }



    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public Long getMedia_id() {
        return media_id;
    }

    public void setMedia_id(Long media_id) {
        this.media_id = media_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}