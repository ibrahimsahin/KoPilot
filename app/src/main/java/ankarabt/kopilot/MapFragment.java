package ankarabt.kopilot;

        import android.content.pm.PackageManager;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Toast;

        import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
        import com.google.android.gms.maps.CameraUpdate;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.MapView;
        import com.google.android.gms.maps.MapsInitializer;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.CustomCap;
        import com.google.android.gms.maps.model.Dash;
        import com.google.android.gms.maps.model.Dot;
        import com.google.android.gms.maps.model.Gap;
        import com.google.android.gms.maps.model.JointType;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.maps.model.PatternItem;
        import com.google.android.gms.maps.model.Polygon;
        import com.google.android.gms.maps.model.Polyline;
        import com.google.android.gms.maps.model.PolylineOptions;
        import com.google.android.gms.maps.model.RoundCap;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

        import DataLayer.Nokta_Data;
        import DataLayer.Ziyaret_Data;
        import EntityLayer.Nokta;
        import EntityLayer.Ziyaret;
        import ToolLayer.DefaultException;

/**
 * Created by isahin on 28.5.2017.
 */
public class MapFragment extends AppCompatActivity implements OnMapReadyCallback {

    View rootView;
    private GoogleMap map;
    MapView mapView;

    private TrackGPS gps;
    double longitude;
    double latitude;
    String guzergah_mid = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_fragment_places);

        guzergah_mid = getIntent().getStringExtra("guzergah_mid");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    void draw_polyline(GoogleMap map_)
    {
        PolylineOptions options = new PolylineOptions();
        options.addAll(latlong_list);


        Polyline polyline1 = map_.addPolyline(options.clickable(true));
        polyline1.setTag("A");
        styleDot(polyline1);
    }


    private static final int POLYLINE_STROKE_WIDTH_PX = 10;

    private void stylePolyline(Polyline polyline) {
        String type = "";
        // Get the data object stored with the polyline.
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "A":
                // Use a custom bitmap as the cap at the start of the line.
                polyline.setStartCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.start), 10));
                polyline.setEndCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.end), 10));
                break;
            case "B":
                // Use a round cap at the start of the line.
                polyline.setStartCap(new RoundCap());
                break;
        }

        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(Color.BLUE);
        polyline.setJointType(JointType.ROUND);
        polyline.setGeodesic(true);
    }



    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;


    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private static final List<PatternItem> PATTERN_POLYGON_BETA =
            Arrays.asList(DOT, GAP, DASH, GAP);

    private void styleDot(Polyline polyline) {
        String type = "";
        // Get the data object stored with the polygon.
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }

        List<PatternItem> pattern = null;
        int strokeColor = COLOR_BLACK_ARGB;

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "A":
                // Apply a stroke pattern to render a dashed line, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;

                polyline.setStartCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.start), 10));
                polyline.setEndCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.end), 10));

                break;
        }

        polyline.setPattern(pattern);
        polyline.setColor(Color.BLUE);
    }



    List<Nokta> nokta_list;
    List<LatLng> latlong_list;
    void getNoktaList(String guzergah_mid)
    {
        Nokta_Data data = new Nokta_Data(MapFragment.this);
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append("SELECT * FROM NOKTA WHERE mustid =" + guzergah_mid);
        try {
            nokta_list = new ArrayList<Nokta>();
            latlong_list = new ArrayList<LatLng>();
            nokta_list = data.loadFromQuery(sqlStr.toString());
            Log.v("nokta size","=>"+nokta_list.size());
            for(Nokta item :nokta_list) {
                Log.v("nokta", "=>" + item.getLongtitude()+"-"+item.getLatitude());
                latlong_list.add(new LatLng(item.getLatitude(),item.getLongtitude()));
            }

        } catch (DefaultException e) {
            e.printStackTrace();
        }
    }


    private void loadPlacesFromSqlite() {
        Ziyaret_Data data = new Ziyaret_Data(this);
        StringBuilder sqlStr = new StringBuilder();


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;


        //kendi locationu alÄ±nÄ±yor
        gps = new TrackGPS(MapFragment.this);
        if (gps.canGetLocation()) {
            longitude = gps.getLongitude();
            latitude = gps.getLatitude();

            float zoomLevel = 16; //This goes up to 21
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), zoomLevel));

            // LatLng my_coor = new LatLng(latitude, longitude);
            //  Log.v("my loc", "=>" + longitude + "-" + latitude);
            //   map.addMarker(new MarkerOptions().position(my_coor).title("").icon(BitmapDescriptorFactory.fromResource(R.drawable.my_loc)));
            //  map.moveCamera(CameraUpdateFactory.newLatLng(my_coor));
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
            map.setMyLocationEnabled(true);

            if(!guzergah_mid.equals("0"))
            {
                getNoktaList(guzergah_mid);
                draw_polyline(map);

            }
        }
        else
        {
            gps.showSettingsAlert();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(gps != null)
            gps.stopUsingGPS();
    }



}
