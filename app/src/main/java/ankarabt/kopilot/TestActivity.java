package ankarabt.kopilot;

import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import EntityLayer.Nokta;
import ToolLayer.MessageBox;

/**
 * Created by isahin on 9.5.2017.
 */
public class TestActivity extends AppCompatActivity {

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


    private LocationManager mlocManager;
    private LocationListener mlocListener;

    private Handler mHandler ;
    private int total_saniye_count = 0;
    private int anlik_saniye_count = 0;
    private boolean aktif = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        init();
    }

    void init() {

        Typeface myTypeface = Typeface.createFromAsset(getAssets(),"DS-DIGIB.TTF");

        gidilen_km_text_total = (TextView) findViewById(R.id.gidilen_km_text_total);
        kalan_km_text_total = (TextView) findViewById(R.id.kalan_km_text_total);
        varis_suresi_text_ortalama = (TextView) findViewById(R.id.varis_suresi_text_ortalama);
        varis_suresi_text_instant = (TextView) findViewById(R.id.varis_suresi_text_instant);

        start = (Button) findViewById(R.id.button_start);
        button_stop = (Button) findViewById(R.id.button_stop);

        speed_text_instant = (TextView) findViewById(R.id.speed_text_instant);
        speed_text_average = (TextView) findViewById(R.id.speed_text_average);

        speed_text_instant.setTypeface(myTypeface);
        speed_text_average.setTypeface(myTypeface);
        gidilen_km_text_total.setTypeface(myTypeface);
        kalan_km_text_total.setTypeface(myTypeface);
        varis_suresi_text_ortalama.setTypeface(myTypeface);
        varis_suresi_text_instant.setTypeface(myTypeface);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(aktif && first_location != null) {
                    Log.v("saniye","tik tok");
                    total_saniye_count++;
                    anlik_saniye_count++;
                }
            }
        }, 1, 1000);



        mlocManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        mlocListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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


       /* start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */

       /* button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        */
    }





    public void start_click(View c)
    {
        MessageBox.showAlert(TestActivity.this,"test başladı",false);
        aktif = true;
    }

    public void stop_click(View c) {
        aktif = false;
        total_saniye_count  = 0;
        anlik_saniye_count = 0;
        first_location = null;
        speed_text_average.setText("00.00");
        speed_text_instant.setText("00.00");
        kalan_km_text_total.setText("00.000");
        gidilen_km_text_total.setText("00.000");
        varis_suresi_text_instant.setText("00.00");
        varis_suresi_text_ortalama.setText("00.00");
        MessageBox.showAlert(TestActivity.this,"test durduruldu",false);
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

            if(!aktif)
                return;

            if(first_location ==  null) {
                Log.v("fist loc","set");
                first_location = location;
                previous_location = location;
                return;
            }


            double distance_instant = calculate_distance(location.getLatitude(),previous_location.getLatitude(),location.getLongitude(),previous_location.getLongitude(),0,0);
            gidilen_mesafe_metre = distance_instant + gidilen_mesafe_metre;
            //double distance_first = calculate_distance(location.getLatitude(),first_location.getLatitude(),location.getLongitude(),first_location.getLongitude(),0,0);

            Log.v("anlk /total ","tik tok=>"+anlik_saniye_count+"/"+total_saniye_count);
            if(anlik_saniye_count == 0)
                anlik_saniye_count = 1;


            anlik_hiz_ms = distance_instant / anlik_saniye_count;
            anlik_hiz_kmh = anlik_hiz_ms * 3.6;

            ortalama_hiz_ms = gidilen_mesafe_metre / total_saniye_count;
            ortalama_hiz_kmh = ortalama_hiz_ms * 3.6;

            speed_text_instant.setText( String.format("%.2f",anlik_hiz_kmh)+ " KM/H");
            anlik_saniye_count = 0;


            speed_text_average.setText( String.format("%.2f",ortalama_hiz_kmh) + "  KM/H");
            previous_location = location;

            gidilen_km_text_total.setText( String.format("%.3f",gidilen_mesafe_metre / 1000)+" KM");
            kalan_km_text_total.setText( String.format("%.3f", toplam_mesafe_km - ( gidilen_mesafe_metre / 1000)) +" KM");

            kalan_mesafe_metre = toplam_mesafe_metre - gidilen_mesafe_metre;

            //anlık hız için tahmini varış süresi
            double instant_kalan_second = kalan_mesafe_metre / anlik_hiz_ms;
            int hours = (int)instant_kalan_second / 3600;
            int minutes = (int)(instant_kalan_second % 3600) / 60;
            int seconds = (int)instant_kalan_second % 60;

            String timeString = String.format("%02d : %02d : %02d", hours, minutes, seconds);

            if(String.valueOf(anlik_hiz_kmh).equals("0.0"))
                varis_suresi_text_instant.setText("-- : -- : --");
            else
                varis_suresi_text_instant.setText(timeString);


            //ortalama hız için tahmini varış süresi
            instant_kalan_second = kalan_mesafe_metre / ortalama_hiz_ms;
            hours = (int)instant_kalan_second / 3600;
            minutes = (int)(instant_kalan_second % 3600) / 60;
            seconds = (int)instant_kalan_second % 60;

            timeString = String.format("%02d : %02d : %02d", hours, minutes, seconds);
            if(String.valueOf(ortalama_hiz_kmh).equals("0.0"))
                varis_suresi_text_ortalama.setText("-- : -- : --");
            else
                varis_suresi_text_ortalama.setText(timeString);



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




}
