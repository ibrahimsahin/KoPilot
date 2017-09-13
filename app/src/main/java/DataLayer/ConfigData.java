package DataLayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import ToolLayer.DefaultException;


public class ConfigData
{



    public String LANGUAGE;
    public String SERVICURL;
    public String MEKKE_OTEL_INDEX;

    public String getMEDINE_OTEL_INDEX() {
        return MEDINE_OTEL_INDEX;
    }

    public String getMEKKE_OTEL_INDEX() {
        return MEKKE_OTEL_INDEX;
    }

    public String getAD_SOYAD() {
        return AD_SOYAD;
    }

    public String MEDINE_OTEL_INDEX;
    public String AD_SOYAD;





    //public static String SERVERNAME = "http://172.20.10.6";
    //public static String SERVERNAME = "http://192.168.101.222";
      public static String SERVERNAME = "http://10.6.14.120";
    //public static String SERVERNAME = "http://192.168.0.19";

    public static String NAMESPACE = "http://tempuri.org/";
    Context activity;
    private String firsttime = "";



    public ConfigData()
    {

       // SERVICURL = "http://172.20.10.6/WebService.asmx";
          SERVICURL = "http://10.6.14.120/WebService.asmx";
       //SERVICURL = "http://192.168.101.222/WebService.asmx";
        //SERVICURL = "http://192.168.0.19/WebService.asmx";

    }

    public String getSERVICURL() {
        return SERVICURL;
    }

    public ConfigData(Context activity_)
    {
        activity = activity_;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        this.SERVICURL=preferences.getString("SERVICEURL", "");
        this.firsttime=preferences.getString("firsttime","");
        this.LANGUAGE =preferences.getString("LANGUAGE","");
        this.MEKKE_OTEL_INDEX =preferences.getString("MEKKE_OTEL_INDEX","");
        this.MEDINE_OTEL_INDEX =preferences.getString("MEDINE_OTEL_INDEX","");
        this.AD_SOYAD =preferences.getString("AD_SOYAD","");
    }



    public void setFirsttime(String firsttime) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("firsttime",firsttime);
        editor.apply();
        Log.v("firstime set","=>"+firsttime);
        this.firsttime = firsttime;
    }


    public String getFirsttime() {
        return firsttime;
    }

    public String getLanguage() {
        return LANGUAGE;
    }


    public void setSelectedLanguage(String language_) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("LANGUAGE",language_);
        editor.apply();
        this.LANGUAGE = language_;
    }


    public void setMekkeOtelIndex(String mekke_otel_index) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("MEKKE_OTEL_INDEX",mekke_otel_index);
        editor.apply();
        this.MEKKE_OTEL_INDEX = mekke_otel_index;
    }


    public void setMedineOtelIndex(String medine_otel_index) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("MEDINE_OTEL_INDEX",medine_otel_index);
        editor.apply();
        this.MEDINE_OTEL_INDEX = medine_otel_index;
    }


    public void setAdSoyad(String ad_soyad) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("AD_SOYAD",ad_soyad);
        editor.apply();
        this.AD_SOYAD = ad_soyad;
    }




    public void setSERVICURL(String SERVICURL) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("SERVICEURL",SERVICURL);
        editor.apply();
        this.SERVICURL = SERVICURL;
    }


    public Context getActivity() {
        return activity;
    }

    public void setActivity(Context activity) {
        this.activity = activity;
    }




    public void setBaseConfigData() throws DefaultException {


    }





}
