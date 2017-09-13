package ankarabt.kopilot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cardiomood.android.controls.gauge.SpeedometerGauge;
import com.cardiomood.android.controls.progress.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import DataLayer.Guzergah_Data;
import DataLayer.Nokta_Data;
import EntityLayer.Guzergah;
import EntityLayer.Nokta;
import ToolLayer.DefaultException;
import ToolLayer.MessageBox;

/**
 * Created by isahin on 14.8.2017.
 */
public class TestFragment  extends Fragment {

    Button start;
    Button button_stop;
    Double toplam_mesafe_km = 100.00;
    Double kalan_mesafe_km = 0.00;
    Double gidilen_mesafe_km = 0.00;

    Double toplam_mesafe_metre = 100000.00;
    Double kalan_mesafe_metre = 0.00;
    Double gidilen_mesafe_metre = 0.00;

    Double anlik_hiz_ms = 0.00;
    Double ortalama_hiz_ms = 0.00;

    Double anlik_hiz_kmh = 0.00;
    Double ortalama_hiz_kmh = 0.00;


    TextView speed_text_instant;
    TextView speed_text_average;
    TextView gidilen_km_text_total;
    TextView kalan_km_text_total;
    TextView varis_suresi_text_ortalama;
    TextView varis_suresi_text_instant;
    TextView gecen_sure_text_total;
    TextView toplam_km_text_total;

    TextView varis_suresi1_tv,varis_suresi2_tv,toplam_yol_tv,kalan_yol_tv;
    LinearLayout varis_suresi1_ly , varis_suresi2_ly , toplam_yol_ly , kalan_yol_ly;

    private LocationManager mlocManager;
    private LocationListener mlocListener;

    private Handler mHandler;
    private int total_saniye_count = 0;
    private int anlik_saniye_count = 0;
    private boolean aktif = false;

    View rootView;


    private SpeedometerGauge speedometer;

   // private TabLayout tabLayout;
   // private ViewPager viewPager;
    private String totalMesafe = "0";
    Typeface myTypeface;



    @Override
    public void onPause() {
        Log.v("test frag","on pause");
        Bundle args = getArguments();
        args.clear();
        anlik_fragment = null;
        ortalama_fragment = null;
        bilgi_fragment = null;

      //  viewPager = null;
     //   tabLayout = null;
        adapter = null;

        super.onPause();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView_ = inflater.inflate(R.layout.test_fragment, container, false);
        rootView = rootView_;
        Log.v("on cereate","test fragment");
        myTypeface = Typeface.createFromAsset(getContext().getAssets(), "DS-DIGIB.TTF");
        toplam_km_text_total = (TextView) rootView.findViewById(R.id.toplam_km_text_total);
        toplam_km_text_total.setTypeface(myTypeface);

        kalan_km_text_total = (TextView) rootView.findViewById(R.id.kalan_km_text_total);
        varis_suresi_text_ortalama = (TextView) rootView.findViewById(R.id.varis_suresi_text_ortalama);
        varis_suresi_text_instant = (TextView) rootView.findViewById(R.id.varis_suresi_text_instant);
        varis_suresi1_tv =  (TextView) rootView.findViewById(R.id.varis_suresi1_tv);
        varis_suresi2_tv = (TextView) rootView.findViewById(R.id.varis_suresi2_tv);
        toplam_yol_tv = (TextView) rootView.findViewById(R.id.toplam_yol_tv);
        kalan_yol_tv = (TextView) rootView.findViewById(R.id.kalan_yol_tv);

        varis_suresi1_ly = (LinearLayout) rootView.findViewById(R.id.varis_suresi1_ly);
        varis_suresi2_ly = (LinearLayout) rootView.findViewById(R.id.varis_suresi2_ly);
        toplam_yol_ly = (LinearLayout) rootView.findViewById(R.id.toplam_yol_ly);
        kalan_yol_ly = (LinearLayout) rootView.findViewById(R.id.kalan_yol_ly);


        Bundle args = getArguments();
        try {
            totalMesafe = args.getString("totalMesafe");
            if(!totalMesafe.equals("0"))
            {

                Ortak.bilgi_toplam_mesafe = totalMesafe;
                toplam_mesafe_km = Double.valueOf(totalMesafe);
                toplam_mesafe_metre = toplam_mesafe_km * 1000;
                Log.v("toplma mesafe","=>"+toplam_mesafe_km);
                toplam_km_text_total.setText(String.valueOf(toplam_mesafe_km)+" KM");
            }
            else
            {
                toplam_km_text_total.setText("00.000");
                toplam_km_text_total.setVisibility(View.INVISIBLE);
                toplam_yol_tv.setVisibility(View.INVISIBLE);
                kalan_km_text_total.setVisibility(View.INVISIBLE);
                kalan_yol_tv.setVisibility(View.INVISIBLE);
                varis_suresi_text_instant.setVisibility(View.INVISIBLE);
                varis_suresi1_tv.setVisibility(View.INVISIBLE);
                varis_suresi_text_ortalama.setVisibility(View.INVISIBLE);
                varis_suresi2_tv.setVisibility(View.INVISIBLE);

                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) varis_suresi1_ly.getLayoutParams();
                lp.height = 0;
                varis_suresi1_ly.setLayoutParams(lp);

                LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) varis_suresi2_ly.getLayoutParams();
                lp2.height = 0;
                varis_suresi2_ly.setLayoutParams(lp2);

                LinearLayout.LayoutParams lp3 = (LinearLayout.LayoutParams) toplam_yol_ly.getLayoutParams();
                lp3.height = 0;
                toplam_yol_ly.setLayoutParams(lp3);

                LinearLayout.LayoutParams lp4 = (LinearLayout.LayoutParams) kalan_yol_ly.getLayoutParams();
                lp4.height = 0;
                kalan_yol_ly.setLayoutParams(lp4);
            }
        }
        catch (Exception ex)
        {
            Log.v("total mesafe","exception");
        }
        finally {
            Log.v("finally","----");
            init();
            return rootView;

        }
    }

    int tab_index = 0;

    void init() {


       // viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
       // setupViewPager(viewPager);

       // tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
     //   tabLayout.setupWithViewPager(viewPager);
        // tabLayout.getTabAt(0).setIcon(R.drawable.car3);
        //   tabLayout.getTabAt(1).setIcon(R.drawable.wheel);
        //   tabLayout.getTabAt(2).setIcon(R.drawable.time);

      /*  tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                tab_index = tabLayout.getSelectedTabPosition();
                if (tab_index == 0) {
                    anlik_yazdir();
                }
                if (tab_index == 1)
                {
                    bilgi_yazdir();
                }
                if(tab_index == 2)
                {
                    bilgi_yazdir();
                    anlik_yazdir();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        */


        // Customize SpeedometerGauge
        speedometer = (SpeedometerGauge) rootView.findViewById(R.id.speedometer);


        // Add label converter
        speedometer.setLabelConverter(new SpeedometerGauge.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                return String.valueOf((int) Math.round(progress));
            }
        });

        // configure value range and ticks
        speedometer.setMaxSpeed(300);
        speedometer.setMajorTickStep(30);
        speedometer.setMinorTicks(2);
        speedometer.setLabelTextSize(28);

        // Configure value range colors
        speedometer.addColoredRange(30, 140, Color.GREEN);
        speedometer.addColoredRange(140, 180, Color.YELLOW);
        speedometer.addColoredRange(180, 400, Color.RED);


        gidilen_km_text_total = (TextView) rootView.findViewById(R.id.gidilen_km_text_total);

        gecen_sure_text_total = (TextView) rootView.findViewById(R.id.gecen_sure_text_total);


        start = (Button) rootView.findViewById(R.id.button_start);
        button_stop = (Button) rootView.findViewById(R.id.button_stop);

        speed_text_instant = (TextView) rootView.findViewById(R.id.speed_text_instant);
        speed_text_average = (TextView) rootView.findViewById(R.id.speed_text_average);

        speed_text_instant.setTypeface(myTypeface);
        speed_text_average.setTypeface(myTypeface);
        gidilen_km_text_total.setTypeface(myTypeface);
        kalan_km_text_total.setTypeface(myTypeface);
        varis_suresi_text_ortalama.setTypeface(myTypeface);
        varis_suresi_text_instant.setTypeface(myTypeface);
        gecen_sure_text_total.setTypeface(myTypeface);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageBox.showAlert(getContext(), "test başladı", false);
                aktif = true;
            }
        });


        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aktif = false;
                total_saniye_count = 0;
                anlik_saniye_count = 0;
                first_location = null;
                speed_text_average.setText("00.00");
                speed_text_instant.setText("00.00");
                kalan_km_text_total.setText("00.000");
                gidilen_km_text_total.setText("00.000");
                varis_suresi_text_instant.setText("00.00");
                varis_suresi_text_ortalama.setText("00.00");
                gecen_sure_text_total.setText("00.00");
                speedometer.setSpeed(0, 1000, 300);
                Ortak.anlik_speed_text_instant = "--.--";
                Ortak.anlik_varis_suresi_text_instant = "-- : -- : --";
                Ortak.ortalama_speed_text =  "--.--";
                Ortak.ortalama_varis_suresi_text = "-- : -- : --";
                Ortak.bilgi_gecen_sure = "--.--";
                Ortak.bilgi_gidilen_yol ="--.--";
                Ortak.bilgi_kalan_yol = "--.--";
                Ortak.bilgi_toplam_mesafe = "-- : -- : --";


                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }
                final EditText input = new EditText(getContext());
                input.setTextColor(getResources().getColor(R.color.bembeyaz));
                builder.setView(input);


                if(totalMesafe.equals("0")) {
                    if(Ortak.nokta_list.size()<2) {
                        MessageBox.showAlert(getContext(), "Test durduruldu!", false);
                        return;
                    }
                    builder.setTitle("Güzergah Kayıt")
                            .setMessage("Güzergah bilgisi kaydedilsin mi?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Guzergah_Data guzergah_data = new Guzergah_Data(getContext());
                                    Nokta_Data nokta_data = new Nokta_Data(getContext());
                                    try {
                                        List<Guzergah> guz_list = new ArrayList<Guzergah>();
                                        Guzergah guzergah = new Guzergah();
                                        guzergah.setGuzergah_adi(input.getText().toString());
                                        String mesafe = String.format("%.2f", gidilen_mesafe_metre / 1000);
                                        mesafe = mesafe.replace(",",".");
                                        guzergah.setMesafe(Double.valueOf(mesafe));
                                        guz_list.add(guzergah);

                                        Long guz_id = guzergah_data.insertFromContent(guz_list);
                                        Log.v("kaydedilen guz id ", "=>" + guz_id);

                                        for (Nokta n : Ortak.nokta_list)
                                            n.setMustid(guz_id);

                                        nokta_data.insertFromContent(Ortak.nokta_list);
                                        Ortak.nokta_list.clear();
                                        MessageBox.showAlert(getContext(), "Güzergah kaydedildi!", false);
                                    } catch (DefaultException e) {
                                        e.printStackTrace();
                                    }

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Ortak.nokta_list.clear();
                                    MessageBox.showAlert(getContext(), "Test durduruldu!", false);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else
                {
                    MessageBox.showAlert(getContext(), "Test durduruldu!", false);
                }
            }
        });


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (aktif && first_location != null) {
                    total_saniye_count++;
                    anlik_saniye_count++;
                }
            }
        }, 1, 1000);


        mlocManager = (LocationManager) getContext().getSystemService(getContext().LOCATION_SERVICE);
        mlocListener = new MyLocationListener();

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);

    }


    AnlikFragment anlik_fragment;
    OrtalamaFragment ortalama_fragment;
    BilgiFragment bilgi_fragment;
    ViewPagerAdapter adapter;

    private void setupViewPager(ViewPager viewPager) {

       adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.notifyDataSetChanged();

        if(anlik_fragment != null)
            Log.v("anlik null degil","ok");

        anlik_fragment = new AnlikFragment();
        ortalama_fragment = new OrtalamaFragment();
        bilgi_fragment = new BilgiFragment();

        adapter.addFragment(anlik_fragment, "ANLIK");
        adapter.addFragment(ortalama_fragment, "ORTALAMA");
        adapter.addFragment(bilgi_fragment, "BİLGİ");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    public static double calculate_distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }


    Location previous_location;
    Location first_location;

    public class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            if (!aktif)
                return;

            if (first_location == null) {
                Log.v("fist loc", "set");
                first_location = location;
                previous_location = location;
                return;
            }


            double distance_instant = calculate_distance(location.getLatitude(), previous_location.getLatitude(), location.getLongitude(), previous_location.getLongitude(), 0, 0);
            gidilen_mesafe_metre = distance_instant + gidilen_mesafe_metre;
            //double distance_first = calculate_distance(location.getLatitude(),first_location.getLatitude(),location.getLongitude(),first_location.getLongitude(),0,0);

            Log.v("anlk /total ", "tik tok=>" + anlik_saniye_count + "/" + total_saniye_count);
            if (anlik_saniye_count == 0)
                anlik_saniye_count = 1;


            anlik_hiz_ms = distance_instant / anlik_saniye_count;
            anlik_hiz_kmh = anlik_hiz_ms * 3.6;

            ortalama_hiz_ms = gidilen_mesafe_metre / total_saniye_count;
            ortalama_hiz_kmh = ortalama_hiz_ms * 3.6;

            speed_text_instant.setText(String.format("%.0f", anlik_hiz_kmh) + " KM/H");
            anlik_saniye_count = 0;


            speed_text_average.setText(String.format("%.0f", ortalama_hiz_kmh) + "  KM/H");
            previous_location = location;

            gidilen_km_text_total.setText(String.format("%.2f", gidilen_mesafe_metre / 1000) + " KM");
            kalan_km_text_total.setText(String.format("%.2f", toplam_mesafe_km - (gidilen_mesafe_metre / 1000)) + " KM");

            kalan_mesafe_metre = toplam_mesafe_metre - gidilen_mesafe_metre;

            //anlık hız için tahmini varış süresi
            double instant_kalan_second = kalan_mesafe_metre / anlik_hiz_ms;
            int hours = (int) instant_kalan_second / 3600;
            int minutes = (int) (instant_kalan_second % 3600) / 60;
            int seconds = (int) instant_kalan_second % 60;

            String timeString = String.format("%02d : %02d : %02d", hours, minutes, seconds);

            if (String.valueOf(anlik_hiz_kmh).equals("0.0"))
                varis_suresi_text_instant.setText("-- : -- : --");
            else
                varis_suresi_text_instant.setText(timeString);


            //ortalama hız için tahmini varış süresi
            instant_kalan_second = kalan_mesafe_metre / ortalama_hiz_ms;
            hours = (int) instant_kalan_second / 3600;
            minutes = (int) (instant_kalan_second % 3600) / 60;
            seconds = (int) instant_kalan_second % 60;

            timeString = String.format("%02d : %02d : %02d", hours, minutes, seconds);
            if (String.valueOf(ortalama_hiz_kmh).equals("0.0"))
                varis_suresi_text_ortalama.setText("-- : -- : --");
            else
                varis_suresi_text_ortalama.setText(timeString);


            //geçen süre
            hours = (int) total_saniye_count / 3600;
            minutes = (int) (total_saniye_count % 3600) / 60;
            seconds = (int) total_saniye_count % 60;

            timeString = String.format("%02d : %02d : %02d", hours, minutes, seconds);
            if (String.valueOf(total_saniye_count).equals("0"))
                gecen_sure_text_total.setText("-- : -- : --");
            else
                gecen_sure_text_total.setText(timeString);


            Nokta nokta = new Nokta();
            nokta.setLongtitude(location.getLongitude());
            nokta.setLatitude(location.getLatitude());
            Ortak.nokta_list.add(nokta);
            Log.v("nokta eklendi", "=>" + nokta.getLatitude());
            speedometer.setSpeed(anlik_hiz_kmh, 1000, 300);


           // anlik_yazdir();
           // ortalama_yazdir();
          //  bilgi_yazdir();

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }


   /* void anlik_yazdir()  {
        if( anlik_fragment.speed_text_instant != null) {
            anlik_fragment.speed_text_instant.setText(String.format("%.2f", anlik_hiz_kmh) + " KM/H");
            //anlık hız için tahmini varış süresi
            double instant_kalan_second_1 = kalan_mesafe_metre / anlik_hiz_ms;
            int hours_1 = (int) instant_kalan_second_1 / 3600;
            int minutes_1 = (int) (instant_kalan_second_1 % 3600) / 60;
            int seconds_1 = (int) instant_kalan_second_1 % 60;

            String timeString_1 = String.format("%02d : %02d : %02d", hours_1, minutes_1, seconds_1);

            if (String.valueOf(anlik_hiz_kmh).equals("0.0")) {
                anlik_fragment.varis_suresi_text_instant.setText("-- : -- : --");
                Ortak.anlik_varis_suresi_text_instant = "-- : -- : --";
            }
            else {
                anlik_fragment.varis_suresi_text_instant.setText(timeString_1);
                Ortak.anlik_varis_suresi_text_instant = timeString_1;
            }

            Ortak.anlik_speed_text_instant = String.format("%.2f", anlik_hiz_kmh) + " KM/H";

        }
    }
    void ortalama_yazdir()  {

        if( ortalama_fragment.speed_text_average != null) {
            ortalama_fragment.speed_text_average.setText(String.format("%.2f", ortalama_hiz_kmh) + "  KM/H");

            double instant_kalan_second_2 = kalan_mesafe_metre / ortalama_hiz_ms;
            int hours_2 = (int) instant_kalan_second_2 / 3600;
            int minutes_2 = (int) (instant_kalan_second_2 % 3600) / 60;
            int seconds_2 = (int) instant_kalan_second_2 % 60;

            String timeString_2 = String.format("%02d : %02d : %02d", hours_2, minutes_2, seconds_2);
            if (String.valueOf(ortalama_hiz_kmh).equals("0.0")) {
                ortalama_fragment.varis_suresi_text_ortalama.setText("-- : -- : --");
                Ortak.ortalama_varis_suresi_text = "-- : -- : --";
            }
            else {
                ortalama_fragment.varis_suresi_text_ortalama.setText(timeString_2);
                Ortak.ortalama_speed_text = timeString_2;
            }
        }
    }
    void bilgi_yazdir()  {

        if (bilgi_fragment.toplam_km_text_total != null) {
            bilgi_fragment.toplam_km_text_total.setText(Ortak.bilgi_toplam_mesafe);
            bilgi_fragment.gidilen_km_text_total.setText(String.format("%.3f", gidilen_mesafe_metre / 1000) + " KM");
            bilgi_fragment.kalan_km_text_total.setText(String.format("%.3f", toplam_mesafe_km - (gidilen_mesafe_metre / 1000)) + " KM");


            Ortak.bilgi_gidilen_yol = String.format("%.3f", gidilen_mesafe_metre / 1000) + " KM";
            Ortak.bilgi_kalan_yol = String.format("%.3f", toplam_mesafe_km - (gidilen_mesafe_metre / 1000)) + " KM";

            int hours_3 = (int) total_saniye_count / 3600;
            int minutes_3 = (int) (total_saniye_count % 3600) / 60;
            int seconds_3 = (int) total_saniye_count % 60;

            String timeString_3 = String.format("%02d : %02d : %02d", hours_3, minutes_3, seconds_3);
            if (String.valueOf(total_saniye_count).equals("0")) {
                bilgi_fragment.gecen_sure_text_total.setText("-- : -- : --");
                Ortak.bilgi_gecen_sure = "-- : -- : --";
            }
            else {
                bilgi_fragment.gecen_sure_text_total.setText(timeString_3);
                Ortak.bilgi_gecen_sure = timeString_3;
            }
        }
    }
    */



}
