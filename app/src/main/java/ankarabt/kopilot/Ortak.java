package ankarabt.kopilot;

import java.util.ArrayList;
import java.util.List;

import EntityLayer.Nokta;

/**
 * Created by isahin on 8.5.2017.
 */
public class  Ortak {

    public static int ID_HAC_INDEX = 0;
    public static int ID_UMRE_INDEX = 1;
    public static int ID_DUA_INDEX = 2;
    public static int ID_ZIYARET_YERI_INDEX = 3;
    public static int ID_ORGANIZASYON_INDEX = 4;
    public static int ID_SAGLIK_BILGILERI_INDEX = 5;
    public static int ID_ONEMLI_HUSUSLAR_INDEX = 6;
    public static int ID_GALERI_INDEX = 7;
    public static int ID_DOKUMANLAR_INDEX = 8;
    public static int ID_UYGULAMALAR_INDEX = 9;
    public static int ID_OTELLER_INDEX = 10;
    public static int ID_AYARLAR_INDEX = 11;

    public static String local_video_path  = "/dib/media/video";
    public static String local_audio_path  = "/dib/media/audio";
    public static String local_image_path  = "/dib/media/image";

    public static boolean show_file_size_info = false;

    public static List<Nokta> nokta_list = new ArrayList<Nokta>();

    public static String anlik_speed_text_instant = "--.--";
    public static String anlik_varis_suresi_text_instant = "-- : -- : --";

    public static String ortalama_speed_text = "--.--";
    public static String ortalama_varis_suresi_text = "-- : -- : --";

    public static String bilgi_toplam_mesafe = "100KM";
    public static String bilgi_gidilen_yol = "--.--";
    public static String bilgi_kalan_yol = "--.--";
    public static String bilgi_gecen_sure = "-- : -- : --";


}
