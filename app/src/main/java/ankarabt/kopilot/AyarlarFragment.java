package ankarabt.kopilot;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.serialization.PropertyInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import DataLayer.ConfigData;
import DataLayer.Content_Data;
import DataLayer.Directory_Data;
import DataLayer.Dua_Data;
import DataLayer.Language_Data;
import DataLayer.Ziyaret_Data;
import EntityLayer.Content;
import EntityLayer.Directory;
import EntityLayer.Dua;
import EntityLayer.Language;
import EntityLayer.Ziyaret;
import ToolLayer.DefaultException;
import ToolLayer.MessageBox;
import ToolLayer.RSOperator;
import ToolLayer.StateReceiver;

/**
 * Created by isahin on 5.6.2017.
 */
public class AyarlarFragment extends Fragment {


    View rootView;
    Button paylas_button , puan_button , feedback_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView_ = inflater.inflate(R.layout.ayarlar_fragment, container, false);
        rootView = rootView_;

        init();
        return rootView;
    }

    void init()
    {
        paylas_button = (Button) rootView.findViewById(R.id.paylas_button);
        puan_button = (Button) rootView.findViewById(R.id.puan_button);
        feedback_button = (Button) rootView.findViewById(R.id.feedback_button);

        paylas_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = getResources().getString(R.string.my_app_play_store_link);
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hız Uygulaması Uygulaması");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        puan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyAppOnPlayStore(getContext() , getResources().getString(R.string.my_app_package));
            }
        });


        feedback_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTo = getResources().getString(R.string.feedback_mail);
                String feedBackSubject = getResources().getString(R.string.feedback_subject);

                String deviceInfo ="Cihaz Bilgileri:";
                deviceInfo += "\n OS Versiyon: " + Build.VERSION.RELEASE;
                deviceInfo += "\n OS API Level: " + android.os.Build.VERSION.SDK_INT;
                deviceInfo += "\n App Versiyon: " + BuildConfig.VERSION_NAME;
                deviceInfo += "\n Cihaz: " + Build.MANUFACTURER;
                deviceInfo += "\n Model: " + android.os.Build.MODEL;

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + emailTo));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, feedBackSubject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, deviceInfo);
                startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
            }
        });



    }




    public void openMyAppOnPlayStore(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("market://details?id=" + packageName));
        context.startActivity(intent);
    }




    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




}
